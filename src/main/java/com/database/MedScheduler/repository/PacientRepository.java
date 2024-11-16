package com.database.MedScheduler.repository;

import com.database.MedScheduler.models.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository <Pacient, Long> {


}
