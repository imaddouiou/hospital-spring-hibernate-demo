package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repositories.ConsultationRepository;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RendezVousRepository;
import com.example.demo.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {

        SpringApplication.run(HospitalApplication.class, args);
	}
    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository) {
        return args -> {
            Stream.of("Mohamed", "Hassan", "Najat" )
                    .forEach(name -> {
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(LocalDate.now());
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });
//            patientRepository.save(new Patient("John", LocalDate.now(),false,null));
            Stream.of("ayamne", "Hanane", "Yasmine" )
                    .forEach(name -> {
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        hospitalService.saveMedecin(medecin);
                    });

            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient2=patientRepository.findByNom("Mohamed");

            Medecin medecin=medecinRepository.findByNom("Yasmine");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            rendezVous.setStatus(StatusRDV.PENDING);
            hospitalService.saveRDV(rendezVous);
            //System.out.println(hospitalService.saveRDV(rendezVous).getId());

            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation .....");
            hospitalService.saveConsultation(consultation);
        };
    }

}

// findById(1L) retourne toujours un Optional :
//  - Optional(objet) si l'ID existe
//  - Optional.empty si l'ID n'existe pas (jamais null)
//
// .orElse(x)      -> renvoie l'objet sinon renvoie x (évite NullPointerException)
// .orElseThrow()  -> renvoie l'objet sinon lance l'exception choisie
// .get()          -> renvoie l'objet sinon lance NoSuchElementException (dangereux)

