// No prueba un metodo suelto: recibe el POST, valida con @valid y responde 201 o 400.
package com.ejemplo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void debeResponder400CuandoFaltaElNombre() throws Exception {

        // Cliente HTTP de Java
        HttpClient client = HttpClient.newHttpClient();

        // Petición sin el campo nombre
        String json = """
                {
                    "email": "julian@example.com"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/usuarios"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Enviar petición y recibir respuesta
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        // Comprobar que el servidor responde 400
        assertEquals(400, response.statusCode());
    }
}
