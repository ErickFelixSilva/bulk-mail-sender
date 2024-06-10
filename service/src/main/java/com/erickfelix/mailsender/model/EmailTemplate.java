package com.erickfelix.mailsender.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EmailTemplate {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank(message = "Email subject is mandatory")
    @Column(nullable = false)
    private String subject;

    @NotNull
    @NotBlank(message = "Email body is mandatory")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public EmailTemplate(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
