package org.richik.orderservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.richik.orderservice.Repository.OrderRepository;
import org.richik.orderservice.dto.InventoryResponseDto;
import org.richik.orderservice.dto.OrderLineItemsDto;
import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.exceptions.WrongInputException;
import org.richik.orderservice.models.Order;
import org.richik.orderservice.models.OrderLineItems;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepo;
    private final WebClient webClient;

    @Override
    public Order placeOrder(OrderRequestDto order){
        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  order.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order1.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order1.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                        .toList();
        log.info("new Order - {} is saved", order1.getOrderNumber());
        //call inventory service and place order if product is in stock
        InventoryResponseDto[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8085/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();               //webcllient will call a synchronous api
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponseDto::isInStock);

        if(!allProductsInStock){
            throw new IllegalArgumentException("Product is out of stock");
        }
        return orderRepo.save(order1);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }

    @Override
    public List<Order> getAllOrders() {

        try {
            return orderRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
