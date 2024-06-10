package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.infra.EmailAlreadyExistsException;
import com.erickfelix.mailsender.infra.NonProfitNotFoundException;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.repository.NonprofitRepository;
import com.erickfelix.mailsender.service.NonprofitService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NonprofitServiceImpl implements NonprofitService {

    private final NonprofitRepository nonprofitRepository;

    public NonprofitServiceImpl(NonprofitRepository nonprofitRepository) {
        this.nonprofitRepository = nonprofitRepository;
    }

    @Override
    public List<Nonprofit> getAllNonprofits() {
        return nonprofitRepository.findAll();
    }

    @Override
    public List<Nonprofit> getAllNonprofitsByIds(List<Long> ids) {
        return nonprofitRepository.findAllById(ids);
    }

    @Override
    public Nonprofit getNonprofitById(Long id) {
        return nonprofitRepository.findById(id).orElseThrow(() -> new NonProfitNotFoundException("Nonprofit not found"));
    }

    @Override
    public Nonprofit createNonprofit(Nonprofit nonprofit) {
        return saveNonprofit(nonprofit);
    }

    @Override
    public Nonprofit updateNonprofit(Long id, Nonprofit nonprofit) {
        return nonprofitRepository.findById(id).map(existingNonprofit -> {
            existingNonprofit.setName(nonprofit.getName());
            existingNonprofit.setEmail(nonprofit.getEmail());
            existingNonprofit.setAddress(nonprofit.getAddress());
            return saveNonprofit(nonprofit);
        }).orElseGet(() -> {
            nonprofit.setId(id);
            return saveNonprofit(nonprofit);
        });
    }

    @Override
    public void deleteNonprofit(Long id) {
        Nonprofit existingNonprofit = nonprofitRepository.findById(id)
                .orElseThrow(() -> new NonProfitNotFoundException("Nonprofit not found"));

        nonprofitRepository.delete(existingNonprofit);
    }

    @Override
    public Boolean nonprofitExists(Long id) {
        return nonprofitRepository.existsById(id);
    }

    @Override
    public void markAsSent(List<Long> ids) {
        nonprofitRepository.markAsSent(ids);
    }

    private Nonprofit saveNonprofit(Nonprofit nonprofit) {
        try {
            return nonprofitRepository.save(nonprofit);
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists: " + nonprofit.getEmail());
        }
    }
}
