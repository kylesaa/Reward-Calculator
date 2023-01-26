package com.example.reward.controller;


import com.example.reward.dto.MonthlyReward;
import com.example.reward.dto.RewardResult;
import com.example.reward.service.RewardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    public void testGetRewardWithTimeRange() throws Exception{
        when(rewardService.calculateRewards(any(),any(),any()))
                .thenReturn(new RewardResult(new ArrayList<>(), 100));
        mockMvc.perform(get("/reward/customer/2?start=2022-07-01T16:12:12.12&end=2023-02-01T16:12:12.12"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRewardWithoutTimeRange() throws Exception{
        when(rewardService.calculateRecentRewards(any()))
                .thenReturn(new RewardResult(new ArrayList<>(), 100));
        mockMvc.perform(get("/reward/customer/2"))
                .andExpect(status().isOk());
    }
}
