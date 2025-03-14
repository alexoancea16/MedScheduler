/*
Clasa pentru a mapa Tabela Pacient din Baza de date
Oancea Constantin-Alexandru
Versiunea: 15.12.2024
*/

package com.Oancea_A.Proiect_AWJ.model;

public class Pacient {
    private Long id;
    private String nume;
    private String prenume;
    private String cnp;
    private String telefon;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
