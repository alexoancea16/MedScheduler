package com.Oancea_A.Proiect_AWJ.servicii;

import com.Oancea_A.Proiect_AWJ.model.Pacient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacientServiceTest {

    @Autowired
    private PacientService pacientService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM pacient");
        jdbcTemplate.execute("INSERT INTO pacient (id_pacient, nume, prenume, cnp, telefon, email) " +
                "VALUES (1, 'Popescu', 'Ion', '1234567890123', '0712345678', 'ion.popescu@example.com')");
        jdbcTemplate.execute("INSERT INTO pacient (id_pacient, nume, prenume, cnp, telefon, email) " +
                "VALUES (2, 'Ionescu', 'Maria', '9876543210987', '0712345679', 'maria.ionescu@example.com')");
    }

    @Test
    void testGetAllPacientiSorted() {
        List<Pacient> pacienti = pacientService.getAllPacientiSorted("nume");
        assertEquals(2, pacienti.size());
        assertEquals("Ionescu", pacienti.get(0).getNume());
        assertEquals("Popescu", pacienti.get(1).getNume());
    }

    @Test
    void testExistsByCnp() {
        assertTrue(pacientService.existsByCnp("1234567890123"));
        assertFalse(pacientService.existsByCnp("1111111111111"));
    }

    @Test
    void testAddPacient() {
        Pacient pacient = new Pacient();
        pacient.setNume("Vasile");
        pacient.setPrenume("Gheorghe");
        pacient.setCnp("2223334445556");
        pacient.setTelefon("0722334455");
        pacient.setEmail("vasile.gheorghe@example.com");

        pacientService.addPacient(pacient);

        Pacient found = pacientService.findByCnp("2223334445556");
        assertNotNull(found);
        assertEquals("Vasile", found.getNume());
        assertEquals("Gheorghe", found.getPrenume());
    }

    @Test
    void testDeletePacientById() {
        pacientService.deletePacientById(1L);
        Pacient pacient = pacientService.findById(1L);
        assertNull(pacient);
    }

    @Test
    void testUpdatePacient() {
        pacientService.updatePacient(1L, "Popescu", "Ionel", "0711111111", "ionel.popescu@example.com");
        Pacient updatedPacient = pacientService.findById(1L);

        assertNotNull(updatedPacient);
        assertEquals("Ionel", updatedPacient.getPrenume());
        assertEquals("0711111111", updatedPacient.getTelefon());
    }

    @Test
    void testFindByCnp() {
        Pacient pacient = pacientService.findByCnp("1234567890123");
        assertNotNull(pacient);
        assertEquals("Ion", pacient.getPrenume());
    }
}
