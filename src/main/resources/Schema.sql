
/*
CREATE TABLE user_account
(
    account_id       INT NOT NULL,
    username         VARCHAR(255),
    password         VARCHAR(255),
    email            VARCHAR(255),
    order_car_id     VARCHAR(255),
    order_account_id VARCHAR(255),
    CONSTRAINT pk_useraccount PRIMARY KEY (account_id)
);



CREATE TABLE car
(
    car_id           INT NOT NULL,
    order_car_id     VARCHAR(255),
    order_account_id VARCHAR(255),
    CONSTRAINT pk_car PRIMARY KEY (car_id)
);

CREATE TABLE customer
(
    id           INT AUTO_INCREMENT NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    address      VARCHAR(255),
    phone_number INT,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE role
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

*/