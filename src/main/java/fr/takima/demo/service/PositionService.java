package fr.takima.demo.service;

import fr.takima.demo.model.Position;
import fr.takima.demo.model.dao.PositionDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private PositionDAO positionDAO;

    public PositionService(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    public Iterable<Position> list() {
        return positionDAO.findAll();
    }

    public Iterable<Position> save(List<Position> positions) {
        return positionDAO.saveAll(positions);
    }

    public void deleteAll() {
        positionDAO.deleteAll();
    }
}
