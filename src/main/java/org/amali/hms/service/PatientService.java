package org.amali.hms.service;

import org.amali.hms.model.Patient;
import org.amali.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setFirstName(patientDetails.getFirstName());
            patient.setSurName(patientDetails.getSurName());
            patient.setBedNumber(patientDetails.getBedNumber());
            patient.setWardId(patientDetails.getWardId());

            return patientRepository.save(patient);
        } else {
            throw new RuntimeException("Patient not found with id " + id);
        }
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}