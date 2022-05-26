





INSERT INTO Car (carBrand, carModel, dailyPrice) VALUES ('Volvo', 'Böttad 740', 2500),
                                                        ('Saab', '900', 200),
                                                        ('Opel', 'Astra', 1000);

INSERT INTO role (name) values ('ROLE_ADMIN'),
                                ('ROLE_CUSTOMER');

INSERT INTO userAccount (username, password) VALUES ('tomas', '$2a$12$PAHZLoT/tzRKVgd9fWNWweJpFKy0m6z6z1/tLyxaShNffrtushdDG');

INSERT INTO customer (firstName, lastName, address, phoneNumber,ACCOUNTID) VALUES ('tomas','wigell','norr25',0706176004,1);

INSERT INTO UserAccount_roles (UserAccount_accountId , roles_id) VALUES (1,1);

