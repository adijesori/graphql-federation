package com.example.users.controllers;

import com.example.users.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @EntityMapping
    public List<User> user(@Argument List<String> idList) {
        logger.info("@EntityMapping (User) called with idList = {}", idList);

        return idList.stream().map(User::getById).toList();
    }

    @QueryMapping
    public List<User> users() {
        return User.getAll();
    }
}