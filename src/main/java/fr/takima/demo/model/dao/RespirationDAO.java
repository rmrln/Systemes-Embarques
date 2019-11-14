package fr.takima.demo.model.dao;

import fr.takima.demo.model.Respiration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespirationDAO extends CrudRepository<Respiration, Long> {
}
