package fr.takima.demo.controller.rest;

import fr.takima.demo.model.Position;
import fr.takima.demo.model.Respiration;
import fr.takima.demo.model.Temperature;
import fr.takima.demo.model.dao.PositionDAO;
import fr.takima.demo.model.dao.RespirationDAO;
import fr.takima.demo.model.dao.TemperatureDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("db")
public class DataBaseRestController {

    private final TemperatureDAO temperatureDAO;
    private final PositionDAO positionDAO;
    private final RespirationDAO respirationDAO;

    public DataBaseRestController(TemperatureDAO temperatureDAO, PositionDAO positionDAO, RespirationDAO respirationDAO) {
        this.temperatureDAO = temperatureDAO;
        this.positionDAO = positionDAO;
        this.respirationDAO = respirationDAO;
    }

    /**
     * Get the temperature
     */
    @GetMapping(path = "temperatures", produces = "application/json")
    public List<Map<String,String>> getTemperatures() {
        List<Map<String, String>> temperatures = new ArrayList<>();

        Iterable<Temperature> str = temperatureDAO.findAll();
        ArrayList<Temperature> all_temperatures = new ArrayList<>();
        str.forEach(all_temperatures::add);
        if(all_temperatures.size()>30){
            for( int i=all_temperatures.size()-30; i< all_temperatures.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_temperatures.get(i).getDate());
                map.put("temperature", all_temperatures.get(i).getTemperature() + "");
                temperatures.add(map);
            }
        }
        else {
            for( int i=0; i< all_temperatures.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_temperatures.get(i).getDate());
                map.put("temperature", all_temperatures.get(i).getTemperature() + "");
                temperatures.add(map);
            }
        }

        return temperatures;
    }




    //Get position
    @GetMapping(path = "positions", produces = "application/json")
    public List<Map<String,String>> getPositions() {
        List<Map<String, String>> positions = new ArrayList<>();

        Iterable<Position> str = positionDAO.findAll();
        ArrayList<Position> all_positions = new ArrayList<>();
        str.forEach(all_positions::add);
        if (all_positions.size()>30) {
            for( int i=all_positions.size()-30; i< all_positions.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_positions.get(i).getDate());
                map.put("position", all_positions.get(i).getPosition() + "");
                positions.add(map);
            }
        }
        else {
            for( int i=0; i< all_positions.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_positions.get(i).getDate());
                map.put("position", all_positions.get(i).getPosition() + "");
                positions.add(map);
            }
        }
        return positions;
    }

    //Get all respiration
    @GetMapping(path = "respirations", produces = "application/json")
    public List<Map<String,String>> getRespiration() {
        List<Map<String, String>> respirations = new ArrayList<>();

        Iterable<Respiration> str = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str.forEach(all_respirations::add);
        if (all_respirations.size()>100) {
            for( int i=all_respirations.size()-100; i< all_respirations.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_respirations.get(i).getDate());
                map.put("respiration", all_respirations.get(i).getAirflow() + "");
                respirations.add(map);
            }
        }
        else {
            for( int i=0; i< all_respirations.size(); i++){
                Map<String, String> map = new TreeMap<>();
                map.put("date",all_respirations.get(i).getDate());
                map.put("respiration", all_respirations.get(i).getAirflow() + "");
                respirations.add(map);
            }
        }
        /*for( int i=0; i< all_respirations.size(); i++){
            Map<String, String> map = new TreeMap<>();
            map.put("date",all_respirations.get(i).getDate());
            map.put("respiration", all_respirations.get(i).getAirflow() + "");
            respirations.add(map);
        }*/

        return respirations;
    }

    //Get 1 respiration
    @GetMapping(path = "lastrespiration", produces = "application/json")
    public Map<String,String> getLastRespiration() {
        Iterable<Respiration> str = respirationDAO.findAll();
        ArrayList<Respiration> all_respirations = new ArrayList<>();
        str.forEach(all_respirations::add);

        Map<String, String> map = new TreeMap<>();
        map.put("date",all_respirations.get(all_respirations.size()-1).getDate());
        map.put("respiration", all_respirations.get(all_respirations.size()-1).getAirflow() + "");

        return map;
    }



}