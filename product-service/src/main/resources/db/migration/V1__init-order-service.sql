CREATE TABLE t_order_line_items
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    sku_code VARCHAR(255) NULL,
    price DOUBLE NULL,
    quantity INT NULL,
    CONSTRAINT pk_t_order_line_items PRIMARY KEY (id)
);

CREATE TABLE t_orders
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    order_number VARCHAR(255) NULL,
    CONSTRAINT pk_t_orders PRIMARY KEY (id)
);

CREATE TABLE t_orders_order_line_items
(
    order_id            BIGINT NOT NULL,
    order_line_items_id BIGINT NOT NULL
);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT uc_t_orders_order_line_items_orderlineitems UNIQUE (order_line_items_id);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT fk_tordordlinite_on_order FOREIGN KEY (order_id) REFERENCES t_orders (id);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT fk_tordordlinite_on_order_line_items FOREIGN KEY (order_line_items_id) REFERENCES t_order_line_items (id);