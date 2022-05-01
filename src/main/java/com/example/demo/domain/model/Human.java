package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Human {

    private String[] dna;

    public boolean hasDna() {
        return dna != null && dna.length > 0;
    }

}
