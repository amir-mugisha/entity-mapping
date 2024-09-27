package org.amali.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer departmentCode;
    private String name;
    private String building;

    @OneToOne
    private Doctor director;
}
