insert into airplane (id, model, "number", number_of_seats) values (1, 'Boing 737', 100, 130);
insert into airplane (id, model, "number", number_of_seats) values (2, 'Boing 747', 101, 147);
insert into airplane (id, model, "number", number_of_seats) values (3, 'Boing 777', 102, 157);
insert into airplane (id, model, "number", number_of_seats) values (4, 'Boing 737', 103, 130);
insert into airplane (id, model, "number", number_of_seats) values (5, 'Boeing 787', 104, 160);
insert into airplane (id, model, "number", number_of_seats) values (6, 'Airbus A320', 105, 180);
insert into airplane (id, model, "number", number_of_seats) values (7, 'Airbus A320', 106, 180);
insert into airplane (id, model, "number", number_of_seats) values (8, 'Airbus A320', 107, 180);
insert into airplane (id, model, "number", number_of_seats) values (9, 'Airbus A318', 108, 105);
insert into airplane (id, model, "number", number_of_seats) values (10, 'Airbus A318', 109, 105);

insert into route (id, city_from, city_to, price) values (1, 'Minsk', 'Vilnius', 120.00);
insert into route (id, city_from, city_to, price) values (2, 'Vilnius', 'Minsk', 100.00);
insert into route (id, city_from, city_to, price) values (3, 'Minsk', 'London', 250.00);
insert into route (id, city_from, city_to, price) values (4, 'London', 'Minsk', 150.00);
insert into route (id, city_from, city_to, price) values (5, 'Moscow', 'London', 200.00);
insert into route (id, city_from, city_to, price) values (6, 'London', 'Moscow', 200.00);
insert into route (id, city_from, city_to, price) values (7, 'Minsk', 'Riga', 120.00);
insert into route (id, city_from, city_to, price) values (8, 'Riga', 'Minsk', 90.00);
insert into route (id, city_from, city_to, price) values (9, 'Minsk', 'Milan', 180.00);
insert into route (id, city_from, city_to, price) values (10, 'Milan', 'Minsk', 150.00);

insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (1, '2021-02-06', '2021-02-06', 130, 120.00, 1, 1);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (2, '2021-02-07', '2021-02-07', 147, 100.00, 2, 2);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (3, '2021-02-08', '2021-02-08', 157, 250.00, 3, 3);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (4, '2021-02-09', '2021-02-09', 130, 150.00, 4, 4);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (5, '2021-02-10', '2021-02-10', 160, 200.00, 5, 5);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (6, '2021-02-11', '2021-02-11', 180, 200.00, 6, 6);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (7, '2021-02-12', '2021-02-12', 180, 120.00, 7, 7);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (8, '2021-02-13', '2021-02-13', 180, 90.00, 8, 8);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (9, '2021-02-14', '2021-02-14', 105, 180.00, 9, 9);
insert into schedule (id, arrival, departure, places_available, price_schedule, airplane_id, route_id) values (10, '2021-02-15', '2021-02-15', 105, 150.00, 10, 10);

insert into user_info (id, email, first_name, last_name, login, role) values (5, 'sas@sas.ru', 'Sasha', 'Sasha', 'sasha', 1);
insert into user_info (id, email, first_name, last_name, login, role) values (6, 'alex@sas.ru', 'Alex', 'Alex', 'alex', 1);
insert into user_info (id, email, first_name, last_name, login, role) values (7, 'max@sas.ru', 'Max', 'Max', 'max', 1);
insert into user_info (id, email, first_name, last_name, login, role) values (8, 'anton@sas.ru', 'Anton', 'Anton', 'anton', 1);
insert into user_info (id, email, first_name, last_name, login, role) values (9, 'anna@sas.ru', 'Anna', 'Anna', 'anna', 1);
insert into user_info (id, email, first_name, last_name, login, role) values (10, 'admin1@sas.ru', 'Sasha', 'Sasha', 'admin1', 0);
insert into user_info (id, email, first_name, last_name, login, role) values (11, 'admin2@sas.ru', 'Alex', 'Alex', 'admin2', 0);
insert into user_info (id, email, first_name, last_name, login, role) values (12, 'admin3@sas.ru', 'Max', 'Max', 'admin3', 0);
insert into user_info (id, email, first_name, last_name, login, role) values (13, 'admin4@sas.ru', 'Anton', 'Anton', 'admin4', 0);
insert into user_info (id, email, first_name, last_name, login, role) values (14, 'admin5@sas.ru', 'Anna', 'Anna', 'admin5', 0);

insert into credentials (id, active, create_date, password, user_id) values (5, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 5);
insert into credentials (id, active, create_date, password, user_id) values (6, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 6);
insert into credentials (id, active, create_date, password, user_id) values (7, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 7);
insert into credentials (id, active, create_date, password, user_id) values (8, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 8);
insert into credentials (id, active, create_date, password, user_id) values (9, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 9);
insert into credentials (id, active, create_date, password, user_id) values (10, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 10);
insert into credentials (id, active, create_date, password, user_id) values (11, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 11);
insert into credentials (id, active, create_date, password, user_id) values (12, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 12);
insert into credentials (id, active, create_date, password, user_id) values (13, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 13);
insert into credentials (id, active, create_date, password, user_id) values (14, 'true', '2021-01-06', '$2a$10$.PkC9w/icDwoWNfmrU23Y.CM3Arae94TxvRpdRIkUZaZ8XUTL8vme', 14);

insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (1, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 5, 1);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (2, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 5, 2);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (3, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 5, 2);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (4, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 6, 2);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (5, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 6, 2);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (6, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 6, 3);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (18, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 7, 3);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (7, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 7, 4);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (19, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 8, 4);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (8, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 4);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (9, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 4);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (10, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (11, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (12, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (13, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (14, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (15, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (16, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);
insert into ticket (id, luggage, passenger_first_name, passenger_last_name, passport_id, priority_boarding, priority_registration, purchase_date, total_price, id_user_info, schedule_id)
values (17, 'true', 'Sasha', 'Alex', '125SD85641BLR', 'true', 'true', '2021-01-22', 120, 9, 6);

ALTER SEQUENCE hibernate_sequence RESTART WITH 50;