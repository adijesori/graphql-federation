package com.example.orders;

import com.example.orders.controllers.MainController;
import com.example.orders.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest({MainController.class, UserController.class})
public class GraphQLTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetUserOrders() {
        this.graphQlTester
				.documentName("userOrders")
                .execute()
                .path("_entities")
                .matchesJson("""
                    [
                       {
                         "userOrders": [
                           {
                             "date": "2024-09-22"
                           },
                           {
                             "date": "2024-09-20"
                           }
                         ]
                       }
                    ]
                """);
    }
}
