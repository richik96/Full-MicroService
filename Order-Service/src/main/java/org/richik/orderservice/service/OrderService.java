package org.richik.orderservice.service;

import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.exceptions.WrongInputException;
import org.richik.orderservice.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Order placeOrder(OrderRequestDto order);

}
