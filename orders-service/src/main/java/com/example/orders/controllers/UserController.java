package com.example.orders.controllers;

import com.example.orders.entities.Order;
import com.example.orders.entities.User;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @EntityMapping
    public User user(@Argument String id) {
        return new User(id);
    }

    @SchemaMapping
    public List<Order> userOrders(User user) {
        return Order.getUserOrders(user.id());
    }
}

