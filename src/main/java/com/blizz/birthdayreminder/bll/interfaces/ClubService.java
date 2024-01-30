package com.blizz.birthdayreminder.bll.interfaces;

import com.blizz.birthdayreminder.bo.Club;
import com.blizz.birthdayreminder.bo.Personne;

import java.util.List;

public interface ClubService {
    void add(Club club);
    Club findById(int id);
    List<Club> findAll();
    void setClubToPersonne(Club club, Personne personne);
}
