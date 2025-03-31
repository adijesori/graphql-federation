package com.example.users;

import com.example.users.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(MainController.class)
public class GraphQLTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetAuthorByID() {
        this.graphQlTester
				.documentName("authorDetails")
				.variable("id", "author-1")
                .execute()
                .path("authorById")
                .matchesJson("""
                    {
                        "id": "author-1",
                        "firstName": "Joshua",
                        "lastName": "Bloch"
                    }
                """);
    }
}
