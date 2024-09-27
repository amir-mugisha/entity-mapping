package org.amali.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Doctor extends Employee{
    private String speciality;

    @OneToOne(mappedBy = "director")
    private Department department;
}
