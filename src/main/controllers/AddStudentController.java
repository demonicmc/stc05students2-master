package main.controllers;

import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by onetrs on 27.04.2017.
 */
@Controller
public class AddStudentController {
    private static final Logger LOGGER = Logger.getLogger(AddStudentController.class);
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students/new")
    public String showForm(){
        return "add-student";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/students/new")
    public String addStudent(@RequestParam(name = "id") String id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "age") String age,
                             @RequestParam(name = "group_id") String group_id){

        try {
            studentService.addStudent(Integer.valueOf(id), name, Integer.valueOf(age), Integer.valueOf(group_id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:/list";
    }


}
