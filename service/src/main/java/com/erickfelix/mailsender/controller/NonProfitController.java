package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.dto.NonprofitDto;
import com.erickfelix.mailsender.dto.NonprofitMapper;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.service.EmailService;
import com.erickfelix.mailsender.service.NonprofitService;
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
    private final EmailService emailService;

    public NonProfitController(NonprofitService nonProfitService,
                               EmailService emailService) {
        this.nonProfitService = nonProfitService;
        this.emailService = emailService;
    }

     @GetMapping
     public List<NonprofitDto> getAllNonProfits() {
         var nonprofits = nonProfitService.getAllNonprofits();
         return nonprofits.stream()
                 .map(nonprofit -> {
                    var recentlySent = emailService.isRecentlySent(nonprofit.getId());
                    return NonprofitMapper.toDto(nonprofit, recentlySent);
                 }).toList();
     }

     @GetMapping("/{id}")
     public NonprofitDto getNonProfitById(@PathVariable Long id) {
         var nonprofit = nonProfitService.getNonprofitById(id);
         return NonprofitMapper.toDto(nonprofit, emailService.isRecentlySent(id));
     }

     @PostMapping
     public Nonprofit createNonProfit(@RequestBody Nonprofit nonProfit) {
         return nonProfitService.createNonprofit(nonProfit);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Nonprofit> updateNonProfit(@PathVariable Long id, @RequestBody Nonprofit nonProfit) {
         boolean nonProfitExists = nonProfitService.nonprofitExists(id);
         Nonprofit updatedNonprofit = nonProfitService.updateNonprofit(id, nonProfit);

         if (nonProfitExists) {
             return ResponseEntity.ok(updatedNonprofit);
         } else {
             return new ResponseEntity<>(updatedNonprofit, HttpStatus.CREATED);
         }
     }

     @DeleteMapping("/{id}")
     public void deleteNonProfit(@PathVariable Long id) {
         nonProfitService.deleteNonprofit(id);
     }
}
