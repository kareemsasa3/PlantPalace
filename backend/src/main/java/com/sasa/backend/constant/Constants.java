package com.sasa.backend.constant;

public final class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static final String ADDRESS_NOT_FOUND_MESSAGE = "Address not found with id: ";
    public static final String PATIENT_NOT_FOUND_MESSAGE = "Patient not found with id: ";
    public static final String INVALID_ENUM_VALUE_MESSAGE = "Invalid value: %s for enum: %s";
}
