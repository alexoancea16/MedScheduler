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
@Table(name = "set_analize")
public class SetAnalize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Integer setId;

    @Column(name = "denumire_set", nullable = false)
    private String denumireSet;

    @Column(name = "tipologie_set", nullable = false)
    private String tipologieSet;

    @Column(name = "durata_medie", nullable = false)
    private Integer durataMedie;

    @Column(name = "pret_set", nullable = false)
    private Float pretSet;
}
