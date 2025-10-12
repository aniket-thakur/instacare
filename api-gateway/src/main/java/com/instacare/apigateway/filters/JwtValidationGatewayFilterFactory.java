package com.instacare.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private static final Logger log = LoggerFactory.getLogger(JwtValidationGatewayFilterFactory.class);
    /*
        Using webclient to make  http request
         */
    private final WebClient client;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
                                             @Value("${auth.service.url}") String authServiceUrl) {

        this.client = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    /*
    apply will apply the filter to every request for request lifecycle
     */
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                log.warn("Authorization header not found");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }

            return client.get()
                    .uri("/validate")
                    .header("Authorization", token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };
    }
}
