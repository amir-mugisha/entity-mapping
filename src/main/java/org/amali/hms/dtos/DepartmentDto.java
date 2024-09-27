package org.amali.hms.dtos;

import lombok.Getter;
import lombok.Setter;
import org.amali.hms.model.Doctor;


@Getter
@Setter
public class DepartmentDto {
    private String name;
    private String building;
    private Integer departmentCode;
    private Integer director_id;
}