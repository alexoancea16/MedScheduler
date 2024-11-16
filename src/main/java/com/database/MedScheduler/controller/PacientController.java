package com.database.MedScheduler.controller;

import com.database.MedScheduler.models.Pacient;
import com.database.MedScheduler.service.PacientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacienti")
public class PacientController {

    private final PacientService pacientService;

    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @GetMapping
    public ResponseEntity<List<Pacient>> getAllPacienti() {
        return ResponseEntity.ok(pacientService.getAllPacienti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacient> getPacientById(@PathVariable Integer id) {
        return pacientService.getPacientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pacient> createPacient(@RequestBody Pacient pacient) {
        return ResponseEntity.ok(pacientService.savePacient(pacient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacient> updatePacient(@PathVariable Integer id, @RequestBody Pacient pacient) {
        return pacientService.updatePacient(id, pacient)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Integer id) {
        if (pacientService.deletePacient(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
