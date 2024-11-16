package com.database.MedScheduler.service;

import com.database.MedScheduler.models.Medic;

import java.util.List;
import java.util.Optional;

public interface MedicService {

    List<Medic> getAllMedici();

    Optional<Medic> getMedicById(Integer id);

    List<Medic> getMediciByNume(String nume);

    Medic saveMedic(Medic medic);

    Optional<Medic> updateMedic(Integer id, Medic medicDetalii);

    boolean deleteMedic(Integer id);
}
