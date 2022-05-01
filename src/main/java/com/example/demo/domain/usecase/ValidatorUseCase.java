package com.example.demo.domain.usecase;

import com.example.demo.domain.model.Report;
import com.example.demo.domain.model.gateway.ValidatorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class ValidatorUseCase {

    private static final int MAX_REPETITIONS = 3;
    private static final int MINIMUN_SEQUENCE = 1;

    private ValidatorRepository repository;

    public boolean isMutant(String[] dna) {
        Boolean result = repository.getResultByDna(dna);
        if (result != null) {
            return result;
        }
        result = validate(dna);
        repository.save(dna, result);
        return result;
    }

    private boolean validate(String[] dna) {
        int sequenceCants = 0;
        for (int j = 0; j < dna.length; j++) {
            for (int i = 0; i < dna[j].length(); i++) {
                if (hasPosibilites(i, j, dna)) {
                    char current = dna[j].charAt(i);
                    if (searchX(i, j, current, dna)) {
                        sequenceCants++;
                        if (sequenceCants > MINIMUN_SEQUENCE) {
                            return true;
                        }
                        continue;
                    }
                    if (searchY(i, j, current, dna)) {
                        sequenceCants++;
                        if (sequenceCants > MINIMUN_SEQUENCE) {
                            return true;
                        }
                        continue;
                    }
                    if (searchRigthDown(i, j, current, dna)) {
                        sequenceCants++;
                        if (sequenceCants > MINIMUN_SEQUENCE) {
                            return true;
                        }
                        continue;
                    }

                    if (searchLeftDown(i, j, current, dna)) {
                        sequenceCants++;
                        if (sequenceCants > MINIMUN_SEQUENCE) {
                            return true;
                        }
                        continue;
                    }

                }
            }
        }
        return false;
    }

    private boolean hasPosibilites(int x, int y, String[] dna) {
        return x < (dna[0].length() - MAX_REPETITIONS) || y < (dna.length - MAX_REPETITIONS);
    }

    private boolean searchX(int i, int j, char current, String[] dna) {
        int repetitions = 0;
        for (int x = i + 1; x < dna[i].length(); x++) {
            if (current == dna[j].charAt(x)) {
                repetitions++;
                if (repetitions == MAX_REPETITIONS) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private boolean searchY(int i, int j, char current, String[] dna) {
        int repetitions = 0;
        for (int y = j + 1; y < dna.length; y++) {
            if (current == dna[y].charAt(i)) {
                repetitions++;
                if (repetitions == MAX_REPETITIONS) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private boolean searchRigthDown(int i, int j, char current, String[] dna) {
        int repetitions = 0;
        int x = i + 1;
        int y = j + 1;
        while ((x >= 0 && x < dna[0].length()) && (y >= 0 && y < dna.length)) {
            if (current == dna[y].charAt(x)) {
                repetitions++;
                if (repetitions == MAX_REPETITIONS) {
                    return true;
                }
                x++;
                y++;
            } else {
                break;
            }
        }
        return false;
    }

    private boolean searchLeftDown(int i, int j, char current, String[] dna) {
        int repetitions = 0;
        int x = i - 1;
        int y = j + 1;
        while ((x >= 0 && x < dna[0].length()) && (y >= 0 && y < dna.length)) {
            if (current == dna[y].charAt(x)) {
                repetitions++;
                if (repetitions == MAX_REPETITIONS) {
                    return true;
                }
                x--;
                y++;
            } else {
                break;
            }
        }
        return false;
    }

    public Report getReport() {
        return repository.getReport();
    }

}
