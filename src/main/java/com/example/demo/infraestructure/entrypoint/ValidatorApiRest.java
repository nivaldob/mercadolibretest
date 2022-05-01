package com.example.demo.infraestructure.entrypoint;

import com.example.demo.domain.model.Human;
import com.example.demo.domain.model.Report;
import com.example.demo.domain.usecase.ValidatorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidatorApiRest {

    @Autowired
    private ValidatorUseCase useCase;

    @PostMapping("/mutant")
    public ResponseEntity isMutant(@RequestBody Human human) {
        if (!human.hasDna()) {
            return ResponseEntity.badRequest().build();
        }
        return useCase.isMutant(human.getDna()) ? ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<Report> getReport() {
        return ResponseEntity.ok(useCase.getReport());
    }

}
