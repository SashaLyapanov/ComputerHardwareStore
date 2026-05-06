-----------------------------------------------------------------------------------------------------
-----CREATE TABLES-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------

CREATE TABLE desktops (
    id VARCHAR(36) PRIMARY KEY,
    serial_number BIGINT NOT NULL,
    manufacturer VARCHAR(300) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    form_factor VARCHAR(30) NOT NULL,

    CONSTRAINT check_price_positive CHECK ( price > 0 ),
    CONSTRAINT check_quantity_non_negative CHECK ( stock_quantity >= 0 ),
    CONSTRAINT check_form_factor CHECK ( form_factor IN ('DESKTOP_TYPE', 'MONOBLOCK_TYPE', 'NETTOP_TYPE') )
);

CREATE TABLE hard_drives (
    id VARCHAR(36) PRIMARY KEY,
    serial_number BIGINT NOT NULL,
    manufacturer VARCHAR(300) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    volume INT NOT NULL,

    CONSTRAINT check_price_positive CHECK ( price > 0 ),
    CONSTRAINT check_quantity_non_negative CHECK ( stock_quantity >= 0 ),
    CONSTRAINT check_volume_positive CHECK ( volume > 0 )
);

CREATE TABLE laptops (
    id VARCHAR(36) PRIMARY KEY,
    serial_number BIGINT NOT NULL,
    manufacturer VARCHAR(300) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    size VARCHAR(15) NOT NULL,

    CONSTRAINT check_price_positive CHECK ( price > 0 ),
    CONSTRAINT check_quantity_non_negative CHECK ( stock_quantity >= 0 ),
    CONSTRAINT check_size CHECK ( size IN ('INCH_13', 'INCH_14', 'INCH_15', 'INCH_17') )
);

CREATE TABLE screens (
    id VARCHAR(36) PRIMARY KEY,
    serial_number BIGINT NOT NULL,
    manufacturer VARCHAR(300) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    diagonal FLOAT NOT NULL,

    CONSTRAINT check_price_positive CHECK ( price > 0 ),
    CONSTRAINT check_quantity_non_negative CHECK ( stock_quantity >= 0 ),
    CONSTRAINT check_diagonal_positive CHECK ( diagonal > 0 )
);

-----------------------------------------------------------------------------------------------------
-----INSERT SOME VALUES------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------
INSERT INTO desktops(id, serial_number, manufacturer, price, stock_quantity, form_factor)
VALUES ('8a68b568-826c-4217-aac0-61afd86fc2ee', 123, 'Macintosh', 63050.90, 15, 'DESKTOP_TYPE');
INSERT INTO desktops(id, serial_number, manufacturer, price, stock_quantity, form_factor)
VALUES ('cc7e5f2d-81d4-425b-b1d0-406b70d5d6e0', 123, 'Lenovo', 90000.90, 44, 'MONOBLOCK_TYPE');

INSERT INTO hard_drives(id, serial_number, manufacturer, price, stock_quantity, volume)
VALUES ('f71534c3-e231-40fe-861a-9d208b128e0a', 903032, 'Samsung', 12990, 10, 1024);
INSERT INTO hard_drives(id, serial_number, manufacturer, price, stock_quantity, volume)
VALUES ('7538353a-75e0-4fed-851e-c20d74f8f855', 90303221, 'Kingston', 14800, 4, 1024);

INSERT INTO laptops(id, serial_number, manufacturer, price, stock_quantity, size)
VALUES ('cf05f410-d895-4c6e-8b83-c75f5027c0b6', 34234234, 'Xiaomi Redmi', 120000, 8, 'INCH_15');
INSERT INTO laptops(id, serial_number, manufacturer, price, stock_quantity, size)
VALUES ('a3c702c9-953f-4d3a-a740-bc793bfe9aef', 12356642, 'Apple', 150000, 7, 'INCH_15');

INSERT INTO screens (id, serial_number, manufacturer, price, stock_quantity, diagonal)
VALUES ('8b6247fd-7aa5-4f7b-8f71-f4537840c211', 912983, 'Samsung', 32000, 5, 28);
INSERT INTO screens (id, serial_number, manufacturer, price, stock_quantity, diagonal)
VALUES ('cbe32cd2-68ae-40a5-90e5-36c2e7dd91c1', 13284793, 'Xiaomi', 15000, 30, 24);