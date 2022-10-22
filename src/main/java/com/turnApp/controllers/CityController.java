package com.turnApp.controllers;

import com.turnApp.entities.City;
import com.turnApp.services.CityService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String listCities(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("city", cityService.listAll(q));
        } else {
            model.addAttribute("city", cityService.listAll());
        }
        return "city-list";
    }

    @GetMapping("/form")
    public String createCity(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<City> optional = cityService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("city", optional.get());
            } else {
                return "redirect:/city/list";
            }
        } else {
            model.addAttribute("city", new City());
        }
        return "city-form";
    }

    @PostMapping("/save")
    public String saveCity(Model model, RedirectAttributes redirect, @ModelAttribute City city) {
        try {
            cityService.save(city);
            redirect.addFlashAttribute("succes", "¡Ciudad creada con exito!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/city/list";
    }

    @GetMapping("/delete")
    public String deleteCity(@RequestParam(required = true) String id) {
        cityService.deleteById(id);
        return "redirect:/city/list";
    }

}
