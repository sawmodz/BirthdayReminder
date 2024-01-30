package com.blizz.birthdayreminder.dal.interfaces;

import com.blizz.birthdayreminder.bo.Club;
import com.blizz.birthdayreminder.bo.Personne;

import java.util.List;

public interface ClubDAO {
    void add(Club club);
    Club findById(int id);
    List<Club> findAll();
    void setClubToPersonne(Club club, Personne personne);
}
