package org.amali.hms.service;

import org.amali.hms.model.Doctor;
import org.amali.hms.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Integer id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setSpeciality(doctorDetails.getSpeciality());
            doctor.setDepartment(doctorDetails.getDepartment());
            return doctorRepository.save(doctor);
        } else {
            throw new RuntimeException("Doctor not found with id " + id);
        }
    }

    public void deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }
}