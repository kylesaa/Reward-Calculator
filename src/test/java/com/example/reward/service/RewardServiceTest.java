package com.example.reward.service;

import com.example.reward.dao.CustomerRepository;
import com.example.reward.dao.TransactionRepository;
import com.example.reward.dto.RewardResult;
import com.example.reward.entity.Customer;
import com.example.reward.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RewardServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    RewardServiceImpl rewardService;

    @BeforeEach
    public void setUp() {
        List<Transaction> mockList = new ArrayList<>();
        mockList.add(new Transaction(1, 100, LocalDateTime.now(), new Customer()));
        mockList.add(new Transaction(2, 200, LocalDateTime.now(), new Customer()));
        mockList.add(new Transaction(3, 80, LocalDateTime.now(), new Customer()));

        when(transactionRepository.findAllByDatetimeBetweenAndCustomerId(any(), any(), any()))
                .thenReturn(mockList);
        when(customerRepository.findById(any())).thenReturn(Optional.of(new Customer(1, "user1", LocalDateTime.now().minusMonths(1))));
    }

    @Test
    public void testCalculateRewards(){


        RewardResult rewardResult = rewardService.calculateRewards(LocalDateTime.now(), LocalDateTime.now(), 1L);

        assertEquals(rewardResult.getTotalReward(), 330);
    }

    @Test
    public void testCalculateRecentRewards(){
        RewardResult rewardResult = rewardService.calculateRecentRewards(1L);

        assertEquals(rewardResult.getTotalReward(), 330);
    }
}
