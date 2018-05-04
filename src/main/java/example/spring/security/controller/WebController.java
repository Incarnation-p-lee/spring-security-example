package example.spring.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "This is Home page!";
    }

}
