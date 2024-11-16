package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.models.Medic;
import com.database.MedScheduler.repository.MedicRepository;
import com.database.MedScheduler.service.MedicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicServiceImpl implements MedicService {

    private final MedicRepository medicRepository;

    public MedicServiceImpl(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    @Override
    public List<Medic> getAllMedici() {
        return medicRepository.findAll();
    }

    @Override
    public Optional<Medic> getMedicById(Integer id) {
        return medicRepository.findById(id);
    }

    @Override
    public List<Medic> getMediciByNume(String nume) {
        return medicRepository.findByNume(nume);
    }

    @Override
    public Medic saveMedic(Medic medic) {
        return medicRepository.save(medic);
    }

    @Override
    public Optional<Medic> updateMedic(Integer id, Medic medicDetalii) {
        return medicRepository.findById(id).map(medic -> {
            medic.setNume(medicDetalii.getNume());
            medic.setPrenume(medicDetalii.getPrenume());
            medic.setGradProfesional(medicDetalii.getGradProfesional());
            medic.setTelefon(medicDetalii.getTelefon());
            return medicRepository.save(medic);
        });
    }

    @Override
    public boolean deleteMedic(Integer id) {
        if (medicRepository.existsById(id)) {
            medicRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
