package mvc.controllers;

import mvc.dao.PersonDAO;
import mvc.models.Person;
import mvc.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class OpenPage {
    private final PersonDAO personDAO;
    @Autowired
    public OpenPage(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping("/admin")
    public String back()
    {
        return "openpage";
    }
    @Autowired
    private FileService fileService;
    @GetMapping("/successful")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) throws FileNotFoundException {
        Person person = personDAO.success(email, password);
        if (person != null) {
            String department = person.getDepartment();
            if ("Main".equals(department)) {
                return "/openpage";
            }
            switch (department) {
                case "Finance":
                    return processDepartment(model, "finance.txt","/taskpage" );
                case "Industrial":
                    return processDepartment(model, "industrial.txt", "/taskpage");
                case "Technical":
                    return processDepartment(model, "technical.txt", "/taskpage");
                default:
                    model.addAttribute("error", "Unknown department: " + department);
                    return "redirect:/";
            }
        }
        return "redirect:/";
    }
    private String processDepartment(Model model, String fileName, String page) {
        try {
            List<String> lines = fileService.readFile(fileName);
            model.addAttribute("lines", lines);
        } catch (FileNotFoundException e) {
            model.addAttribute("error", "File " + fileName + " not found");
        } catch (IOException e) {
            model.addAttribute("error", "Error reading file " + fileName);
        }
        return page;
    }


}
