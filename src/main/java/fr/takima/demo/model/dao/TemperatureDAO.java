package fr.takima.demo.model.dao;

import fr.takima.demo.model.Temperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureDAO extends CrudRepository<Temperature, Long>{
}
