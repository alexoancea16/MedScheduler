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
@Table(name = "serviciu_medical")
public class ServiciuMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviciu_id")
    private Integer serviciuId;

    @Column(name = "denumire_serviciu", nullable = false)
    private String denumireServiciu;

    @Column(name = "data_desfasurare", nullable = false)
    private LocalDate dataDesfasurare;

    @Column(name = "ora_desfasurare", nullable = false)
    private LocalTime oraDesfasurare;

    @Column(name = "durata_medie", nullable = false)
    private Integer durataMedie;

    @Column(name = "numar_salon", nullable = false)
    private String numarSalon;

    @Column(name = "pret_serviciu", nullable = false)
    private Float pretServiciu;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "observatii_serviciu")
    private String observatiiServiciu;

    @ManyToOne
    @JoinColumn(name = "specialitate_id", nullable = false)
    private ZonaSpecialitate zonaSpecialitate;
}
