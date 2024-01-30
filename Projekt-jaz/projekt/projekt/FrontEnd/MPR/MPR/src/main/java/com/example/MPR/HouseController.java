package com.example.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HouseController {
    @Autowired

    private final HouseService service;

    public HouseController(HouseService houseService) {
        this.service = houseService;
    }
    @GetMapping(value = "/indexHouses")
    public String getViewAllHouses(Model model)
    {
        model.addAttribute("houses", service.findAllHouses());
        return "indexHouses";
    }
    @GetMapping(value = "/editHouse/{id}")
    public String getEditHouseView(@PathVariable("id") Integer id, Model model)
    {
        Optional<House> house = Optional.ofNullable(service.findHouseById(id));
        if(house.isPresent())
        {
            model.addAttribute("house", house.get());
        }
        else
        {
            model.addAttribute("errorMessage", "House is not present");
        }
        return "editHouse";
    }
    @PostMapping(value = "/editHouse")
    public String editHouse(House house, Model model, RedirectAttributes redirectAttributes)
    {
        if(house.getHouseOwner()==null)
        {
            model.addAttribute("errorMessage","Name cant be null");
            return "editHouse";
        }
        else
        {
            service.updateHouse(house);
            redirectAttributes.addFlashAttribute("successMessage","House successfully updated");
            return "redirect:/indexHoues";
        }
    }
    @GetMapping(value = "/addHouse")
    public String getAddHouseView(Model model)
    {
        model.addAttribute("house", new House("",0.0));
        return "addHouse";
    }
    @PostMapping (value = "/addHouse")
    public String addPet(House house, Model model, RedirectAttributes redirectAttributes)
    {
        service.addHouse(house);
        redirectAttributes.addFlashAttribute("successMessage","House successfully added");
        return "redirect:/indexHouse";
    }
    @GetMapping (value = "/deleteHouse/{id}")
    public String deleteHouse(@PathVariable("id") Integer id, Model model)
    {
        House house = service.findHouseById(id);
        if(house!=null)
        {
            model.addAttribute("house", house);
            service.deleteHouse(id);
            return "redirect:/indexHouses";
        }
        else
        {
            model.addAttribute("errorMessage", "House is not present");
        }
        return "indexHouses";
    }
}
}