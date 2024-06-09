package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.model.NonProfit;
import com.erickfelix.mailsender.service.NonProfitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NonProfitController {

    private final NonProfitService nonProfitService;

    public NonProfitController(NonProfitService nonProfitService) {
        this.nonProfitService = nonProfitService;
    }

     @GetMapping
     public List<NonProfit> getAllNonProfits() {
         return nonProfitService.getAllNonProfits();
     }

     @GetMapping("/{id}")
     public NonProfit getNonProfitById(@PathVariable Long id) {
         return nonProfitService.getNonProfitById(id);
     }

     @PostMapping
     public NonProfit createNonProfit(@RequestBody NonProfit nonProfit) {
         return nonProfitService.createNonProfit(nonProfit);
     }

     @PutMapping("/{id}")
     public ResponseEntity<NonProfit> updateNonProfit(@PathVariable Long id, @RequestBody NonProfit nonProfit) {
         boolean nonProfitExists = nonProfitService.nonProfitExists(id);
         NonProfit updatedNonProfit = nonProfitService.updateNonProfit(id, nonProfit);

         if (nonProfitExists) {
             return ResponseEntity.ok(updatedNonProfit);
         } else {
             return new ResponseEntity<>(updatedNonProfit, HttpStatus.CREATED);
         }
     }

     @DeleteMapping("/{id}")
     public void deleteNonProfit(@PathVariable Long id) {
         nonProfitService.deleteNonProfit(id);
     }
}
