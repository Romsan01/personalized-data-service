CREATE DATABASE personalized WITH OWNER = myuser ENCODING = 'UTF8' CONNECTION LIMIT = -1;

\c personalized


GRANT USAGE, CREATE ON SCHEMA public TO myuser;

CREATE TABLE Shopper (
    id SERIAL PRIMARY KEY,
    shopperId VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    productId VARCHAR(255) UNIQUE NOT NULL,
    category VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL
);

CREATE TABLE Shelf (
    id SERIAL PRIMARY KEY,
    shopperId INT REFERENCES Shopper(id),
    productId INT REFERENCES Product(id),
    relevancyScore DOUBLE PRECISION NOT NULL,
    UNIQUE(shopperId, productId)
);

GRANT ALL PRIVILEGES ON TABLE Shopper TO myuser;
GRANT ALL PRIVILEGES ON TABLE Product TO myuser;
GRANT ALL PRIVILEGES ON TABLE Shelf TO myuser;


