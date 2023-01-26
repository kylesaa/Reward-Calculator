package com.example.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RewardResult {

    private List<MonthlyReward> monthlyRewards;

    private Integer totalReward;

}
