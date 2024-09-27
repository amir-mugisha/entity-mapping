package org.amali.hms.service;

import org.amali.hms.model.Ward;
import org.amali.hms.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;

    public List<Ward> getWards() {
        return wardRepository.findAll();
    }

    public Optional<Ward> getWardById(Integer id) {
        return wardRepository.findById(id);
    }

    public Ward createWard(Ward ward) {
        return wardRepository.save(ward);
    }

    public Ward updateWard(Integer id, Ward wardDetails) {
        Optional<Ward> optionalWard = wardRepository.findById(id);
        if (optionalWard.isPresent()) {
            Ward ward = optionalWard.get();
            ward.setWardNumber(wardDetails.getWardNumber());
            ward.setBeds(wardDetails.getBeds());
            ward.setSupervisor(wardDetails.getSupervisor());

            return wardRepository.save(ward);
        } else {
            throw new RuntimeException("Ward not found with id " + id);
        }
    }

    public void deleteWard(Integer id) {
        wardRepository.deleteById(id);
    }
}