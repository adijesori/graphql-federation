package com.example.users.controllers;

import com.example.users.entities.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    @QueryMapping
    public List<User> users() {
        return User.getAll();
    }
}