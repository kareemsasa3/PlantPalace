package com.sasa.backend.constant;

public final class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static final String ADDRESS_NOT_FOUND_MESSAGE = "Address not found with id: ";
    public static final String ROLE_NOT_FOUND_MESSAGE = "Role not found with id: ";
    public static final String INVALID_ENUM_VALUE_MESSAGE = "Invalid value: %s for enum: %s";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with id: ";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found with id: ";
}
