package mvc.controllers;

import mvc.dao.PersonDAO;
import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/tgaz")
public class ControllPage {
    private final  PersonDAO personDAO;
    private final PasswordEncoder passwordEncoder;
   @Autowired
    public ControllPage(PersonDAO personDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
       this.passwordEncoder = new BCryptPasswordEncoder();
   }
    @GetMapping()
    public String openPage()
    {
        return "/openpage";
    }

    @GetMapping("/hello")
    public String sayHello()
    {
        return "/firstpage";
    }
    @GetMapping("/index")
            public String index(Model model)
    {
        model.addAttribute("people",personDAO.index());
        return "/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model)  {
        model.addAttribute("person",personDAO.show(id));
        return "/show";
    }
    @GetMapping("/department/{department}")
    public String output(@PathVariable("department") String department,Model model)
    {
        List<Person> persons =  personDAO.output(department);
        model.addAttribute("department",persons);
        return "/depout";
    }

    //Binding result якщо є помилки то вони туда записуються
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {

        return "/new";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name,Model model)
    {
        model.addAttribute("body",personDAO.search(name));
        return "/search";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult) throws SQLException {

      if(bindingResult.hasErrors()) {
      return "/new";
      }
        String hashedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personDAO.save(person);
        return "redirect:/tgaz/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("person",personDAO.show(id));
        return "/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid  Person person,BindingResult bindingResult,@PathVariable("id") int id)
    {
        if(bindingResult.hasErrors())
        {
            return "/edit";
        }
        personDAO.update(id,person);
        return "redirect:/tgaz/index";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id)
    {
            personDAO.delete(id);
            return "redirect:/tgaz/index";
    }

}
