package org.richik.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.richik.orderservice.Repository.OrderRepository;
import org.richik.orderservice.dto.OrderLineItemsDto;
import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.exceptions.WrongInputException;
import org.richik.orderservice.models.Order;
import org.richik.orderservice.models.OrderLineItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepo;

    @Override
    public Order placeOrder(OrderRequestDto order){
        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  order.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order1.setOrderLineItems(orderLineItems);
        log.info("new Order - {} is saved", order1.getOrderNumber());
        return orderRepo.save(order1);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
