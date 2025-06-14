package com.example.Apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.example.Apigateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;// Validates if the route requires authentication

	@Autowired
	private JwtUtil util;// Utility class for JWT operations

	public static class Config {
	}

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			// Check if the request needs authentication
			if (validator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					return handleUnauthorized(exchange.getResponse(), "Missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				try {
					String role = util.extractRolesFromToken(authHeader);
					String requestedPath = exchange.getRequest().getPath().toString();
					String method = exchange.getRequest().getMethod().name();

					if (!isAuthorized(role, requestedPath, method)) {
						return handleUnauthorized(exchange.getResponse(), "Unauthorized access");// Handle unauthorized
																									// access
					}

				} catch (Exception e) {
					return handleUnauthorized(exchange.getResponse(), "Invalid token");// Handle invalid token
				}
			}
			return chain.filter(exchange);
		};
	}

	private boolean isAuthorized(String role, String path, String method) {
		if ("ADMIN".equalsIgnoreCase(role)) {
			return (path.startsWith("/policies") || path.startsWith("/notifications") || path.startsWith("/agents")
					|| path.startsWith("/claims") || path.startsWith("/customers"));
		} else if ("CUSTOMER".equalsIgnoreCase(role)) {
			return (path.startsWith("/policies") || path.startsWith("/claims"))
					&& (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT"));
		} else if ("AGENT".equalsIgnoreCase(role)) {
			return (path.startsWith("/policies") || path.startsWith("/claims"))
					&& (method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("GET"));
		}

		return false;
	}

	private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
		response.setStatusCode(HttpStatus.FORBIDDEN);
		return response.setComplete();
	}
}
