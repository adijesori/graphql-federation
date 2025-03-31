package com.example.orders;

import com.example.orders.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Objects;

@GraphQlTest(MainController.class)
public class GraphQLTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetBookByID() {
        this.graphQlTester
				.documentName("bookDetails")
				.variable("id", "book-1")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "book-1",
                        "name": "Effective Java",
                        "pageCount": 416,
                        "author": {
                          "id": "author-1"
                        }
                    }
                """);
    }

    @Test
    void shouldGetBookAuthor() {
        this.graphQlTester
                .documentName("bookDetails")
                .variable("id", "book-2")
                .execute()
                .path("bookById.author")
                .matchesJson("""
                    {
                        "id": "author-2"
                    }
                """);
    }

    @Test
    void shouldGetBookByIDRequiredParam() {
        this.graphQlTester
                .documentName("bookDetails")
                .execute()
                .errors()
                .expect(error -> Objects.requireNonNull(error.getMessage())
                        .contains("Variable 'id' has coerced Null value for NonNull type 'ID!'"));
    }
}
