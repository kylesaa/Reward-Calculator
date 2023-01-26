package com.example.reward.service;

import com.example.reward.dto.RewardResult;

import java.time.LocalDateTime;

public interface RewardService {
    RewardResult calculateRewards(LocalDateTime start, LocalDateTime end, Long customerId);

    RewardResult calculateRecentRewards(Long customerId);
}
