package org.richik.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_Order_Line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems extends Basemodel{

    private String skuCode;
    private Double price;
    private Integer quantity;
}
