package fr.takima.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import fr.takima.demo.model.*;
import fr.takima.demo.model.dao.*;
import fr.takima.demo.service.PositionService;
import fr.takima.demo.service.RespirationService;
import fr.takima.demo.service.TemperatureService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 *
 */
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
@Controller
public class LibraryController {

    private final TemperatureDAO temperatureDAO;
    private final PositionDAO positionDAO;
    private final RespirationDAO respirationDAO;
    private final TemperatureService temperatureService;
    private final PositionService positionService;
    private final RespirationService respirationService;

    // TODO : Changer l'adresse de la rasberry Pi pour se connecter
    private final String RASBERRYPI_LOCALHOST = "172.20.10.4";

    public LibraryController(TemperatureDAO temperatureDAO,
                             PositionDAO positionDAO,
                             RespirationDAO respirationDAO,
                             TemperatureService temperatureService,
                             PositionService positionService,
                             RespirationService respirationService) {

        this.temperatureDAO = temperatureDAO;
        this.positionDAO = positionDAO;
        this.respirationDAO = respirationDAO;
        this.temperatureService = temperatureService;
        this.positionService = positionService;
        this.respirationService = respirationService;
    }

    @GetMapping
    public String homePage(Model m) {
        return "index";
    }

    @GetMapping("/medecin")
    public String medecinPage(Model m){
        return "medecin";
    }

    @GetMapping("/famille")
    public String famillePage(Model m){

        return "famille";
    }

    @GetMapping("/temperature/membre/")
    public String consultTemperatures(Model m) {
        //TODO: Quand RasberryPi connecté decommanter ça :
        getTemperatures_RasberryPi();
        Iterable<Temperature> str = temperatureDAO.findAll();
        ArrayList<Temperature> all_temperatures = new ArrayList<>();
        str.forEach(all_temperatures::add);
        ArrayList<Temperature> temperatures = new ArrayList<>();
        for( int i=0; i< all_temperatures.size(); i++){

            if (all_temperatures.get(i).getTemperature()>= 35){
                temperatures.add(all_temperatures.get(i));
            }
        }

        m.addAttribute("data",temperatures);
        m.addAttribute("labelData", "Température");
        return "dataTableau";
    }

    @GetMapping("/dataPatient/membre/")
    public String consultPatient(Model m) {
        //Temperature
        //TODO: Quand RasberryPi connecté decommanter ça :
        getTemperatures_RasberryPi();
        //get all the temperatures in de DB
        Iterable<Temperature> str = temperatureDAO.findAll();
        ArrayList<Temperature> all_temperatures = new ArrayList<>();
        str.forEach(all_temperatures::add);

        //get the temperature of the patient with id
        ArrayList<Temperature> temperatureArrayList = new ArrayList<>();
        for( int i=0; i< all_temperatures.size(); i++){
                if (all_temperatures.get(i).getTemperature()>= 35){
                    temperatureArrayList.add(all_temperatures.get(i));
                }
        }
        double[] temperaturesTable = new double[temperatureArrayList.size()];
        int temperatureAverage;
        int somme = 0;
        String[] dateTemperatureTable = new String[temperatureArrayList.size()];
        String[] borderColorTemperatures = new String[temperatureArrayList.size()];
        double[] sizePointTemperature = new double[temperatureArrayList.size()];
        for( int i=0; i< temperatureArrayList.size(); i++){


            temperaturesTable[i] = temperatureArrayList.get(i).getTemperature();
            somme += temperaturesTable[i];
            dateTemperatureTable[i] = temperatureArrayList.get(i).getDate();
            if(temperatureArrayList.get(i).getTemperature()>37.5){
                borderColorTemperatures[i] = "rgba(54, 162, 235, 1)";
                sizePointTemperature[i] = 7;
            }else{
                borderColorTemperatures[i] = "rgba(255, 99, 132, 1)";
                sizePointTemperature[i] = 4;
            }
        }
        temperatureAverage = (somme / temperatureArrayList.size());

        m.addAttribute("temperatureAverage", temperatureAverage);
        m.addAttribute("temperatures",temperaturesTable);
        m.addAttribute("datesTemperatures",dateTemperatureTable);
        m.addAttribute("borderColorTemperatures",borderColorTemperatures);
        m.addAttribute("sizePointTemperatures",sizePointTemperature);

        //Respirations
        //TODO: Quand RasberryPi connecté decommanter ça :
        getAllRespiration_RasberryPi();
        //get all the respirations in de DB
        Iterable<Respiration> str_respiration = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str_respiration.forEach(all_respirations::add);

        //get the respirations of the patient with id
        ArrayList<Respiration> respirationsArrayList = new ArrayList<>();
        for( int i=0; i< all_respirations.size(); i++){
                respirationsArrayList.add(all_respirations.get(i));
        }
        int[] respirationsTable = new int[respirationsArrayList.size()];
        String[] dateRespirationsTable = new String[respirationsArrayList.size()];
        String[] borderColorRespirations = new String[respirationsArrayList.size()];
        double[] sizePointRespirations = new double[respirationsArrayList.size()];
        for( int i=0; i< respirationsArrayList.size(); i++){

            respirationsTable[i] = respirationsArrayList.get(i).getRespiration();
            dateRespirationsTable[i] = respirationsArrayList.get(i).getDate();
            borderColorRespirations[i] = "rgba(255, 99, 132, 1)";
            sizePointRespirations[i] = 4;
        }

        m.addAttribute("respirations",respirationsTable);
        m.addAttribute("datesRespirations",dateRespirationsTable);
        m.addAttribute("borderColorRespirations",borderColorRespirations);
        m.addAttribute("sizePointRespirations",sizePointRespirations);

        // Positions
        //TODO: Quand RasberryPi connecté decommanter ça :
        getAllPosition_RasberryPi();
        //get all the positions in de DB
        Iterable<Position> str_position = positionDAO.findAll();
        ArrayList<Position> all_positions = new ArrayList<>();
        str_position.forEach(all_positions::add);

        //get the positions of the patient with id
        ArrayList<Position> positionsArrayList = new ArrayList<>();
        for( int i=0; i< all_positions.size(); i++){
                positionsArrayList.add(all_positions.get(i));
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
        return "dataPatient";
    }

    private void getTemperatures_RasberryPi() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Temperature>> typeReference = new TypeReference<List<Temperature>>(){};
        try {
            URL url = new URL("http://" + RASBERRYPI_LOCALHOST + ":8080/db/temperatures");
            List<Temperature> temperatures = mapper.readValue(url,typeReference);
            temperatureService.deleteAll();
            temperatureService.save(temperatures);
            System.out.println("Temperature Saved!");
        } catch (IOException e){
            System.out.println("Unable to save temperature: " + e.getMessage());
        }
    }

    @GetMapping("/position/membre/")
    public String consultPosition(Model m) {
        //TODO: Quand RasberryPi connecté decommanter ça :
        getAllPosition_RasberryPi();
        Iterable<Position> str = positionDAO.findAll();
        ArrayList<Position> all_positions = new ArrayList<>();
        str.forEach(all_positions::add);
        ArrayList<Position> positionsPatient = new ArrayList<>();
        for( int i=0; i< all_positions.size(); i++){
                positionsPatient.add(all_positions.get(i));
        }

        m.addAttribute("data",positionsPatient);
        m.addAttribute("labelData", "Position");
        return "dataTableau";
    }

    private void getAllPosition_RasberryPi() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Position>> typeReference = new TypeReference<List<Position>>(){};
        try {
            URL url = new URL("http://" + RASBERRYPI_LOCALHOST + ":8080/db/positions");
            List<Position> positions = mapper.readValue(url,typeReference);
            positionService.deleteAll();
            positionService.save(positions);
            System.out.println("Positions Saved!");
        } catch (IOException e){
            System.out.println("Unable to save positions: " + e.getMessage());
        }
    }


    @GetMapping("/respiration/membre/")
    public String consultRespiration(Model m) {
        //TODO: Quand RasberryPi connecté decommanter ça :
        getAllRespiration_RasberryPi();
        Iterable<Respiration> str = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str.forEach(all_respirations::add);
        ArrayList<Respiration> respirationsPatient = new ArrayList<>();
        for( int i=0; i< all_respirations.size(); i++){
                respirationsPatient.add(all_respirations.get(i));
        }

        m.addAttribute("data",respirationsPatient);
        m.addAttribute("labelData", "Flux d'air");
        return "dataTableau";
    }

    private void getLastRespiration_RasberryPi() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Respiration> typeReference = new TypeReference<Respiration>(){};
        try {
            URL url = new URL("http://" + RASBERRYPI_LOCALHOST + ":8080/db/lastrespiration");
            Respiration respiration = mapper.readValue(url,typeReference);
            respirationService.save(respiration);
            System.out.println("One respiration Saved!");
        } catch (IOException e){
            System.out.println("Unable to save one respiration: " + e.getMessage());
        }
    }

    private void getAllRespiration_RasberryPi() {
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Respiration>> typeReference = new TypeReference<List<Respiration>>(){};
        try {
            URL url = new URL("http://" + RASBERRYPI_LOCALHOST + ":8080/db/respirations");
            List<Respiration> respirations = mapper.readValue(url,typeReference);
            respirationService.deleteAll();
            respirationService.save(respirations);
            System.out.println("Respirations Saved!");
        } catch (IOException e){
            System.out.println("Unable to save respirations: " + e.getMessage());
        }
    }
}
