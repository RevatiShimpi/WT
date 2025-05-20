## db
CREATE DATABASE electricity_billing_php;
USE electricity_billing_php;

CREATE TABLE consumer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE billing (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consumer_id INT,
    units INT,
    amount DECIMAL(10,2),
    billing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (consumer_id) REFERENCES consumer(id) ON DELETE CASCADE
);


## backend - frontend

elec-php/
│
├── db.php
├── consumer/
│   ├── create.php
│   ├── read.php
│   ├── update.php
│   └── delete.php
│
├── billing/
│   └── calculate.php
│
└── index.html


## how to run
xampp -> start apache, mysql
mysql -u root -p OR mysql -u root
and execute db script
all files in elec-php
elec-php in htdocs of xampp of c drive
in browser, http://localhost/elec-php/index.html


## postman

1. Create Consumer (POST)
http://localhost/elec-php/consumer/create.php
{ "name": "John Doe", "address": "123 Main Street" }

2. Read All Consumers (GET)
http://localhost/elec-php/consumer/read.php

3. Update Consumer (PUT)
http://localhost/elec-php/consumer/update.php
{ "id": 1, "name": "John Updated", "address": "456 New Address" }

4. Delete Consumer (DELETE)
http://localhost/elec-php/consumer/delete.php
{ "id": 1 }

5. Calculate & Create Bill (POST)
http://localhost/elec-php/billing/create.php
{ "consumer_id": 1, "units": 275 }

6. Read Bills for Particular Consumer (POST)
http://localhost/elec-php/billing/read_by_consumer.php
{ "consumer_id": 1 }
