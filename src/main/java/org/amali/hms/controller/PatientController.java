package org.amali.hms.controller;

import org.amali.hms.dtos.PatientDto;
import org.amali.hms.model.Patient;
import org.amali.hms.model.Ward;
import org.amali.hms.service.PatientService;
import org.amali.hms.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private WardService wardService;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setSurName(patientDto.getSurName());
        patient.setBedNumber(patientDto.getBedNumber());
        patient.setWardId(patientDto.getWardId());

        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.ok().body(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody PatientDto patientDto) {
        Patient patientDetails = new Patient();
        patientDetails.setFirstName(patientDto.getFirstName());
        patientDetails.setSurName(patientDto.getSurName());
        patientDetails.setBedNumber(patientDto.getBedNumber());

        patientDetails.setWardId(patientDto.getWardId());

        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}