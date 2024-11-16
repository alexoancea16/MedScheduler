package com.database.MedScheduler.models;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // Lombok generează automat getter-ele, setter-ele, equals, hashCode și toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Pacient")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pacient_id")
    private Integer PacientId;

    @Column(name = "Nume", nullable = false) // NOT NULL
    private String nume;

    @Column(name = "Prenume", nullable = false) // NOT NULL
    private String prenume;

    @Column(name = "CNP", nullable = false, unique = true) // NOT NULL și unic
    private String cnp;

    @Column(name = "Data_nasterii", nullable = false) // NOT NULL
    private LocalDate dataNasterii;

    @Column(name = "Sex", nullable = false, length = 1) // NOT NULL, doar 'M' sau 'F'
    private String sex;

    @Column(name = "Telefon", nullable = false) // NOT NULL
    private String telefon;

    @Column(name = "Adresa", nullable = false) // NOT NULL
    private String adresa;

    // Validare pentru sex
    @PrePersist
    @PreUpdate
    private void validateSex() {
        if (!"M".equalsIgnoreCase(sex) && !"F".equalsIgnoreCase(sex)) {
            throw new IllegalArgumentException("Sexul trebuie să fie 'M' sau 'F'");
        }
    }
}
