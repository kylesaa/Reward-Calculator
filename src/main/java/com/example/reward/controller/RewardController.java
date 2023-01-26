package com.example.reward.controller;

import com.example.reward.dto.RewardResult;
import com.example.reward.service.RewardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RewardController {

    private RewardService rewardService;

    RewardController(RewardService rewardService){
        this.rewardService = rewardService;
    }

    @GetMapping("/reward/customer/{customer_id}")
    public ResponseEntity<RewardResult> getRewards(@PathVariable("customer_id") Long customerId,
                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){

        if((start == null && end!= null) || (start != null && end== null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(start == null && end == null){
            return ResponseEntity.ok(rewardService.calculateRecentRewards(customerId));
        }

        return ResponseEntity.ok(rewardService.calculateRewards(start, end, customerId));

    }
}
