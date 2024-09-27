package org.amali.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Nurse extends Employee{
    private String rotation;
    private Float salary;

    @ManyToOne
    @JoinColumn( name = "department_id")
    private Department department;
}
