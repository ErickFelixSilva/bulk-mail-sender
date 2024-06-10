package com.erickfelix.mailsender.service;

import com.erickfelix.mailsender.model.Nonprofit;

import java.util.List;

public interface NonprofitService {
    List<Nonprofit> getAllNonprofits();

    List<Nonprofit> getAllNonprofitsByIds(List<Long> ids);

    Nonprofit getNonprofitById(Long id);
    Nonprofit createNonprofit(Nonprofit nonProfit);
    Nonprofit updateNonprofit(Long id, Nonprofit nonProfit);
    void deleteNonprofit(Long id);
    Boolean nonprofitExists(Long id);
}
