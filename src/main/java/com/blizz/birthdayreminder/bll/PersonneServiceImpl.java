package com.blizz.birthdayreminder.bll;

import com.blizz.birthdayreminder.bo.Personne;
import com.blizz.birthdayreminder.dal.PersonneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneServiceImpl implements PersonneService{
    @Autowired
    PersonneDAO dao;

    @Override
    public void add(Personne personne) throws PersonneExeption {
        if(!personne.isAdult()) throw new PersonneExeption("Il faut que ce soit un adulte");
        dao.add(personne);
    }

    @Override
    public Personne findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Personne> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Personne> findNextMonth() {
        return dao.findNextMonth();
    }
}
