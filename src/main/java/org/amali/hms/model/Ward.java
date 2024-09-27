package org.amali.hms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ward {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer wardNumber;
    private Integer beds;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;


    @ManyToOne
    private Department department;
}
