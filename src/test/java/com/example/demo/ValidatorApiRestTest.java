package com.example.demo;

import com.example.demo.domain.model.Human;
import com.example.demo.domain.model.Report;
import com.example.demo.domain.usecase.ValidatorUseCase;
import com.example.demo.infraestructure.entrypoint.ValidatorApiRest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ValidatorApiRestTest {

    @Mock
    private ValidatorUseCase useCase;

    @InjectMocks
    private ValidatorApiRest apiRest;

    private final String[] testDna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldValidateHuman() {
        Human human = new Human();
        human.setDna(testDna);
        when(useCase.isMutant(any())).thenReturn(true);
        ResponseEntity<Object> response = apiRest.isMutant(human);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotValidateHuman() {
        Human human = new Human();
        human.setDna(testDna);
        when(useCase.isMutant(any())).thenReturn(false);
        ResponseEntity<Object> response = apiRest.isMutant(human);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void shouldGetReport() {
        when(useCase.getReport()).thenReturn(Report.builder().build());
        ResponseEntity<Report> response = apiRest.getReport();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


}
