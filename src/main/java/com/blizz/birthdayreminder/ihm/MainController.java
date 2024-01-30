package com.blizz.birthdayreminder.ihm;

import com.blizz.birthdayreminder.bll.PersonneExeption;
import com.blizz.birthdayreminder.bll.PersonneService;
import com.blizz.birthdayreminder.bo.Personne;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    PersonneService service;

    @ModelAttribute("listUsers")
    public List<Personne> listUsers() {
        return service.findNextMonth();
    }

    @GetMapping
    public String index(Personne personne) {
        return "index";
    }

    @PostMapping
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String add(@ModelAttribute Personne personne, BindingResult errors, Model model) throws PersonneExeption {
        try {
            service.add(personne);
        } catch (PersonneExeption e) {
            errors.rejectValue("dateNaissance", "error.personne", e.getMessage());
            model.addAttribute("errors", errors);
            System.out.println(errors);
            return "index";
        }

        return "redirect:/";
    }
}
