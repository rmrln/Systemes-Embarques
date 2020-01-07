package fr.takima.demo.service;

import fr.takima.demo.model.Temperature;
import fr.takima.demo.model.dao.TemperatureDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService
{
    private TemperatureDAO temperatureDAO;

    public TemperatureService(TemperatureDAO temperatureDAO) {
        this.temperatureDAO = temperatureDAO;
    }

    public Iterable<Temperature> list() {
        return temperatureDAO.findAll();
    }

    public Iterable<Temperature> save(List<Temperature> temperatures) {
        return temperatureDAO.saveAll(temperatures);
    }

    public void deleteAll() {
        temperatureDAO.deleteAll();
    }

}