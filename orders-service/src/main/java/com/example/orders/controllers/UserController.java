package com.example.orders.controllers;

import com.example.orders.entities.Order;
import com.example.orders.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @EntityMapping
    public User user(@Argument String id) {
        logger.info("@EntityMapping (User) called with userId = {}", id);
        return new User(id);
    }

    @SchemaMapping
    public List<Order> userOrders(User user) {
        return Order.getUserOrders(user.id());
    }
}

