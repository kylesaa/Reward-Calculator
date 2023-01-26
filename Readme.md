# Reward calculator
A microservice applicaition that calculate rewards.

#API Examples

localhost:8080/reward/2?start=2022-07-01T16:12:12.12&end=2023-02-01T16:12:12.12

{
"monthlyRewards": [
{
"monthAndYear": "2022-10",
"reward": 90
},
{
"monthAndYear": "2022-11",
"reward": 450
},
{
"monthAndYear": "2023-01",
"reward": 200
},
{
"monthAndYear": "2022-12",
"reward": 30
},
{
"monthAndYear": "2022-08",
"reward": 40
}
],
"totalReward": 810
}

localhost:8080/reward/2

{
"monthlyRewards": [
{
"monthAndYear": "2022-11",
"reward": 450
},
{
"monthAndYear": "2023-01",
"reward": 200
},
{
"monthAndYear": "2022-12",
"reward": 30
}
],
"totalReward": 680
}

if start and end time are not provided. The api will return the rewards of recent 3 months and set the time of now as calculated. Next time when request rewards of recent 3 months, calculated transactions won't be count in.