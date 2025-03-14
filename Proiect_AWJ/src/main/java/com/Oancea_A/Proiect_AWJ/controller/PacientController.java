/*
Oancea Alexandru
Clasa pentru gestionarea cererilor web - Controller pentru pagina de pacienti
Varianta actualizata: 10.1.2025
*/
package com.Oancea_A.Proiect_AWJ.controller;

import com.Oancea_A.Proiect_AWJ.model.Pacient;
import com.Oancea_A.Proiect_AWJ.servicii.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PacientController {

    @Autowired
    private PacientService pacientService;

    @GetMapping("/pacienti")
    public String showPacientiPage(
            @RequestParam(required = false, defaultValue = "nume") String sortBy,
            Model model) {
        List<Pacient> pacienti = pacientService.getAllPacientiSorted(sortBy);
        model.addAttribute("pacienti", pacienti);
        model.addAttribute("sortBy", sortBy);
        return "pacienti";
    }

    @GetMapping("/pacienti/adauga")
    public String showAddPacientForm() {
        return "adaugaPacient";
    }

    @PostMapping("/pacienti/salveaza")
    public String savePacient(@ModelAttribute Pacient pacient, Model model) {
        if (pacientService.existsByCnp(pacient.getCnp())) {
            model.addAttribute("error", "Pacientul cu acest CNP există deja!");
            return "adaugaPacient";
        }
        pacientService.addPacient(pacient);
        return "redirect:/pacienti";
    }
    @GetMapping("/pacienti/cauta")
    public String searchPacientByCnp(@RequestParam String cnp, Model model) {
        Pacient pacient = pacientService.findByCnp(cnp);
        if (pacient == null) {
            model.addAttribute("error", "Pacientul cu CNP-ul " + cnp + " nu a fost gasit.");
        } else {
            model.addAttribute("pacient", pacient);
        }
        List<Pacient> pacienti = pacientService.getAllPacientiSorted("nume");
        model.addAttribute("pacienti", pacienti);

        return "pacienti";
    }

    @PostMapping("/pacienti/sterge/{id}")
    public String deletePacient(@PathVariable Long id) {
        pacientService.deletePacientById(id);
        return "redirect:/pacienti";
    }

    // Afișare formular de editare
    @GetMapping("/pacienti/editeaza/{id}")
    public String showEditPacientForm(@PathVariable Long id, Model model) {
        Pacient pacient = pacientService.findById(id);
        if (pacient == null) {
            model.addAttribute("error", "Pacientul nu a fost găsit!");
            return "redirect:/pacienti";
        }
        model.addAttribute("pacient", pacient);
        return "editarePacient";
    }

    // Salvare modificări
    @PostMapping("/pacienti/editeaza/salveaza")
    public String saveEditedPacient(@RequestParam Long id,
                                    @RequestParam String nume,
                                    @RequestParam String prenume,
                                    @RequestParam String telefon,
                                    @RequestParam String email,
                                    Model model) {
        pacientService.updatePacient(id, nume, prenume, telefon, email);
        model.addAttribute("message", "Modificările au fost salvate cu succes!");
        return "redirect:/pacienti";
    }

    @PostMapping("/pacienti/confirmare")
    public String confirmAction(@RequestParam String message, @RequestParam String confirmUrl, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("confirmUrl", confirmUrl);
        return "confirmare";
    }

}
