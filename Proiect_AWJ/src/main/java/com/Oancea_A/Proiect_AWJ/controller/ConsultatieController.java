/*
Oancea Alexandru
Clasa pentru gestionarea cererilor web - Controller pentru pagina de consultatii
Varianta actualizata: 10.1.2025
*/
package com.Oancea_A.Proiect_AWJ.controller;

import com.Oancea_A.Proiect_AWJ.model.Consultatie;
import com.Oancea_A.Proiect_AWJ.servicii.ConsultatieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ConsultatieController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @Autowired
    private ConsultatieService consultatieService;

    @GetMapping("/consultatii")
    public String showConsultatii(@RequestParam(value = "sortBy", required = false) String sortBy, Model model) {
        List<Map<String, Object>> consultatii;

        if ("data".equals(sortBy)) {
            consultatii = consultatieService.getAllConsultatiiSortedBy("data");
        } else if ("specializare".equals(sortBy)) {
            consultatii = consultatieService.getAllConsultatiiSortedBy("specializare_consultatie");
        } else {
            consultatii = consultatieService.getAllConsultatiiWithPacientInfo();
        }

        model.addAttribute("consultatii", consultatii);
        return "consultatii";
    }
    @PostMapping("/consultatii/salveaza")
    public String salveazaConsultatie(
            @RequestParam String cnp,
            @RequestParam String data,
            @RequestParam String ora,
            @RequestParam String specializare,
            @RequestParam String numeMedic,
            @RequestParam int nrSalon,
            Model model) {

        Integer idPacient = consultatieService.findPacientIdByCnp(cnp);
        if (idPacient == null) {
            model.addAttribute("error", "Pacientul cu CNP-ul introdus nu există.");
            return "adaugaConsultatie"; // Reîncarcă formularul cu mesajul de eroare
        }

        boolean hasConsultatieInZiuaRespectiva = consultatieService.hasConsultatieInZiuaRespectiva(idPacient, data);
        if (hasConsultatieInZiuaRespectiva) {
            model.addAttribute("error", "Pacientul are deja o consultație în această zi.");
            return "adaugaConsultatie"; // Reîncarcă formularul cu mesajul de eroare
        }

        consultatieService.salveazaConsultatie(idPacient, LocalDate.parse(data), LocalTime.parse(ora), nrSalon, specializare, numeMedic);
        return "redirect:/consultatii";
    }
    @PostMapping("/consultatii/sterge/{id}")
    public String deleteConsultatie(@PathVariable int id) {
        consultatieService.deleteConsultatieById(id);
        return "redirect:/consultatii";
    }

    @GetMapping("/consultatii/adauga")
    public String showAdaugaConsultatieForm(Model model) {
        return "adaugaConsultatie"; // Numele fișierului HTML
    }

    @GetMapping("/consultatii/cauta")
    public String searchConsultatii(
            @RequestParam("cnp") String cnp,
            @RequestParam("data") String data,
            Model model) {
        try {
            List<Map<String, Object>> consultatii = consultatieService.searchConsultatiiByCnpAndDate(cnp, data);

            if (consultatii.isEmpty()) {
                model.addAttribute("message", "Nu au fost găsite consultații care să corespundă criteriilor introduse.");
            }

            model.addAttribute("consultatii", consultatii);
            model.addAttribute("cnp", cnp);
            model.addAttribute("data", data);
        } catch (Exception e) {
            model.addAttribute("message", "Eroare la cautarea consultatiilor: " + e.getMessage());
        }
        return "consultatii";
    }
}
