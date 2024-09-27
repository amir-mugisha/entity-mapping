package org.amali.hms.controller;

import org.amali.hms.dtos.NurseDto;
import org.amali.hms.model.Nurse;
import org.amali.hms.model.Department;
import org.amali.hms.service.NurseService;
import org.amali.hms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Nurse>> getNurses() {
        return ResponseEntity.ok().body(nurseService.getNurses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Integer id) {
        Optional<Nurse> nurse = nurseService.getNurseById(id);
        return nurse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nurse> createNurse(@RequestBody NurseDto nurseDto) {
        Nurse nurse = new Nurse();
        nurse.setRotation(nurseDto.getRotation());
        nurse.setSalary(nurseDto.getSalary());

        Optional<Department> department = departmentService.getDepartmentById(nurseDto.getDepartmentId());
        nurse.setDepartment(department.orElse(null));

        Nurse createdNurse = nurseService.createNurse(nurse);
        return ResponseEntity.ok().body(createdNurse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Integer id, @RequestBody NurseDto nurseDto) {
        Nurse nurseDetails = new Nurse();
        nurseDetails.setRotation(nurseDto.getRotation());
        nurseDetails.setSalary(nurseDto.getSalary());

        Optional<Department> department = departmentService.getDepartmentById(nurseDto.getDepartmentId());
        nurseDetails.setDepartment(department.orElse(null));

        Nurse updatedNurse = nurseService.updateNurse(id, nurseDetails);
        return ResponseEntity.ok().body(updatedNurse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Integer id) {
        nurseService.deleteNurse(id);
        return ResponseEntity.noContent().build();
    }
}