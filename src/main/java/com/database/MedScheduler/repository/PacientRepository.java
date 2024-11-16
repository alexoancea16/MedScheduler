package com.database.MedScheduler.repository;

import com.database.MedScheduler.models.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacientRepository extends JpaRepository<Pacient, Integer> {
    Optional<Pacient> findByCnp(String cnp);
}

