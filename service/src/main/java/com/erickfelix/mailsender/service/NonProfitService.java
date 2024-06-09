package com.erickfelix.mailsender.service;

import com.erickfelix.mailsender.model.NonProfit;

import java.util.List;

public interface NonProfitService {
    List<NonProfit> getAllNonProfits();

    List<NonProfit> getAllNonProfitsByIds(List<Long> ids);

    NonProfit getNonProfitById(Long id);
    NonProfit createNonProfit(NonProfit nonProfit);
    NonProfit updateNonProfit(Long id, NonProfit nonProfit);
    void deleteNonProfit(Long id);
    Boolean nonProfitExists(Long id);
}
