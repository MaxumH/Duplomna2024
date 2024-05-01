package mvc.controllers;

import mvc.dao.PersonDAO;
import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /* @GetMapping("/successful/email={email}&password={password}")
    public String login(@PathVariable("email") String email, @PathVariable("password") String password, Model model) {
        Person person = personDAO.success(email, password);
        if (person != null) {
            if ("Main".equals(person.getDepartment())) {
                return "/openpage";
            } else {
                return "/firstpage";
            }
        } else {
            return "redirect:/";
        }
    }*/
    @GetMapping("/successful")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Person person = personDAO.success(email, password);
        if (person != null) {
            if ("Main".equals(person.getDepartment())) {
                return "/openpage";
            } else {
                return "/firstpage";
            }
        } else {
            return "redirect:/";
        }
    }
}
