package com.erickfelix.mailsender.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTemplateTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testEmailTemplateValid() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Template subject");
        emailTemplate.setBody("Template body");

        Set<ConstraintViolation<EmailTemplate>> violations = validator.validate(emailTemplate);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmailTemplateSubjectNotEmpty() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("");
        emailTemplate.setBody("Template body");

        Set<ConstraintViolation<EmailTemplate>> violations = validator.validate(emailTemplate);
        assertEquals(1, violations.size());

        ConstraintViolation<EmailTemplate> violation = violations.iterator().next();
        assertEquals("Email subject is mandatory", violation.getMessage());
    }

    @Test
    void testEmailTemplateBodyNotEmpty() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Template subject");
        emailTemplate.setBody("");

        Set<ConstraintViolation<EmailTemplate>> violations = validator.validate(emailTemplate);
        assertEquals(1, violations.size());

        ConstraintViolation<EmailTemplate> violation = violations.iterator().next();
        assertEquals("Email body is mandatory", violation.getMessage());
    }
}
