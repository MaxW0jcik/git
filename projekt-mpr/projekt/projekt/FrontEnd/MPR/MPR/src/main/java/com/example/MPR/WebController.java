package com.example.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WebController {
    @Autowired
    private final MyRestService service;

    public WebController(MyRestService myRestService) {this.service = myRestService;}

    @GetMapping(value = "/index")
    public String getIndexView(Model model) {
        model.addAttribute("users", service.findAll());
        return "index";
    }

    @GetMapping(value = "/addUser")
    public String getAddView(Model model) {
        model.addAttribute("user", new UserDTO("","",0));
        return "addUser";
    }

    @PostMapping (value = "/addUser")
    public String addUser(UserDTO user, Model model, RedirectAttributes redirectAttributes) {
        service.addUser(user);
        redirectAttributes.addFlashAttribute("successMessage","User successfully added");
        return "redirect:/index";
    }


    @GetMapping("/users/delete/{id}")
    public String getDeleteView(@PathVariable("id") Long id, Model model) {
        UserDTO user = service.findById(id);
        if (user !=null) {
            model.addAttribute("user", user);
            return "deleteUser";
        } else {
            model.addAttribute("error", "User is not present");
            return "error";
        }
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return "redirect:/index";
        } catch (HttpClientErrorException.NotFound ex) {
            return "error";
        }
    }

    @GetMapping(value="/users/editUser/{id}")
    public String getEditView(@PathVariable("id") long id, Model model){
        UserDTO user = service.findById(id);
        if(user != null){
            model.addAttribute("user", user);
        }
        else{
            model.addAttribute("errorMessage", "User is not present");
        }
        return "editUser";
    }

    @PostMapping("/users/editUser/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("user") UserDTO updatedUser) {
        service.updateUserById(updatedUser, id);
        return "redirect:/index";
    }
}
