package com.database.MedScheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "zona_specialitate")
public class ZonaSpecialitate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialitate_id")
    private Integer specialitateId;

    @Column(name = "denumire_specialitate", nullable = false)
    private String denumireSpecialitate;

    @Column(name = "numar_cabinete", nullable = false)
    private Integer numarCabinete;

    @Column(name = "sef_departament")
    private String sefDepartament;

    @OneToMany(mappedBy = "zonaSpecialitate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medic> medici;
}
