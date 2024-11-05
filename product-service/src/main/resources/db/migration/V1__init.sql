CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    is_deleted    BIT(1) NOT NULL,
    name          VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);