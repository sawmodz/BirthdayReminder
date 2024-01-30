package com.blizz.birthdayreminder.dal.interfaces;

import com.blizz.birthdayreminder.bo.Personne;

import java.util.List;

public interface PersonneDAO {
    public void add(Personne personne);
    public Personne findById(int id);
    public List<Personne> findAll();
    public List<Personne> findNextMonth();
}
