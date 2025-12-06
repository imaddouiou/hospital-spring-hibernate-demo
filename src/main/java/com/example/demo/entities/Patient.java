package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;
        private  String nom;
        private LocalDate dateNaissance;
        private boolean malade;
        @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
        private Collection<RendezVous> rendezVous;

    public Patient(String nom, LocalDate dateNaissance, boolean malade, Collection<RendezVous> rendezVous) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;

        this.rendezVous = rendezVous;
    }


    public Patient(String nom, LocalDate dateNaissance, boolean malade) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
    }

    public Patient(Long id, String nom, LocalDate dateNaissance, boolean malade) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
    }
}
