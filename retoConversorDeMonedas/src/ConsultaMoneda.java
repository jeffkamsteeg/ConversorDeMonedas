import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase que realiza consultas a la API de ExchangeRate para obtener tasas de conversión de monedas.
 */
public class ConsultaMoneda {
    // Clave de la API para autenticar las solicitudes
    private static final String API_KEY = "9112e3f312c77c560b82293a";

    /**
     * Realiza una consulta a la API de ExchangeRate para obtener las tasas de conversión de una moneda base.
     *
     * @param baseCode El código de la moneda base (por ejemplo, "USD").
     * @return Un objeto Moneda que contiene las tasas de conversión.
     */
    public Moneda buscaMoneda(String baseCode) {
        // Construir la URI para la solicitud de la API
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCode);

        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear una solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el estado de la respuesta
            if (response.statusCode() == 200) {
                // Imprimir la respuesta para depuración
                System.out.println("Respuesta de la API: " + response.body());

                // Convertir la respuesta JSON a un objeto Moneda
                return new Gson().fromJson(response.body(), Moneda.class);
            } else {
                // Lanzar una excepción si no se encontró la moneda
                throw new RuntimeException("No se encontró la moneda. Código de respuesta: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            // Lanzar una excepción en caso de error durante la solicitud
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }
}
