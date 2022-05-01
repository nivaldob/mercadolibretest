package com.example.demo.infraestructure.drivenadapter;

import com.example.demo.domain.model.Report;
import com.example.demo.domain.model.gateway.ValidatorRepository;
import com.example.demo.infraestructure.drivenadapter.human.HumanData;
import com.example.demo.infraestructure.drivenadapter.human.JPAHumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ValidatorRepositoryAdapter implements ValidatorRepository {

    @Autowired
    private JPAHumanRepository jpaRepository;

    @Override
    public void save(String[] dna, boolean isMutant) {
        HumanData human = new HumanData();
        human.setDna(Arrays.toString(dna));
        human.setIsMutant(isMutant);
        jpaRepository.save(human);
    }

    @Override
    public Report getReport() {
        List<HumanData> humans = jpaRepository.findAll();
        long mutantsCant = humans.stream().filter(HumanData::getIsMutant).count();
        long humansCant = humans.size();
        Float ratio = ((float) mutantsCant / (float) humansCant);
        return Report.builder()
                .mutants(mutantsCant)
                .humans(humansCant)
                .ratio(!ratio.isNaN() ? ratio : 0)
                .build();
    }

    @Override
    public Boolean getResultByDna(String[] dna) {
        Optional<HumanData> humanData = jpaRepository.findByDna(Arrays.toString(dna));
        return humanData.isPresent() ? humanData.get().getIsMutant() : null;
    }
}
