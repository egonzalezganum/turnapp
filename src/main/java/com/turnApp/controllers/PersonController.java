package com.turnApp.controllers;

import com.turnApp.entities.Person;
import com.turnApp.services.CityService;
import com.turnApp.services.PersonService;
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
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String listPersons(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("person", personService.listByQ(q));
        } else {
            model.addAttribute("person", personService.listAll());
        }
        return "person-list";
    }

    @GetMapping("/form")
    public String createPerson(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Person> optional = personService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("person", optional.get());
            } else {
                return "redirect:/person/list";
            }
        } else {
            model.addAttribute("person", new Person());
        }
        model.addAttribute("cities",cityService.listAll());
        return "person-form";
    }

    @PostMapping("/save")
    public String savePerson(Model model, RedirectAttributes redirect, @ModelAttribute Person person) {
        try {
            personService.save(person);
            redirect.addFlashAttribute("succes", "¡Usuario creado con exito!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/person/list";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam(required = true) String id) {
        personService.deleteById(id);
        return "redirect:/person/list";
    }

}
