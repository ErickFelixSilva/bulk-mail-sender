package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.infra.EmailAlreadyExistsException;
import com.erickfelix.mailsender.infra.NonProfitNotFoundException;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.repository.NonprofitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NonprofitServiceImplTest {

    @Mock
    private NonprofitRepository nonprofitRepository;

    @InjectMocks
    private NonprofitServiceImpl nonprofitService;

    @Test
    void testGetAllNonprofits() {
        Nonprofit nonprofit1 = new Nonprofit();
        Nonprofit nonprofit2 = new Nonprofit();
        when(nonprofitRepository.findAll()).thenReturn(Arrays.asList(nonprofit1, nonprofit2));

        List<Nonprofit> nonprofits = nonprofitService.getAllNonprofits();

        assertEquals(2, nonprofits.size());
        verify(nonprofitRepository, times(1)).findAll();
    }

    @Test
    void testGetNonprofitById() {
        Nonprofit nonprofit = new Nonprofit();
        when(nonprofitRepository.findById(1L)).thenReturn(Optional.of(nonprofit));

        Nonprofit result = nonprofitService.getNonprofitById(1L);

        assertEquals(nonprofit, result);
        verify(nonprofitRepository, times(1)).findById(1L);
    }

    @Test
    void testGetNonprofitByIdNotFound() {
        when(nonprofitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NonProfitNotFoundException.class, () -> nonprofitService.getNonprofitById(1L));
        verify(nonprofitRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateNonprofit() {
        Nonprofit nonprofit = new Nonprofit();
        when(nonprofitRepository.save(nonprofit)).thenReturn(nonprofit);

        Nonprofit result = nonprofitService.createNonprofit(nonprofit);

        assertEquals(nonprofit, result);
        verify(nonprofitRepository, times(1)).save(nonprofit);
    }

    @Test
    void testUpdateNonprofit() {
        Nonprofit existingNonprofit = new Nonprofit();
        existingNonprofit.setId(1L);
        existingNonprofit.setEmail("old@example.com");

        Nonprofit updatedNonprofit = new Nonprofit();
        updatedNonprofit.setId(1L);
        updatedNonprofit.setName("Updated Name");
        updatedNonprofit.setEmail("updated@example.com");
        updatedNonprofit.setAddress("Updated Address");

        when(nonprofitRepository.findById(1L)).thenReturn(Optional.of(existingNonprofit));
        when(nonprofitRepository.save(any(Nonprofit.class))).thenReturn(updatedNonprofit);

        Nonprofit result = nonprofitService.updateNonprofit(1L, updatedNonprofit);

        assertEquals("Updated Name", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("Updated Address", result.getAddress());
        verify(nonprofitRepository, times(1)).findById(1L);
        verify(nonprofitRepository, times(1)).save(existingNonprofit);
    }

    @Test
    void testDeleteNonprofit() {
        Nonprofit existingNonprofit = new Nonprofit();
        existingNonprofit.setId(1L);

        when(nonprofitRepository.findById(1L)).thenReturn(Optional.of(existingNonprofit));
        doNothing().when(nonprofitRepository).delete(existingNonprofit);

        nonprofitService.deleteNonprofit(1L);

        verify(nonprofitRepository, times(1)).findById(1L);
        verify(nonprofitRepository, times(1)).delete(existingNonprofit);
    }

    @Test
    void testSaveNonprofitWithDuplicateEmail() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setEmail("duplicate@example.com");

        when(nonprofitRepository.save(nonprofit)).thenThrow(DataIntegrityViolationException.class);

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            nonprofitService.createNonprofit(nonprofit);
        });

        assertEquals("Email already exists: duplicate@example.com", exception.getMessage());
        verify(nonprofitRepository, times(1)).save(nonprofit);
    }
}
