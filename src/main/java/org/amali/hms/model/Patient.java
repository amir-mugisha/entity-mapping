package org.amali.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String surName;
    private Integer bedNumber;

    @ManyToOne
    private Ward ward;
}
