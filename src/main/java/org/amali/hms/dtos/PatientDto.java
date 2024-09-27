package org.amali.hms.dtos;

import lombok.Getter;
import lombok.Setter;
import org.amali.hms.model.Ward;

@Getter
@Setter
public class PatientDto {
    private String firstName;
    private String surName;
    private Integer bedNumber;
    private Integer wardId;
}