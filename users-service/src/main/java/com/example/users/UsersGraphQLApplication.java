package com.example.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.federation.FederationSchemaFactory;
import org.springframework.graphql.server.WebGraphQlInterceptor;

import java.util.Map;

@SpringBootApplication
public class UsersGraphQLApplication {
	private static final Logger logger = LoggerFactory.getLogger(UsersGraphQLApplication.class);
	private final ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(UsersGraphQLApplication.class, args);
	}

	@Bean
	public GraphQlSourceBuilderCustomizer customizer(FederationSchemaFactory factory) {
		return builder -> builder.schemaFactory(factory::createGraphQLSchema);
	}

	@Bean
	FederationSchemaFactory federationSchemaFactory() {
		return new FederationSchemaFactory();
	}

	@Bean
	public WebGraphQlInterceptor loggingInterceptor() {
		return (request, chain) -> {
			String query = formatGraphQLQuery(request.getDocument());
			Map<String, Object> variables = request.getVariables();

			logger.info("GraphQL Request:\n{}", query);
			if (variables != null && !variables.isEmpty()) {
				try {
					String prettyVariables = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(variables);
					logger.info("GraphQL Variables:\n{}", prettyVariables);
				} catch (JsonProcessingException e) {
					logger.info("GraphQL Variables: {}", variables);
				}
			}

			return chain.next(request).doOnNext(response -> {
				ExecutionResult executionResult = response.getExecutionResult();
				if (!executionResult.getErrors().isEmpty()) {
					try {
						String prettyErrors = objectMapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(executionResult.getErrors());
						logger.info("GraphQL Errors:\n{}", prettyErrors);
					} catch (JsonProcessingException e) {
						logger.info("GraphQL Errors: {}", executionResult.getErrors());
					}
				} else {
					logger.info("GraphQL execution completed successfully");
				}
			});
		};
	}

	/**
	 * Format the GraphQL query with proper indentation
	 */
	private String formatGraphQLQuery(String query) {
		if (query == null || query.trim().isEmpty()) {
			return "Empty Query";
		}

		// Basic formatting to handle common patterns
		query = query.replaceAll("\\s+", " ")            // Normalize whitespace
				.replaceAll("\\s*\\{\\s*", " {\n  ") // Add newline after opening braces
				.replaceAll("\\s*}\\s*", "\n}")     // Add newline before closing braces
				.replaceAll("\\s*:\\s*", ": ")      // Normalize spacing around colons
				.replaceAll("\\s*,\\s*", ", ")      // Normalize spacing around commas
				.replaceAll("\\s*\\(\\s*", "(")     // Remove spaces around opening parentheses
				.replaceAll("\\s*\\)\\s*", ") ");   // Normalize spaces after closing parentheses

		// Apply proper indentation
		StringBuilder formattedQuery = new StringBuilder();
		String[] lines = query.split("\n");
		int indentLevel = 0;

		for (String line : lines) {
			// Adjust indent level based on braces
			if (line.contains("}")) {
				indentLevel--;
			}

			// Apply indentation
			for (int i = 0; i < indentLevel; i++) {
				formattedQuery.append("  ");
			}

			formattedQuery.append(line).append("\n");

			// Increase indent for next line if this line opens a block
			if (line.contains("{")) {
				indentLevel++;
			}
		}

		return formattedQuery.toString();
	}
}
