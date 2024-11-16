package com.database.MedScheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "istoric_prescriptii")
public class IstoricPrescriptii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "istoric_id")
    private Integer istoricId;

    @Column(name = "diagnostic", nullable = false)
    private String diagnostic;

    @Column(name = "cod_diagnostic", nullable = false)
    private String codDiagnostic;

    @Column(name = "prescriptie")
    private String prescriptie;

    @Column(name = "observatii")
    private String observatii;
}
