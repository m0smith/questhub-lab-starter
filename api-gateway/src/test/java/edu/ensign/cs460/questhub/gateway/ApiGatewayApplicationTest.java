package edu.ensign.cs460.questhub.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGatewayApplicationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void routesAreConfigured() {
        webTestClient.get().uri("/api/adventurers").exchange()
                .expectStatus().is5xxServerError();
    }
}
