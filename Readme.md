# Reward calculator
A microservice application that calculates rewards.

## Description
The project is created using Springboot.
Calculator API is implemented using rest controller and h2 embedded DB.

## Build
Using Maven, run `mvn compile` or `mvn install`

## Run
run `mvn springboot:run`

## API
localhost:8080/reward/customer/2?start=2022-07-01T16:12:12.12&end=2023-02-01T16:12:12.12

```json
{
    "monthlyRewards": [ 
        {"monthAndYear": "2022-10",
        "reward": 90},
        {"monthAndYear": "2022-11",
        "reward": 450},
        {"monthAndYear": "2023-01",
        "reward": 200},
        {"monthAndYear": "2022-12",
        "reward": 30},
        {"monthAndYear": "2022-08", 
        "reward": 40}
        ],
    "totalReward": 810
}
```

localhost:8080/reward/customer/2

When start and end times are not provided, the api will return the rewards of recent 3 months and set the time of now as calculated. Next time when request rewards of recent 3 months, calculated transactions won't be counted in.

```json
{
"monthlyRewards": [
    {"monthAndYear": "2022-11",
    "reward": 450},
    {"monthAndYear": "2023-01",
    "reward": 200},
    {"monthAndYear": "2022-12",
    "reward": 30}
    ],
"totalReward": 680
}
```
