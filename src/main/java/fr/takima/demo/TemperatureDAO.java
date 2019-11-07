package fr.takima.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TemperatureDAO extends CrudRepository<Temperature, Long>{
}
