package main.controllers;

import main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by onetrs on 27.04.2017.
 */
@Controller
public class ListController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(name = "/list/")
    public String showList(Model model){
        model.addAttribute("studentHello", "Hello student from servlet!");
        model.addAttribute("studentList", studentService.getAllStudents());
        return "list";
    }
}