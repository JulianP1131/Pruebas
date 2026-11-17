// Manda la peticion al servidor y muestra el resultado en verde o en rojo
async function enviarUsuario(datos) {
    const mensaje = document.getElementById("mensaje");

    try {
        const response = await fetch("/api/usuarios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos)
        });

        // Leer la respuesta del servidor
        const texto = await response.text();

        let resultado = {};

        try {
            resultado = texto ? JSON.parse(texto) : {};
        } catch {
            resultado = {};
        }

        // Capturar errores HTTP
        if (!response.ok) {
            const errorServidor =
                resultado.message ||
                resultado.detail ||
                resultado.errors?.[0]?.defaultMessage ||
                `Error ${response.status}: ${response.statusText}`;

            throw new Error(errorServidor);
        }

        // Capturar respuesta exitosa
        mensaje.textContent =
            "✅ Usuario creado: " + resultado.nombre;

        mensaje.style.color = "green";

        console.log("Usuario creado:", resultado);

    } catch (error) {

        // Mostrar error en el DOM
        mensaje.textContent = "❌ " + " El nombre es obligatorio";
        mensaje.style.color = "red";

        console.error("Error al crear usuario:", error);
    }
}

function enviar() {
    const datos = {};

    const nombre = document.getElementById("nombre").value;
    const email = document.getElementById("email").value;

    if (nombre) datos.nombre = nombre;
    if (email) datos.email = email;

    enviarUsuario(datos);
}
