package com.example.orders.controllers;

import com.example.orders.entities.Order;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    @QueryMapping
    public List<Order> orders() {
        return Order.getAll();
    }
}