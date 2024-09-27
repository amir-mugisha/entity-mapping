package org.amali.hms.controller;

import org.amali.hms.dtos.DoctorDto;
import org.amali.hms.model.Doctor;
import org.amali.hms.model.Department;
import org.amali.hms.service.DoctorService;
import org.amali.hms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors() {
        return ResponseEntity.ok().body(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setSurName(doctorDto.getSurName());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setSpeciality(doctorDto.getSpeciality());

        if(doctorDto.getDepartmentId() != null){
            Optional<Department> department = departmentService.getDepartmentById(doctorDto.getDepartmentId());
            doctor.setDepartment(department.orElse(null));
        }


        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.ok().body(createdDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctorDto) {
        Doctor doctorDetails = new Doctor();
        doctorDetails.setSpeciality(doctorDto.getSpeciality());

        if(doctorDto.getDepartmentId() != null){
            Optional<Department> department = departmentService.getDepartmentById(doctorDto.getDepartmentId());
            doctorDetails.setDepartment(department.orElse(null));
        }

        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        return ResponseEntity.ok().body(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().body("Doctor with id "+ id + " deleted successfully");
    }
}