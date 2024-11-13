package org.richik.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.richik.orderservice.models.OrderLineItems}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto{
    @NotNull
    private String skuCode;
    @NotNull
    private double price;
    @NotNull
    private int quantity;
}