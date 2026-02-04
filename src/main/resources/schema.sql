-- Drops and recreates the Products table.

DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
  Product_Id   BIGINT PRIMARY KEY AUTO_INCREMENT,
  Product_Name VARCHAR(255) NOT NULL,
  Description  VARCHAR(255),
  Price        DECIMAL(10,2) NOT NULL,
  Stock        INT NOT NULL
);