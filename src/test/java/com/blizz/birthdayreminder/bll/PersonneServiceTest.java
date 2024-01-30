package com.blizz.birthdayreminder.bll;

import com.blizz.birthdayreminder.bll.exeption.PersonneExeption;
import com.blizz.birthdayreminder.bll.interfaces.PersonneService;
import com.blizz.birthdayreminder.bo.Personne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PersonneServiceTest {
    @Autowired
    PersonneService service;

    @Test
    @Transactional
    void contextLoads() throws PersonneExeption {
        Personne personne = new Personne("Theo", "Wincke", LocalDate.of(2003, 6, 6));
        service.add(personne);
        List<Personne> lst = service.findAll();
        lst.forEach(System.out::println);
    }
}
