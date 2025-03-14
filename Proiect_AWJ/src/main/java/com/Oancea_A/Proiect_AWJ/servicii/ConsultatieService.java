/*
Clasa pentru a realiza serviciile cerute in controller ce tin in special de clasa consuntatie, interogari pe baza de date
Oancea Constantin-Alexandru
Versiunea: 10.1.2025
*/

package com.Oancea_A.Proiect_AWJ.servicii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class ConsultatieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllConsultatiiWithPacientInfo() {
        String sql = "SELECT c.id_consultatie, c.data, c.ora, c.nr_salon, c.specializare_consultatie AS specializare, c.nume_medic, " +
                "p.nume AS pacient_nume, p.prenume AS pacient_prenume " +
                "FROM consultatie c " +
                "JOIN pacient p ON c.id_pacient = p.id_pacient";
        return jdbcTemplate.queryForList(sql);
    }

    public void deleteConsultatieById(int id) {
        String sql = "DELETE FROM consultatie WHERE id_consultatie = ?";
        jdbcTemplate.update(sql, id);
    }
    public List<Map<String, Object>> getAllConsultatiiSortedBy(String sortBy) {
        String sql = "SELECT c.id_consultatie, c.data, c.ora, c.nr_salon, c.specializare_consultatie AS specializare, " +
                "c.nume_medic, p.nume AS pacient_nume, p.prenume AS pacient_prenume " +
                "FROM consultatie c JOIN pacient p ON c.id_pacient = p.id_pacient " +
                "ORDER BY " + sortBy;
        return jdbcTemplate.queryForList(sql);
    }
    public Integer findPacientIdByCnp(String cnp) {
        String sql = "SELECT id_pacient FROM pacient WHERE cnp = ?";
        List<Integer> results = jdbcTemplate.queryForList(sql, Integer.class, cnp);
        return results.isEmpty() ? null : results.get(0);
    }

    public boolean hasConsultatieInZiuaRespectiva(int idPacient, String data) {
        String sql = "SELECT COUNT(*) FROM consultatie WHERE id_pacient = ? AND data = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idPacient, LocalDate.parse(data));
        return count != null && count > 0;
    }

    public void salveazaConsultatie(int idPacient, LocalDate data, LocalTime ora, int nrSalon, String specializare, String numeMedic) {
        String sql = "INSERT INTO consultatie (id_pacient, data, ora, nr_salon, specializare_consultatie, nume_medic) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, idPacient, data, ora, nrSalon, specializare, numeMedic);
    }

    public List<Map<String, Object>> searchConsultatiiByCnpAndDate(String cnp, String data) {
        String sql = "SELECT c.id_consultatie, c.data, c.ora, c.nr_salon, c.specializare_consultatie, " +
                "c.nume_medic, p.nume AS pacient_nume, p.prenume AS pacient_prenume " +
                "FROM consultatie c " +
                "JOIN pacient p ON c.id_pacient = p.id_pacient " +
                "WHERE p.cnp = ? AND c.data = ?";

        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);
            return jdbcTemplate.queryForList(sql, cnp, sqlDate);
        } catch (Exception e) {
            throw new RuntimeException("Eroare la cautarea consultatiilor: " + e.getMessage());
        }
    }

}

