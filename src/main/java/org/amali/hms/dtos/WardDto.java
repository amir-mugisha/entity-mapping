package org.amali.hms.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WardDto {
    private Integer beds;
    private Integer wardNumber;
    private Integer supervisorId;
    private Integer departmentId;
}