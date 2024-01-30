package com.blizz.birthdayreminder.dal;

import com.blizz.birthdayreminder.bo.Personne;
import com.blizz.birthdayreminder.dal.interfaces.PersonneDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PersonneDAOTest {
    @Autowired
    PersonneDAO dao;

    @Test
    @Transactional
    void contextLoads() {
        Personne personne = new Personne("Theo", "Wincke", LocalDate.of(2003, 6, 6));
        dao.add(personne);
        List<Personne> lst = dao.findAll();
        lst.forEach(System.out::println);
    }
}
