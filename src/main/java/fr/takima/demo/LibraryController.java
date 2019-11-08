package fr.takima.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
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
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "../CV-Project/uploads/";
    //TODO : Creer un folder pour medecin
    //TODO : Creer un folder pour temperature

    public LibraryController(PatientDAO patientDAO, MedecinDAO medecinDAO, TemperatureDAO temperatureDAO) {

        this.patientDAO = patientDAO;
        this.medecinDAO = medecinDAO;
        this.temperatureDAO = temperatureDAO;
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

        m.addAttribute("temperatures",temperatures);
        return "temperature";
    }

    @GetMapping("/dataPatient/membre/{id}")
    public String consultPatient(Model m,@PathVariable Long id) {
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
            if(temperatureArrayList.get(i).getTemperature()>37){
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
        m.addAttribute("sizePointTemperature",sizePointTemperature);
        m.addAttribute("IdMember", id);
        return "dataPatient";
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
