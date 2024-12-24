package org.richik.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.richik.orderservice.Repository.OrderRepository;
import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.models.Order;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;
    @InjectMocks                //it automatically injects dependencies if they are available.
    // Here service needs repository object to it takes it and makes itself as mock as well
    OrderService orderService;
    @Test
    public void createOrderAndTest() {
        log.info("testing with mockito");
        OrderRequestDto order = new OrderRequestDto();
        orderService.placeOrder(order);
    }
}