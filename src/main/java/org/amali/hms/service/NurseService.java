package org.amali.hms.service;

import org.amali.hms.model.Nurse;
import org.amali.hms.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    public List<Nurse> getNurses() {
        return nurseRepository.findAll();
    }

    public Optional<Nurse> getNurseById(Integer id) {
        return nurseRepository.findById(id);
    }

    public Nurse createNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse updateNurse(Integer id, Nurse nurseDetails) {
        Optional<Nurse> optionalNurse = nurseRepository.findById(id);
        if (optionalNurse.isPresent()) {
            Nurse nurse = optionalNurse.get();
            nurse.setRotation(nurseDetails.getRotation());
            nurse.setSalary(nurseDetails.getSalary());
            nurse.setDepartment(nurseDetails.getDepartment());
            return nurseRepository.save(nurse);
        } else {
            throw new RuntimeException("Nurse not found with id " + id);
        }
    }

    public void deleteNurse(Integer id) {
        nurseRepository.deleteById(id);
    }
}