package com.example.demo.web;

import com.example.demo.entities.Patient;
import com.example.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> patientList() {
//        return patientRepository.findAll().stream()
//                .map(p -> new Patient(
//                        p.getId(),
//                        p.getNom(),
//                        p.getDateNaissance(),
//                        p.isMalade()
//                ))
//                .collect(Collectors.toList());
        return patientRepository.findAll();
//        ->boucle infini stack over flow erreur
    }

}
