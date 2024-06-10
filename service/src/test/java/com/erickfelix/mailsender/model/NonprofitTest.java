package com.erickfelix.mailsender.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NonprofitTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testNonProfitValid() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setName("Test Nonprofit");
        nonprofit.setAddress("123 Test St");
        nonprofit.setEmail("test@example.com");

        Set<ConstraintViolation<Nonprofit>> violations = validator.validate(nonprofit);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNonprofitNameNotBlank() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setName("");
        nonprofit.setAddress("123 Test St");
        nonprofit.setEmail("test@example.com");

        Set<ConstraintViolation<Nonprofit>> violations = validator.validate(nonprofit);
        assertEquals(1, violations.size());

        ConstraintViolation<Nonprofit> violation = violations.iterator().next();
        assertEquals("Name is mandatory", violation.getMessage());
    }

    @Test
    void testNonprofitEmailNotValid() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setName("Test Nonprofit");
        nonprofit.setAddress("123 Test St");
        nonprofit.setEmail("invalid-email");

        Set<ConstraintViolation<Nonprofit>> violations = validator.validate(nonprofit);
        assertEquals(1, violations.size());

        ConstraintViolation<Nonprofit> violation = violations.iterator().next();
        assertEquals("Email should be valid", violation.getMessage());
    }

    @Test
    void testNonprofitAddressNotBlank() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setName("Test Nonprofit");
        nonprofit.setAddress("");
        nonprofit.setEmail("test@example.com");

        Set<ConstraintViolation<Nonprofit>> violations = validator.validate(nonprofit);
        assertEquals(1, violations.size());

        ConstraintViolation<Nonprofit> violation = violations.iterator().next();
        assertEquals("Address is mandatory", violation.getMessage());
    }
}
