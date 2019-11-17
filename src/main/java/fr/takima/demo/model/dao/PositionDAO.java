package fr.takima.demo.model.dao;

import fr.takima.demo.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDAO extends CrudRepository<Position, Long>{
}