package com.example.orders.controllers;

import com.example.orders.entities.Order;
import com.example.orders.entities.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    @QueryMapping
    public List<Order> orders() {
        return Order.getAll();
    }

    @SchemaMapping
    public User user(Order order) {
        return new User(order.userId());
    }
}


