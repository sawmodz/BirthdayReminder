package com.blizz.birthdayreminder.dal;

import com.blizz.birthdayreminder.bo.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class PersonneDAORepository implements PersonneDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    RowMapper<Personne> personneRowMapper = (rs, i)->
            new Personne(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"), rs.getDate("date_naissance").toLocalDate());

    @Override
    public void add(Personne personne) {
        String insert = "INSERT INTO PERSONNE (prenom, nom, date_naissance) VALUES (:prenom, :nom, :dateNaissance)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("prenom", personne.getPrenom());
        namedParameters.addValue("nom", personne.getNom());
        namedParameters.addValue("dateNaissance", personne.getDateNaissance());

        jdbcTemplate.update(insert, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) personne.setId(keyHolder.getKey().intValue());
    }

    @Override
    public Personne findById(int id) {
        String findById = "SELECT id, prenom, nom, date_naissance FROM PERSONNE WHERE id = :id";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return jdbcTemplate.queryForObject(findById, namedParameters, personneRowMapper);
    }

    @Override
    public List<Personne> findAll() {
        String findAll = "SELECT id, prenom, nom, date_naissance FROM PERSONNE";

        return jdbcTemplate.query(findAll, personneRowMapper);
    }

    @Override
    public List<Personne> findNextMonth() {
        List<Personne> personnes = findAll();

        return personnes.stream().filter(personne -> isBirthdayInNextMonth(personne.getDateNaissance())).collect(Collectors.toList());
    }

    private boolean isBirthdayInNextMonth(LocalDate targetDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int currentMonth = cal.get(Calendar.MONTH);
        cal.setTime(java.sql.Date.valueOf(targetDate));
        int targetMonth = cal.get(Calendar.MONTH);
        return targetMonth == currentMonth + 1;
    }
}
