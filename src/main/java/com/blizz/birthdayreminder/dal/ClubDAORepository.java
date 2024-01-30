package com.blizz.birthdayreminder.dal;

import com.blizz.birthdayreminder.bo.Club;
import com.blizz.birthdayreminder.bo.Personne;
import com.blizz.birthdayreminder.dal.interfaces.ClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubDAORepository implements ClubDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    RowMapper<Club> clubRowMapper = (rs, i)->
            new Club(rs.getInt("id"), rs.getString("name"));

    @Override
    public void add(Club club) {
        String add = "INSERT INTO CLUB (name) VALUES (:name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", club.getName());

        jdbcTemplate.update(add, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) club.setId(keyHolder.getKey().intValue());
    }

    @Override
    public Club findById(int id) {
        String findById = "SELECT * FROM CLUB WHERE id = :id";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return jdbcTemplate.queryForObject(findById, namedParameters, clubRowMapper);
    }

    @Override
    public List<Club> findAll() {
        String findAll = "SELECT * FROM CLUB";

        return jdbcTemplate.query(findAll, clubRowMapper);
    }

    @Override
    public void setClubToPersonne(Club club, Personne personne) {
        String setClubToPersonne = "INSERT INTO PERSONNE (id_personne, id_club) VALUES (:id_personne, :id_club)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id_personne", personne.getId());
        namedParameters.addValue("id_club", club.getId());

        personne.setClub(club);

        jdbcTemplate.update(setClubToPersonne, namedParameters);
    }
}
