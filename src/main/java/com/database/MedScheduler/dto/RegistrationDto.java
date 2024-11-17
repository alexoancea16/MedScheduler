package com.database.MedScheduler.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegistrationDto {

    // Câmpuri pentru utilizator
    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    // Câmpuri pentru tabelul Pacient
    @NotEmpty
    private String nume;

    @NotEmpty
    private String prenume;

    @NotEmpty
    private String cnp;

    @NotEmpty
    private String dataNasterii; // Reprezentată ca String pentru a fi convertită ulterior în LocalDate

    @NotEmpty
    private String sex; // Valori permise: "M", "F"

    @NotEmpty
    private String telefon;

    @NotEmpty
    private String adresa;

    // Câmpuri pentru tabelul FisaMedicala
    private String alergii; // Poate fi gol (opțional)

    private String grupaSange; // Exemplu: "A+", "B-", etc. (poate fi gol)

    private Double greutate; // Exemplu: 70.5 kg (opțional)

    private Double inaltime; // Exemplu: 1.75 m (opțional)

    // Getters și Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getAlergii() {
        return alergii;
    }

    public void setAlergii(String alergii) {
        this.alergii = alergii;
    }

    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
    }

    public Double getGreutate() {
        return greutate;
    }

    public void setGreutate(Double greutate) {
        this.greutate = greutate;
    }

    public Double getInaltime() {
        return inaltime;
    }

    public void setInaltime(Double inaltime) {
        this.inaltime = inaltime;
    }
}
