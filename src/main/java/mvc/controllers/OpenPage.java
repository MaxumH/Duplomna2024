package mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class OpenPage {
    @GetMapping()
    public String mainwindow()
    {
        return "/openpage";
    }
}
