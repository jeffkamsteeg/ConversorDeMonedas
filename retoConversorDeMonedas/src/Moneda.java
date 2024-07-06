import java.util.Map;

/**
 * Registro que representa una moneda y sus tasas de conversión.
 *
 * @param base_code        El código de la moneda base (por ejemplo, "USD").
 * @param conversion_rates Un mapa que contiene las tasas de conversión de la moneda base a otras monedas.
 */
public record Moneda(String base_code, Map<String, Double> conversion_rates) {
}
