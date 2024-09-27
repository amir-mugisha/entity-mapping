package org.amali.hms.controller;

import org.amali.hms.dtos.DepartmentDto;
import org.amali.hms.model.Department;
import org.amali.hms.model.Doctor;
import org.amali.hms.service.DepartmentService;
import org.amali.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok().body(departmentService.getDepartments());
    }

    @GetMapping("/{id}")
    @Cacheable(value = "department", key = "#id")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = new Department();

        department.setDepartmentCode(departmentDto.getDepartmentCode());
        department.setName(departmentDto.getName());
        department.setBuilding(departmentDto.getBuilding());

        if(departmentDto.getDirector_id() != null){
            Optional<Doctor> director = doctorService.getDoctorById(departmentDto.getDirector_id());
            department.setDirector(director.get());
        }


        Department createdDepartment = departmentService.createDepartment(department);
        return ResponseEntity.ok().body(createdDepartment);
    }

    @PutMapping("/{id}")
    @CachePut(cacheNames = "department", key = "#id")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {

        Department departmentDetails = new Department();

        departmentDetails.setDepartmentCode(departmentDto.getDepartmentCode());
        departmentDetails.setName(departmentDto.getName());
        departmentDetails.setBuilding(departmentDto.getBuilding());

        if(departmentDto.getDirector_id() != null){
            Optional<Doctor> director = doctorService.getDoctorById(departmentDto.getDirector_id());
            departmentDetails.setDirector(director.get());
        }

        Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
        return ResponseEntity.ok().body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "department", key = "#id", beforeInvocation = true)
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().body("department with id " + id + " deleted successfully");
    }

}