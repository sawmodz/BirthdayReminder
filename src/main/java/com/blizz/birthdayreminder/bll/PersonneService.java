package com.blizz.birthdayreminder.bll;

import com.blizz.birthdayreminder.bo.Personne;

import java.util.List;

public interface PersonneService {
    public void add(Personne personne) throws PersonneExeption;
    public Personne findById(int id);
    public List<Personne> findAll();
    public List<Personne> findNextMonth();
}
