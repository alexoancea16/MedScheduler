package com.database.MedScheduler.service;

import com.database.MedScheduler.models.Pacient;

import java.util.List;
import java.util.Optional;

public interface PacientService {

    List<Pacient> getAllPacienti();

    Optional<Pacient> getPacientById(Integer id);

    Pacient savePacient(Pacient pacient);

    Optional<Pacient> updatePacient(Integer id, Pacient pacientDetalii);

    boolean deletePacient(Integer id);
}
