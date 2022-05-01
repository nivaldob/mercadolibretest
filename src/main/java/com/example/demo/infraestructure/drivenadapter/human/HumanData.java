package com.example.demo.infraestructure.drivenadapter.human;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "humans")
@NoArgsConstructor
public class HumanData {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String dna;

    private Boolean isMutant;

}
