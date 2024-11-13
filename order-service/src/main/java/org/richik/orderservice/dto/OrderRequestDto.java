package org.richik.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.richik.orderservice.models.OrderLineItems;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

//    @JsonProperty("orderLineItemsDto") List<OrderLineItemsDto>  orderLineItemsDtoList ;
//    {
//        if(orderLineItemsDtoList != null) {
//            orderLineItemsDtoList = List.copyOf(orderLineItemsDtoList);
//        }
//        this.orderLineItemsDtoList = orderLineItemsDtoList;
//    }
    @NotNull
    private List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();

}
