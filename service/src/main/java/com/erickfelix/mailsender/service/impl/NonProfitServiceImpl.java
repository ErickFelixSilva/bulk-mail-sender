package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.infra.NonProfitNotFoundException;
import com.erickfelix.mailsender.model.NonProfit;
import com.erickfelix.mailsender.repository.NonProfitRepository;
import com.erickfelix.mailsender.service.NonProfitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NonProfitServiceImpl implements NonProfitService {

    private final NonProfitRepository nonProfitRepository;

    public NonProfitServiceImpl(NonProfitRepository nonProfitRepository) {
        this.nonProfitRepository = nonProfitRepository;
    }

    @Override
    public List<NonProfit> getAllNonProfits() {
        return nonProfitRepository.findAll();
    }

    @Override
    public NonProfit getNonProfitById(Long id) {
        return nonProfitRepository.findById(id).orElseThrow(() -> new NonProfitNotFoundException("Nonprofit not found"));
    }

    @Override
    public NonProfit createNonProfit(NonProfit nonProfit) {
        return nonProfitRepository.save(nonProfit);
    }

    @Override
    public NonProfit updateNonProfit(Long id, NonProfit nonProfit) {
        return nonProfitRepository.findById(id).map(existingNonProfit -> {
            existingNonProfit.setName(nonProfit.getName());
            existingNonProfit.setEmail(nonProfit.getEmail());
            existingNonProfit.setAddress(nonProfit.getAddress());
            return nonProfitRepository.save(existingNonProfit);
        }).orElseGet(() -> {
            nonProfit.setId(id);
            return nonProfitRepository.save(nonProfit);
        });
    }

    @Override
    public void deleteNonProfit(Long id) {
        NonProfit existingNonProfit = nonProfitRepository.findById(id)
                .orElseThrow(() -> new NonProfitNotFoundException("Nonprofit not found"));

        nonProfitRepository.delete(existingNonProfit);
    }

    @Override
    public Boolean nonProfitExists(Long id) {
        return nonProfitRepository.existsById(id);
    }
}
