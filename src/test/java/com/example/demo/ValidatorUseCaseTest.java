package com.example.demo;

import com.example.demo.domain.model.Report;
import com.example.demo.domain.model.gateway.ValidatorRepository;
import com.example.demo.domain.usecase.ValidatorUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ValidatorUseCaseTest {

    @Mock
    private ValidatorRepository repository;

    @InjectMocks
    private ValidatorUseCase useCase;

    private final String[] testDna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private final String[] testDnaFail = new String[]{"ATGCXA", "CAGTGC", "TTATGT", "AGAAGG", "CCXCTA", "TCACTG"};

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldValidateHuman() {
        when(repository.getResultByDna(any())).thenReturn(null);
        assertTrue(useCase.isMutant(testDna));
    }

    @Test
    public void shouldNotValidateHuman() {
        when(repository.getResultByDna(any())).thenReturn(null);
        assertFalse(useCase.isMutant(testDnaFail));
    }

    @Test
    public void shouldValidateHumanThatExists() {
        when(repository.getResultByDna(any())).thenReturn(Boolean.TRUE);
        assertTrue(useCase.isMutant(testDna));
    }

    @Test
    public void shouldGetReport() {
        when(repository.getReport()).thenReturn(Report.builder().build());
        assertNotNull(useCase.getReport());
    }


}
