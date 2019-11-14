package fr.takima.demo.model.dao;


import fr.takima.demo.model.Medecin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinDAO extends CrudRepository<Medecin, Long> {
}
