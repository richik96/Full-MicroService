package org.richik.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.exceptions.WrongInputException;
import org.richik.orderservice.models.Order;
import org.richik.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDto orderRequest) throws WrongInputException {
        if(orderRequest.getOrderLineItemsDtoList().isEmpty()) {
            throw new WrongInputException("Input is empty");
        }
        ResponseEntity<Order> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(orderRequest))
                ;
        return response;
    }
}
