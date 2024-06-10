package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.dto.NonprofitDto;
import com.erickfelix.mailsender.dto.NonprofitMapper;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.service.NonprofitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nonprofits")
public class NonProfitController {

    private final NonprofitService nonProfitService;

    public NonProfitController(NonprofitService nonProfitService) {
        this.nonProfitService = nonProfitService;
    }

     @GetMapping
     public List<NonprofitDto> getAllNonProfits() {
         var nonprofits = nonProfitService.getAllNonprofits();
         return nonprofits.stream().map(NonprofitMapper::toDto).toList();
     }

     @GetMapping("/{id}")
     public NonprofitDto getNonProfitById(@PathVariable Long id) {
         var nonprofit = nonProfitService.getNonprofitById(id);
         return NonprofitMapper.toDto(nonprofit);
     }

     @PostMapping
     public NonprofitDto createNonProfit(@Valid @RequestBody Nonprofit nonProfit) {
         var nonprofit = nonProfitService.createNonprofit(nonProfit);
         return NonprofitMapper.toDto(nonprofit);
    }

     @PutMapping("/{id}")
     public ResponseEntity<NonprofitDto> updateNonProfit(@PathVariable Long id,@Valid @RequestBody Nonprofit nonProfit) {
         boolean nonProfitExists = nonProfitService.nonprofitExists(id);
         Nonprofit updatedNonprofit = nonProfitService.updateNonprofit(id, nonProfit);
         NonprofitDto nonprofitDto = NonprofitMapper.toDto(updatedNonprofit);

         if (nonProfitExists) {
             return ResponseEntity.ok(nonprofitDto);
         } else {
             return new ResponseEntity<>(nonprofitDto, HttpStatus.CREATED);
         }
     }

     @DeleteMapping("/{id}")
     public void deleteNonProfit(@PathVariable Long id) {
         nonProfitService.deleteNonprofit(id);
     }
}
