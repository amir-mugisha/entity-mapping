package org.amali.hms.dtos;

import lombok.Getter;
import lombok.Setter;
import org.amali.hms.model.Department;

import java.util.List;

@Getter
@Setter
public class DoctorDto {
    private String firstName;
    private String surName;
    private String  address;
    private String phoneNumber;
    private String speciality;
    private Integer departmentId;
}