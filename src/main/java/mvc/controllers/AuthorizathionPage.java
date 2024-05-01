package mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizathionPage {
    @GetMapping
    public String authorizathion()
    {
        return "/authorizathion";
    }
}
