package org.amali.hms.controller;

import org.amali.hms.dtos.WardDto;
import org.amali.hms.model.Department;
import org.amali.hms.model.Nurse;
import org.amali.hms.model.Ward;
import org.amali.hms.service.DepartmentService;
import org.amali.hms.service.NurseService;
import org.amali.hms.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/wards")
public class WardController {

    @Autowired
    private WardService wardService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private NurseService nurseService;

    @GetMapping
    public ResponseEntity<List<Ward>> getWards() {
        return ResponseEntity.ok().body(wardService.getWards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Integer id) {
        Optional<Ward> ward = wardService.getWardById(id);
        return ward.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ward> createWard(@RequestBody WardDto wardDto) {
        Ward ward = new Ward();
        ward.setWardNumber(wardDto.getWardNumber());
        ward.setBeds(wardDto.getBeds());

        if(wardDto.getDepartmentId() != null){
            Optional<Department> department = departmentService.getDepartmentById(wardDto.getDepartmentId());
            ward.setDepartment(department.orElse(null));
        }

        if(wardDto.getSupervisorId() != null){
            Optional<Nurse> nurse = nurseService.getNurseById(wardDto.getSupervisorId());
            ward.setSupervisor(nurse.orElse(null));
        }

        Ward createdWard = wardService.createWard(ward);
        return ResponseEntity.ok().body(createdWard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ward> updateWard(@PathVariable Integer id, @RequestBody WardDto wardDto) {
        Ward wardDetails = new Ward();
        wardDetails.setWardNumber(wardDto.getWardNumber());
        wardDetails.setBeds(wardDto.getBeds());

        if(wardDto.getDepartmentId() != null){
            Optional<Department> department = departmentService.getDepartmentById(wardDto.getDepartmentId());
            wardDetails.setDepartment(department.orElse(null));
        }

        if(wardDto.getSupervisorId() != null){
            Optional<Nurse> nurse = nurseService.getNurseById(wardDto.getSupervisorId());
            wardDetails.setSupervisor(nurse.orElse(null));
        }


        Ward updatedWard = wardService.updateWard(id, wardDetails);
        return ResponseEntity.ok().body(updatedWard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWard(@PathVariable Integer id) {
        wardService.deleteWard(id);
        return ResponseEntity.noContent().build();
    }
}