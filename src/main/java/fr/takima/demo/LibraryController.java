package fr.takima.demo;

import fr.takima.demo.model.*;
import fr.takima.demo.model.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
@RequestMapping("/")
@Controller
public class LibraryController {

    private final PatientDAO patientDAO;
    private final MedecinDAO medecinDAO;
    private final TemperatureDAO temperatureDAO;
    private final PositionDAO positionDAO;
    private final RespirationDAO respirationDAO;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "../CV-Project/uploads/";
    //TODO : Creer un folder pour medecin
    //TODO : Creer un folder pour temperature

    public LibraryController(PatientDAO patientDAO, MedecinDAO medecinDAO, TemperatureDAO temperatureDAO, PositionDAO positionDAO, RespirationDAO respirationDAO) {

        this.patientDAO = patientDAO;
        this.medecinDAO = medecinDAO;
        this.temperatureDAO = temperatureDAO;
        this.positionDAO = positionDAO;
        this.respirationDAO = respirationDAO;
    }

    @GetMapping
    public String homePage(Model m) {
        return "index";
    }


    @GetMapping("/medecin")
    public String medecinPage(Model m){
        m.addAttribute("membres", patientDAO.findAll());
        return "medecin";
    }

    @GetMapping("/famille")
    public String famillePage(Model m){

        return "famille";
    }

    @GetMapping("/index")
    public String indexPage(Model m) {
        return "index";
    }


    @GetMapping("/delete/membre/{id}")
    public RedirectView deletePatient(@ModelAttribute Patient patient, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message", "CV supprimé avec succès");
        patientDAO.deleteById(patient.getId());
        return new RedirectView("/famille");
    }

    @GetMapping("/temperature/membre/{id}")
    public String consultTemperatures(Model m,@PathVariable Long id) {
        Iterable<Temperature> str = temperatureDAO.findAll();
        ArrayList<Temperature> all_temperatures = new ArrayList<>();
        str.forEach(all_temperatures::add);
        ArrayList<Temperature> temperatures = new ArrayList<>();
        for( int i=0; i< all_temperatures.size(); i++){
            if(all_temperatures.get(i).getId_patient() == id ){
                temperatures.add(all_temperatures.get(i));
            }
        }

        m.addAttribute("data",temperatures);
        m.addAttribute("labelData", "Température");
        return "dataTableau";
    }

    @GetMapping("/dataPatient/membre/{id}")
    public String consultPatient(Model m,@PathVariable Long id) {
        //Temperature
        //get all the temperatures in de DB
        Iterable<Temperature> str = temperatureDAO.findAll();
        ArrayList<Temperature> all_temperatures = new ArrayList<>();
        str.forEach(all_temperatures::add);

        //get the temperature of the patient with id
        ArrayList<Temperature> temperatureArrayList = new ArrayList<>();
        for( int i=0; i< all_temperatures.size(); i++){
            if(all_temperatures.get(i).getId_patient() == id ){
                temperatureArrayList.add(all_temperatures.get(i));
            }
        }
        double[] temperaturesTable = new double[temperatureArrayList.size()];
        String[] dateTempertaureTable = new String[temperatureArrayList.size()];
        String[] borderColorTemperatures = new String[temperatureArrayList.size()];
        double[] sizePointTemperature = new double[temperatureArrayList.size()];
        for( int i=0; i< temperatureArrayList.size(); i++){

            temperaturesTable[i] = temperatureArrayList.get(i).getTemperature();
            dateTempertaureTable[i] = temperatureArrayList.get(i).getDate();
            if(temperatureArrayList.get(i).getTemperature()>37.5){
                borderColorTemperatures[i] = "rgba(54, 162, 235, 1)";
                sizePointTemperature[i] = 7;
            }else{
                borderColorTemperatures[i] = "rgba(255, 99, 132, 1)";
                sizePointTemperature[i] = 4;
            }
        }

        m.addAttribute("temperatures",temperaturesTable);
        m.addAttribute("datesTemperatures",dateTempertaureTable);
        m.addAttribute("borderColorTemperatures",borderColorTemperatures);
        m.addAttribute("sizePointTemperatures",sizePointTemperature);

        //Respirations
        //get all the respirations in de DB
        Iterable<Respiration> str_respiration = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str_respiration.forEach(all_respirations::add);

        //get the respirations of the patient with id
        ArrayList<Respiration> respirationsArrayList = new ArrayList<>();
        for( int i=0; i< all_respirations.size(); i++){
            if(all_respirations.get(i).getId_patient() == id ){
                respirationsArrayList.add(all_respirations.get(i));
            }
        }
        int[] respirationsTable = new int[respirationsArrayList.size()];
        String[] dateRespirationsTable = new String[respirationsArrayList.size()];
        String[] borderColorRespirations = new String[respirationsArrayList.size()];
        double[] sizePointRespirations = new double[respirationsArrayList.size()];
        for( int i=0; i< respirationsArrayList.size(); i++){

            respirationsTable[i] = respirationsArrayList.get(i).getAirflow();
            dateRespirationsTable[i] = respirationsArrayList.get(i).getDate();
            borderColorRespirations[i] = "rgba(255, 99, 132, 1)";
            sizePointRespirations[i] = 4;
        }

        m.addAttribute("respirations",respirationsTable);
        m.addAttribute("datesRespirations",dateRespirationsTable);
        m.addAttribute("borderColorRespirations",borderColorRespirations);
        m.addAttribute("sizePointRespirations",sizePointRespirations);

        // Positions
        //get all the positions in de DB
        Iterable<Position> str_position = positionDAO.findAll();
        ArrayList<Position> all_positions = new ArrayList<>();
        str_position.forEach(all_positions::add);

        //get the positions of the patient with id
        ArrayList<Position> positionsArrayList = new ArrayList<>();
        for( int i=0; i< all_positions.size(); i++){
            if(all_positions.get(i).getId_patient() == id ){
                positionsArrayList.add(all_positions.get(i));
            }
        }




        String[] positionsTable = new String[positionsArrayList.size()];
        String[] datePositionsTable = new String[positionsArrayList.size()];
        String[] borderColorPositions = new String[positionsArrayList.size()];
        double[] sizePointPositions = new double[positionsArrayList.size()];
        for( int i=0; i< positionsArrayList.size(); i++) {
            // recuperer le type de position : debout ou allongé
            if (positionsArrayList.get(i).getPosition() == 5) {
                positionsTable[i] = "Debout"; // debout
            } else {
                positionsTable[i] = "Allongé"; // allongé
            }

            //positionsTable[i] = positionsArrayList.get(i).getPosition();
            datePositionsTable[i] = positionsArrayList.get(i).getDate();
            borderColorPositions[i] = "rgba(255, 99, 132, 1)";
            sizePointPositions[i] = 4;
        }

        m.addAttribute("positions",positionsTable);
        m.addAttribute("datesPositions",datePositionsTable);
        m.addAttribute("borderColorPositions",borderColorPositions);
        m.addAttribute("sizePointPositions",sizePointPositions);

        m.addAttribute("IdMember", id);
        return "dataPatient";
    }

    @GetMapping("/position/membre/{id}")
    public String consultPosition(Model m,@PathVariable Long id) {
        Iterable<Position> str = positionDAO.findAll();
        ArrayList<Position> all_positions = new ArrayList<>();
        str.forEach(all_positions::add);
        ArrayList<Position> positionsPatient = new ArrayList<>();
        for( int i=0; i< all_positions.size(); i++){
            if(all_positions.get(i).getId_patient() == id ){
                positionsPatient.add(all_positions.get(i));
            }
        }

        m.addAttribute("data",positionsPatient);
        m.addAttribute("labelData", "Position");
        return "dataTableau";
    }

    @GetMapping("/respiration/membre/{id}")
    public String consultRespiration(Model m,@PathVariable Long id) {
        Iterable<Respiration> str = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str.forEach(all_respirations::add);
        ArrayList<Respiration> respirationsPatient = new ArrayList<>();
        for( int i=0; i< all_respirations.size(); i++){
            if(all_respirations.get(i).getId_patient() == id ){
                respirationsPatient.add(all_respirations.get(i));
            }
        }

        m.addAttribute("data",respirationsPatient);
        m.addAttribute("labelData", "Flue d'aire");
        return "dataTableau";
    }

    @GetMapping("/modif/membre/{id}")
    public String viewPatient(Model m,@PathVariable Long id){
        m.addAttribute("patient",patientDAO.findById(id));
        return "modif";
    }

    @PostMapping("/modif/membre")
    public RedirectView updatePatient(@ModelAttribute Patient patient,@RequestParam("file") MultipartFile file, RedirectAttributes attrs) {

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            attrs.addFlashAttribute("msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msg",
                    "Bad" + file.getOriginalFilename() + "'");
        }
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès"+UPLOADED_FOLDER);
        System.out.println(UPLOADED_FOLDER + file.getOriginalFilename());
        String pathModif = "../../../../uploads/";
        String photo = pathModif + file.getOriginalFilename();

        patientDAO.save(patient);
        return new RedirectView("/modif/membre/"+patient.getId());
    }

    @GetMapping("/delete/medecin/{id}")
    public RedirectView deleteMedecin(@ModelAttribute Medecin medecin, RedirectAttributes attrs) {
        medecinDAO.deleteById(medecin.getId());
        attrs.addFlashAttribute("message", "Medecin supprimé avec succès");
        return new RedirectView("/medecin");
    }

    @GetMapping("/consult/medecin/{id}")
    public String consultMedecin(Model m,@PathVariable Long id) {
        Optional str = medecinDAO.findById(id);
        if(str.isPresent()){
            m.addAttribute("patient",str.get());
        }
        return "consultMedecin";
    }

    @GetMapping("/modif/medecin/{id}")
    public String viewMedecin(Model m,@PathVariable Long id){
        m.addAttribute("patient",medecinDAO.findById(id));
        return "modifMedecin";
    }

    @PostMapping("/modif/medecin")
    public RedirectView updateMedecin(@ModelAttribute Medecin medecin,@RequestParam("file") MultipartFile file, RedirectAttributes attrs) {

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            attrs.addFlashAttribute("msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msg",
                    "Bad" + file.getOriginalFilename() + "'");
        }
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès"+UPLOADED_FOLDER);
        System.out.println(UPLOADED_FOLDER + file.getOriginalFilename());
        String pathModif = "../../../../uploads/";
        String photo = pathModif + file.getOriginalFilename();

        medecinDAO.save(medecin);
        return new RedirectView("/modif/medecin/"+medecin.getId());
    }

    @GetMapping("/delete/temperature/{id}")
    public RedirectView deleteTemperature(@ModelAttribute Temperature temperature, RedirectAttributes attrs) {
        Optional<Temperature> str = temperatureDAO.findById(temperature.getId());
        temperatureDAO.deleteById(temperature.getId());
        attrs.addFlashAttribute("message", "Temperature supprimée avec succès");

        return new RedirectView("/temperature/membre/" + str.get().getId_patient());
    }
}
