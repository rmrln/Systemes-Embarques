package fr.takima.demo.model.dao;

import fr.takima.demo.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface PatientDAO extends CrudRepository<Patient, Long> {

}