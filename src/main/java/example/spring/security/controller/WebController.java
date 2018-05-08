package example.spring.security.controller;

import example.spring.security.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = Constants.PATH_ROOT, method = RequestMethod.GET)
    public String getHomePage() {
        return Constants.PAGE_ROOT;
    }

    @RequestMapping(value = Constants.PATH_LOGIN, method = RequestMethod.GET)
    public String getLogin() {
        return Constants.PAGE_LOGIN;
    }

    @RequestMapping(value = Constants.PATH_INDEX, method = RequestMethod.GET)
    public String getIndex() {
        return Constants.PAGE_INDEX;
    }

}
