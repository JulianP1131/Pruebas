// Aqui defino la regla con NotBlank, donde defino que el nombre debe ser obligatorio, si no, entonces el servidor responde 400
package com.ejemplo.demo;

import jakarta.validation.constraints.NotBlank;

public record Usuario(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        String email) {
}
