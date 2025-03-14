/*
Clasa pentru a mapa Tabela Consultatie din Baza de date
Oancea Constantin-Alexandru
Versiunea: 15.12.2024
*/

package com.Oancea_A.Proiect_AWJ.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultatie {
    private int id;
    private LocalDate data;
    private LocalTime ora;
    private int nrSalon;
    private String specializare;
    private String numeMedic;
    private int idPacient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public int getNrSalon() {
        return nrSalon;
    }

    public void setNrSalon(int nrSalon) {
        this.nrSalon = nrSalon;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setNumeMedic(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }
}

