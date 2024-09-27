package org.amali.hms.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String surName;
    private String  address;
    private String phoneNumber;
//    private Integer

}
