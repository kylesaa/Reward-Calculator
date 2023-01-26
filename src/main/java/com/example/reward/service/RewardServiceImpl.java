package com.example.reward.service;

import com.example.reward.dao.CustomerRepository;
import com.example.reward.dao.TransactionRepository;
import com.example.reward.dto.MonthlyReward;
import com.example.reward.dto.RewardResult;
import com.example.reward.entity.Customer;
import com.example.reward.entity.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;

    RewardServiceImpl(TransactionRepository transactionRepository, CustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public RewardResult calculateRewards(LocalDateTime start, LocalDateTime end, Long customerId){

        List<Transaction> transactions = transactionRepository.findAllByDatetimeBetweenAndCustomerId(start, end, customerId);

        Map<String, Integer> monthlyRewardsMap = new HashMap<>();
        Integer totalReward = 0;

        for(Transaction transaction : transactions){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            String transactionMonthAndYear = transaction.getDatetime().format(formatter);
            Integer reward = calculateTransactionReward(transaction);
            totalReward += reward;

            monthlyRewardsMap.compute(transactionMonthAndYear ,(k, v) -> {
                if(v == null){
                    return reward;
                }else {
                    return v + reward;
                }
            });
        }

        List<MonthlyReward> monthlyRewardList = monthlyRewardsMap.entrySet().stream()
                .map(entry -> new MonthlyReward(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new RewardResult(monthlyRewardList, totalReward);
    }

    @Override
    @Transactional
    public RewardResult calculateRecentRewards(Long customerId){
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.now().minusMonths(3);

        Customer customer = customerRepository.findById(customerId).orElse(null);

        if(customer != null && customer.getRecentRewardCalculatedTime() != null && start.isBefore(customer.getRecentRewardCalculatedTime())){
            start = customer.getRecentRewardCalculatedTime();
        }

        if(customer == null){
            return null;
        }else {
            customer.setRecentRewardCalculatedTime(end);
            customerRepository.save(customer);
        }

        return calculateRewards(start, end, customerId);
    }


    private Integer calculateTransactionReward(Transaction transaction){
        Integer result = 0;
        Integer transactionValue = transaction.getTransactionValue();
        if(transactionValue > 50){
            result += Math.min(transactionValue - 50, 50) * 1;
            if(transactionValue > 100){
                result += (transactionValue - 100) * 2;
            }
        }
        return result;
    }
}
