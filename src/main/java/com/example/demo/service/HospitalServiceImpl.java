package com.example.demo.service;

import com.example.demo.entities.Consultation;
import com.example.demo.entities.Medecin;
import com.example.demo.entities.Patient;
import com.example.demo.entities.RendezVous;
import com.example.demo.repositories.ConsultationRepository;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
//    injection des independences  @Autowired pour chqu'une
//    injecte par constructeur

    public HospitalServiceImpl(PatientRepository patientRepository, ConsultationRepository consultationRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(   Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
