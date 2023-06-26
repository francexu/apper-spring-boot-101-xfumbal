package com.apper;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

// Laboratory Exercise
@Service
public class IdGeneratorService {
    public String generateRandomCharacters(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
