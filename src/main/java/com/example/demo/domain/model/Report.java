package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {

    private long mutants;
    private long humans;
    private float ratio;

}
