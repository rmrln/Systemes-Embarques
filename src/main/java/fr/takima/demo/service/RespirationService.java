package fr.takima.demo.service;

import fr.takima.demo.model.Respiration;
import fr.takima.demo.model.dao.RespirationDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespirationService {
    private RespirationDAO respirationDAO;

    public RespirationService(RespirationDAO respirationDAO) {
        this.respirationDAO = respirationDAO;
    }

    public Iterable<Respiration> list() {
        return respirationDAO.findAll();
    }

    public Iterable<Respiration> save(List<Respiration> respirations) {
        return respirationDAO.saveAll(respirations);
    }

    public Respiration save(Respiration respirations) {
        return respirationDAO.save(respirations);
    }

    public void deleteAll() {
        respirationDAO.deleteAll();
    }
}
