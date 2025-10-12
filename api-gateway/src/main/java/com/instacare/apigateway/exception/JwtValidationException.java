package com.instacare.apigateway.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class JwtValidationException {
    private static final Logger log = LoggerFactory.getLogger(JwtValidationException.class);

    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public Mono<Void> handleUnauthorizedException(ServerWebExchange exchange) {
        log.error("Unauthorized Exception : {}", exchange.getResponse().getStatusCode());
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
