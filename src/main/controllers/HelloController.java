package main.controllers;

import main.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main controller
 */
@Controller
@SessionAttributes(names = {"login"})
public class HelloController {
    private static final Logger logger = Logger.getLogger(HelloController.class);
    private UserService userService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello(){
        return "login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password){
        ModelAndView modelAndView = new ModelAndView();

        if (userService.auth(login, password) != null) {
            //modelAndView.setViewName("redirect:/list/");
        } else {
            //modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }
}
