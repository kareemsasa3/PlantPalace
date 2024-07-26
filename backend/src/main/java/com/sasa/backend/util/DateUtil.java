package com.sasa.backend.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("The date of birth cannot be null");
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
