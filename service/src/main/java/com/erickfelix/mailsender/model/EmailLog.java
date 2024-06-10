package com.erickfelix.mailsender.model;

public record EmailLog(Long id, String recipient, String subject, String body) {}