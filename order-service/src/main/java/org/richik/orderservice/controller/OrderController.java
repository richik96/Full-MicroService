package org.richik.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.richik.orderservice.dto.OrderRequestDto;
import org.richik.orderservice.exceptions.WrongInputException;
import org.richik.orderservice.models.Order;
import org.richik.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(orderService.getAllOrders());
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDto orderRequest) throws WrongInputException {
        if(orderRequest.getOrderLineItemsDtoList().isEmpty()) {
            throw new WrongInputException("Input is empty");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(orderRequest))
                ;
    }
}
