package com.example.users.entities;

import java.util.Arrays;
import java.util.List;

public record User(String id, String name, String address) {

    private static final List<User> users = Arrays.asList(
            new User("user-1", "Joshua", "123 Main St"),
            new User("user-2", "Douglas", "456 Maple Ave"),
            new User("user-3", "Bill", "789 Elm S"),
            new User("user-4", "Diana", "101 Oak St")
    );

    public static List<User> getAll() {
        return users;
    }

    public static User getById(String id) {
        return users.stream()
                .filter(user -> user.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}