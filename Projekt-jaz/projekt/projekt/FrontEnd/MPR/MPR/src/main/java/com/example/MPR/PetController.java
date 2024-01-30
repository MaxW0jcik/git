package com.example.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.MPR.PetService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PetController {
    @Autowired

    private final PetService service;

    public PetController(PetService petService) {
        this.service = petService;
    }
    @GetMapping(value = "/indexPets")
    public String getViewAllPets(Model model)
    {
        model.addAttribute("pets", service.findAllPets());
        return "indexPets";
    }
    @GetMapping(value = "/editPet/{id}")
    public String getEditPetView(@PathVariable("id") Integer id, Model model)
    {
        Optional<Pet> pet = Optional.ofNullable(service.findPetById(id));
        if(pet.isPresent())
        {
            model.addAttribute("pet", pet.get());
        }
        else
        {
            model.addAttribute("errorMessage", "Pet is not present");
        }
        return "editPet";
    }
    @PostMapping(value = "/editPet")
    public String editPet(Pet pet, Model model, RedirectAttributes redirectAttributes)
    {
        if(pet.getPetName()==null)
        {
            model.addAttribute("errorMessage","Name cant be null");
            return "editPet";
        }
        else
        {
            service.updatePet(pet);
            redirectAttributes.addFlashAttribute("successMessage","Pet successfully updated");
            return "redirect:/indexPets";
        }
    }
    @GetMapping(value = "/addPet")
    public String getAddPetView(Model model)
    {
        model.addAttribute("pet", new Pet("", ""));
        return "addPet";
    }
    @PostMapping (value = "/addPet")
    public String addPet(Pet pet, Model model, RedirectAttributes redirectAttributes)
    {
        service.addPet(pet);
        redirectAttributes.addFlashAttribute("successMessage","Pet successfully added");
        return "redirect:/indexPet";
    }

    @GetMapping (value = "/deletePet/{id}")
    public String deletePet(@PathVariable("id") Integer id, Model model)
    {
        Pet pet = service.findPetById(id);
        if(pet!=null)
        {
            model.addAttribute("pet", pet);
            service.deletePet(id);
            return "redirect:/indexPets";
        }
        else
        {
            model.addAttribute("errorMessage", "Pet is not present");
        }
        return "indexPets";
    }

}