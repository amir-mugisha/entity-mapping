package org.amali.hms.model;

import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "patients")
public class Patient {

    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String surName;
    private Integer bedNumber;
    private Integer wardId;
}