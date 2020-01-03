package fr.takima.demo.controller.rest;

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
        for( int i=0; i< all_temperatures.size(); i++){
            Map<String, String> map = new TreeMap<>();
            map.put("date",all_temperatures.get(i).getDate());
            map.put("temperature", all_temperatures.get(i).getTemperature() + "");
            temperatures.add(map);
        }

        return temperatures;
    }
}
