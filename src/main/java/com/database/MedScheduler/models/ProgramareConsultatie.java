package com.database.MedScheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "programare_consultatie")
public class ProgramareConsultatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programare_id")
    private Integer programareId;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "serviciu_id", nullable = false)
    private ServiciuMedical serviciuMedical;

    @Column(name = "data_efectuare", nullable = false)
    private LocalDate dataEfectuare;

    @Column(name = "ora_efectuare", nullable = false)
    private LocalTime oraEfectuare;

    @Column(name = "asistenta_prm", nullable = false)
    private String asistentaPrm;

    @Column(name = "status_programare", nullable = false)
    private String statusProgramare;

    @ManyToOne
    @JoinColumn(name = "istoric_id", nullable = false)
    private IstoricPrescriptii istoricPrescriptii;
}
