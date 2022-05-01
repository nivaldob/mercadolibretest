package com.example.demo.domain.model.gateway;

import com.example.demo.domain.model.Report;

public interface ValidatorRepository {

    void save(String[] dna, boolean isMutant);

    Report getReport();

    Boolean getResultByDna(String[] dna);

}
