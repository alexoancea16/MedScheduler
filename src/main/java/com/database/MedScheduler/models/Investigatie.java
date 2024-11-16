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
@Table(name = "investigatie")
public class Investigatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investigatie_id")
    private Integer investigatieId;

    @ManyToOne
    @JoinColumn(name = "serviciu_id", nullable = false)
    private ServiciuMedical serviciuMedical;

    @ManyToOne
    @JoinColumn(name = "set_id", nullable = false)
    private SetAnalize setAnalize;

    @Column(name = "obligativitate", nullable = false)
    private String obligativitate;

    @Column(name = "observatii")
    private String observatii;
}

