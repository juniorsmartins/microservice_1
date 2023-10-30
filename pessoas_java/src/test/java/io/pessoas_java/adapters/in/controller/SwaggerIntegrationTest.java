package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.configs.AbstractIntegrationTest;
import io.pessoas_java.configs.TestConfigs;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    @Order(1)
    @DisplayName("Swagger - Get")
    public void testarDisplaySwaggerUiPage() {

        var content = RestAssured.given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfigs.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();
        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}

