package com.example.users.controllers;

import com.example.users.entities.Order;
import com.example.users.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersController {
    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @EntityMapping
    public Order order(@Argument String id, @Argument String userId) {
        logger.info("@EntityMapping (Order) called with orderId = {} and userId = {}", id, userId);
        return new Order(id, userId);
    }

    @SchemaMapping
    public User user(Order order) {
        return User.getUserById(order.userId());
    }
}

