package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.models.Pacient;
import com.database.MedScheduler.repository.PacientRepository;
import com.database.MedScheduler.service.PacientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientServiceImpl implements PacientService {

    private final PacientRepository pacientRepository;

    public PacientServiceImpl(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    @Override
    public List<Pacient> getAllPacienti() {
        return pacientRepository.findAll();
    }

    @Override
    public Optional<Pacient> getPacientById(Integer id) {
        return pacientRepository.findById(id);
    }

    @Override
    public Pacient savePacient(Pacient pacient) {
        return pacientRepository.save(pacient);
    }

    @Override
    public Optional<Pacient> updatePacient(Integer id, Pacient pacientDetalii) {
        return pacientRepository.findById(id).map(pacient -> {
            pacient.setNume(pacientDetalii.getNume());
            pacient.setPrenume(pacientDetalii.getPrenume());
            pacient.setCnp(pacientDetalii.getCnp());
            pacient.setDataNasterii(pacientDetalii.getDataNasterii());
            pacient.setSex(pacientDetalii.getSex());
            pacient.setTelefon(pacientDetalii.getTelefon());
            pacient.setAdresa(pacientDetalii.getAdresa());
            return pacientRepository.save(pacient);
        });
    }

    @Override
    public boolean deletePacient(Integer id) {
        if (pacientRepository.existsById(id)) {
            pacientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
