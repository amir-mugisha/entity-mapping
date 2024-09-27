package org.amali.hms.service;

import org.amali.hms.model.Department;
import org.amali.hms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Department updateDepartment(Integer id, Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setDepartmentCode(departmentDetails.getDepartmentCode());
            department.setName(departmentDetails.getName());
            department.setBuilding(departmentDetails.getBuilding());
            department.setDirector(departmentDetails.getDirector());
            return departmentRepository.save(department);
        } else {
            throw new RuntimeException("Department not found with id " + id);
        }
    }

    @Transactional
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}