package org.amali.hms.dtos;

import lombok.Getter;
import lombok.Setter;
import org.amali.hms.model.Department;
import org.amali.hms.model.Nurse;

import java.util.List;

@Getter
@Setter
public class NurseDto {
    private String rotation;
    private Float salary;
    private Integer departmentId;
}