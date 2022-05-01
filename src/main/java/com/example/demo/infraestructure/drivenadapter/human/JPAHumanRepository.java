package com.example.demo.infraestructure.drivenadapter.human;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPAHumanRepository extends JpaRepository<HumanData, Long> {

    Optional<HumanData> findByDna(String dna);

    List<HumanData> findAll();

}
