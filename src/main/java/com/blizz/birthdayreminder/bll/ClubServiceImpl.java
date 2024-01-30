package com.blizz.birthdayreminder.bll;

import com.blizz.birthdayreminder.bll.interfaces.ClubService;
import com.blizz.birthdayreminder.bo.Club;
import com.blizz.birthdayreminder.bo.Personne;
import com.blizz.birthdayreminder.dal.interfaces.ClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubDAO clubDAO;

    @Override
    public void add(Club club) {
        clubDAO.add(club);
    }

    @Override
    public Club findById(int id) {
        return clubDAO.findById(id);
    }

    @Override
    public List<Club> findAll() {
        return clubDAO.findAll();
    }

    @Override
    public void setClubToPersonne(Club club, Personne personne) {
        clubDAO.setClubToPersonne(club, personne);
    }
}
