package com.example.users.controllers;

import com.example.users.entities.User;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    @EntityMapping
    public List<User> user(@Argument List<String> idList) {
        return idList.stream().map(User::getById).toList();
    }

    @QueryMapping
    public List<User> users() {
        return User.getAll();
    }
}