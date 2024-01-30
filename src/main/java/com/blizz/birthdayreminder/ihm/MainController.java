package com.blizz.birthdayreminder.ihm;

import com.blizz.birthdayreminder.bll.exeption.PersonneExeption;
import com.blizz.birthdayreminder.bll.interfaces.ClubService;
import com.blizz.birthdayreminder.bll.interfaces.PersonneService;
import com.blizz.birthdayreminder.bo.Club;
import com.blizz.birthdayreminder.bo.Personne;
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
    PersonneService personneService;
    @Autowired
    ClubService clubService;

    @ModelAttribute("listUsers")
    public List<Personne> listUsers() {
        System.out.println(personneService.findNextMonth());
        return personneService.findNextMonth();
    }

    @ModelAttribute("listClub")
    public List<Club> listClub() { return clubService.findAll(); }

    @GetMapping
    public String index(Personne personne, Club club) {
        return "index";
    }

    @PostMapping("/personne")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String add(@ModelAttribute Personne personne,@ModelAttribute("club") String club , BindingResult errors, Model model) throws PersonneExeption {
        try {
            personne.setClub(clubService.findById(Integer.parseInt(club)));
            personneService.add(personne);
        } catch (PersonneExeption e) {
            errors.rejectValue("dateNaissance", "error.personne", e.getMessage());
            model.addAttribute("errors", errors);
            System.out.println(errors);
            return "index";
        }

        return "redirect:/";
    }

    @PostMapping("/club")
    public String addClub(@ModelAttribute Club club) {
        clubService.add(club);
        return "redirect:/";
    }
}
