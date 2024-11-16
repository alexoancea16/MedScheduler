package com.database.MedScheduler.repository;

import com.database.MedScheduler.models.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicRepository extends JpaRepository<Medic, Integer> {

    List<Medic> findByNume(String nume);

    List<Medic> findByNumeAndPrenume(String nume, String prenume);

    List<Medic> findByGradProfesional(String gradProfesional);
}

