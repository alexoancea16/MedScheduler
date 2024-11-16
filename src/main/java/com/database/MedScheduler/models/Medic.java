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
@Table(name = "medic")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medic_id")
    private Integer medicId;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "grad_profesional", nullable = false)
    private String gradProfesional;

    @Column(name = "telefon", nullable = false)
    private String telefon;

    @ManyToOne
    @JoinColumn(name = "specialitate_id", nullable = false)
    private ZonaSpecialitate zonaSpecialitate;
}
