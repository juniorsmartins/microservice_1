package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.configs.TestConfigs;
import io.pessoas_java.configs.AbstractIntegrationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
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

