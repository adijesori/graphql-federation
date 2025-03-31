package com.example.users.controllers;

import com.example.users.entities.Order;
import com.example.users.entities.User;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersController {
    @EntityMapping
    public Order order(@Argument String id, @Argument String userId) {
        return new Order(id, userId);
    }

    @SchemaMapping
    public User user(Order order) {
        return User.getUserById(order.userId());
    }
}

