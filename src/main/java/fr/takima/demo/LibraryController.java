package fr.takima.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
@RequestMapping("/")
@Controller
public class LibraryController {

    private final UserDAO userDAO;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "../CV-Project/uploads/";

    public LibraryController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String homePage(Model m) {
        //m.addAttribute("users", userDAO.findAll());
        return "index";
    }


    @GetMapping("/medecin")
    public String medecinPage(Model m){
        m.addAttribute("patients", userDAO.findAll());
        return "medecin";
    }

    @GetMapping("/famille")
    public String famillePage(Model m){
        return "famille";
    }

    @GetMapping("/index")
    public String indexPage(Model m) {

        return "index";
    }

    @GetMapping("/index_dev")
    public String testPage(Model m) {

        return "index_dev";
    }


    @GetMapping("/delete/{id}")
    public RedirectView deleteUser(@ModelAttribute User user, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message", "CV supprimé avec succès");
        userDAO.deleteById(user.getId());
        return new RedirectView("/list");
    }

    @GetMapping("/consult/{id}")
    public String consultCV(Model m,@PathVariable Long id, User user) {
        Optional str = userDAO.findById(id);
        if(str.isPresent()){
            m.addAttribute("user",userDAO.findById(id).get());
        }
        return "consult";
    }

    @GetMapping("/modif/{id}")
    public String viewCV(Model m,@PathVariable Long id){
        m.addAttribute("user",userDAO.findById(id));
        return "modif";
    }

    @PostMapping("/modif")
    public RedirectView updateUser(@ModelAttribute User user,@RequestParam("file") MultipartFile file, RedirectAttributes attrs) {

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            attrs.addFlashAttribute("msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msg",
                    "Bad" + file.getOriginalFilename() + "'");
        }
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès"+UPLOADED_FOLDER);
        System.out.println(UPLOADED_FOLDER + file.getOriginalFilename());
        String pathModif = "../../../../uploads/";
        String photo = pathModif + file.getOriginalFilename();

        userDAO.save(user);
        return new RedirectView("/modif/"+user.getId());
    }
}
