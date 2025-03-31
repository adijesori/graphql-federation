package com.example.orders;

import com.example.orders.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

@Import(OrdersGraphQLApplication.class)
@GraphQlTest(MainController.class)
public class GraphQLTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetBookByID() {
        this.graphQlTester
				.documentName("userOrders")
				.variable("id", "user-1")
                .execute()
                .path("__entities")
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
