package fr.takima.demo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinDAO extends CrudRepository<Medecin, Long> {
}
