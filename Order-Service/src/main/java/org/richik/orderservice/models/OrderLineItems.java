package org.richik.orderservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_Order_Line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
