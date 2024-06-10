package com.erickfelix.mailsender.dto;

import com.erickfelix.mailsender.model.Nonprofit;

public class NonprofitMapper {
    private NonprofitMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static NonprofitDto toDto(Nonprofit nonProfit, boolean recentlySent) {
        return new NonprofitDto(nonProfit.getId(), nonProfit.getName(), nonProfit.getAddress(), nonProfit.getEmail(), recentlySent);
    }
}
