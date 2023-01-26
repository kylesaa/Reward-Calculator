INSERT INTO customer (id, name) VALUES (1, 'user1');
INSERT INTO customer (id, name) VALUES (2, 'user2');
INSERT INTO customer (id, name) VALUES (3, 'user3');

INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (1, 100, {ts '2023-01-23 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (2, 150, {ts '2023-01-15 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (3, 80, {ts '2022-12-23 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (4, 30, {ts '2022-12-22 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (5, 300, {ts '2022-11-30 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (6, 120, {ts '2022-10-01 16:12:12.12'}, 2);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (7, 90, {ts '2022-08-01 16:12:12.12'}, 2);

INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (8, 190, {ts '2022-07-01 16:12:12.12'}, 3);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (9, 40, {ts '2022-08-01 16:12:12.12'}, 3);
INSERT INTO transaction (id, transaction_value, datetime, customer_id) VALUES (10, 200, {ts '2022-12-01 16:12:12.12'}, 3);
