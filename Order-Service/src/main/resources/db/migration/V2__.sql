CREATE TABLE flyway_schema_history
(
    installed_rank INT                     NOT NULL,
    version        VARCHAR(50) NULL,
    `description`  VARCHAR(200)            NOT NULL,
    type           VARCHAR(20)             NOT NULL,
    script         VARCHAR(1000)           NOT NULL,
    checksum       INT NULL,
    installed_by   VARCHAR(100)            NOT NULL,
    installed_on   timestamp DEFAULT NOW() NOT NULL,
    execution_time INT                     NOT NULL,
    success        TINYINT(1)              NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (installed_rank)
);

CREATE TABLE t_order_line_items
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    price DOUBLE NULL,
    quantity INT NULL,
    sku_code VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_orders
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    order_number VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_orders_order_line_items
(
    order_id            BIGINT NOT NULL,
    order_line_items_id BIGINT NOT NULL
);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT UKdr0mag64ltmnuqo6c11iiln6o UNIQUE (order_line_items_id);

CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history (success);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT FKak6ywh7578tmaap0ru1vr85id FOREIGN KEY (order_id) REFERENCES t_orders (id) ON DELETE NO ACTION;

CREATE INDEX FKak6ywh7578tmaap0ru1vr85id ON t_orders_order_line_items (order_id);

ALTER TABLE t_orders_order_line_items
    ADD CONSTRAINT FKq6xv0tik4jv3vp7rgli0ea4ev FOREIGN KEY (order_line_items_id) REFERENCES t_order_line_items (id) ON DELETE NO ACTION;