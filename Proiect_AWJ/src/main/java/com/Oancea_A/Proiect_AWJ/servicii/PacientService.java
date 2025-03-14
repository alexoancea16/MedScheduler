/*
Clasa pentru a realiza serviciile cerute in controller ce tin in special de clasa pacient, interogari pe baza de date
Oancea Constantin-Alexandru
Versiunea: 10.1.2025
*/
package com.Oancea_A.Proiect_AWJ.servicii;

import com.Oancea_A.Proiect_AWJ.model.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pacient> getAllPacientiSorted(String sortBy) {
        String sql = "SELECT id_pacient, nume, prenume, cnp, telefon, email FROM pacient ORDER BY " + mapSortBy(sortBy);
        return jdbcTemplate.query(sql, pacientRowMapper());
    }

    private RowMapper<Pacient> pacientRowMapper() {
        return (rs, rowNum) -> {
            Pacient pacient = new Pacient();
            pacient.setId(rs.getLong("id_pacient")); // Actualizare aici
            pacient.setNume(rs.getString("nume"));
            pacient.setPrenume(rs.getString("prenume"));
            pacient.setCnp(rs.getString("cnp"));
            pacient.setTelefon(rs.getString("telefon"));
            pacient.setEmail(rs.getString("email"));
            return pacient;
        };
    }

    private String mapSortBy(String sortBy) {
        switch (sortBy) {
            case "prenume":
                return "prenume";
            case "cnp":
                return "cnp";
            default:
                return "nume"; // Implicit sortăm după nume
        }
    }
    public boolean existsByCnp(String cnp) {
        String sql = "SELECT COUNT(*) FROM pacient WHERE cnp = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, cnp);
        return count != null && count > 0;
    }

    public void addPacient(Pacient pacient) {
        String sql = "INSERT INTO pacient (nume, prenume, cnp, telefon, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pacient.getNume(), pacient.getPrenume(), pacient.getCnp(), pacient.getTelefon(), pacient.getEmail());
    }

    public Pacient findByCnp(String cnp) {
        String sql = "SELECT id_pacient, nume, prenume, cnp, telefon, email FROM pacient WHERE cnp = ?";
        List<Pacient> pacienti = jdbcTemplate.query(sql, pacientRowMapper(), cnp);
        return pacienti.isEmpty() ? null : pacienti.get(0);
    }
    public void deletePacientById(Long id) {
        String sql = "DELETE FROM pacient WHERE id_pacient = ?";
        jdbcTemplate.update(sql, id);
    }
    public void updatePacient(Long id, String nume, String prenume, String telefon, String email) {
        String sql = "UPDATE pacient SET nume = ?, prenume = ?, telefon = ?, email = ? WHERE id_pacient = ?";
        jdbcTemplate.update(sql, nume, prenume, telefon, email, id);
    }

    public Pacient findById(Long id) {
        String sql = "SELECT id_pacient, nume, prenume, cnp, telefon, email FROM pacient WHERE id_pacient = ?";
        List<Pacient> pacienti = jdbcTemplate.query(sql, pacientRowMapper(), id);
        return pacienti.isEmpty() ? null : pacienti.get(0);
    }
}
