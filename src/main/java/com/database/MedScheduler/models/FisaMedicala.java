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
@Table(name = "fisa_medicala")
public class FisaMedicala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fisa_id")
    private Integer fisaId;

    @Column(name = "alergii")
    private String alergii;

    @Column(name = "grupa_sange", nullable = false)
    private String grupaSange;

    @Column(name = "greutate")
    private Double greutate;

    @Column(name = "inaltime")
    private Double inaltime;

    @OneToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;
}
