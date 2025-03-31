package com.example.orders.entities;

import java.util.Arrays;
import java.util.List;

public record Order(String id, String date, String userId, List<String> productIds) {

    private static final List<Order> orders = Arrays.asList(
            new Order("order-1", "2024-09-22", "user-1", List.of("product-1", "product-2")),
            new Order("order-2", "2024-09-21", "user-2", List.of("product-2")),
            new Order("order-3", "2024-09-20", "user-1", List.of("product-3", "product-4")),
            new Order("order-4", "2024-09-19", "user-3", List.of("product-1")),
            new Order("order-5", "2024-09-18", "user-4", List.of("product-2", "product-3"))
    );


    public static List<Order> getAll() {
        return orders;
    }

    public static List<Order> getUserOrders(String userId) {
        return orders.stream()
                .filter(order -> order.userId.equals(userId))
                .toList();
    }
}
