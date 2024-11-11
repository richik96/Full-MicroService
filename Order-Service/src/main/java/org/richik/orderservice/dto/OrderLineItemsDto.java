package org.richik.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.richik.orderservice.models.OrderLineItems}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto{
    private String skuCode;
    private Double price;
    private Integer quantity;
}