import java.util.Scanner;

/**
 * Clase principal que permite al usuario convertir cantidades entre diferentes monedas
 * utilizando la API de ExchangeRate.
 */
public class Principal {
    /**
     * Método principal que ejecuta el programa.
     * Presenta un menú para seleccionar la conversión deseada y la cantidad a convertir.
     *
     * @param args Argumentos de línea de comando (no utilizados).
     */
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        while (true) {
            // Mostrar opciones de menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir USD a COP");
            System.out.println("2. Convertir COP a USD");
            System.out.println("3. Convertir USD a EUR");
            System.out.println("4. Convertir EUR a USD");
            System.out.println("5. Salir");

            // Leer la opción del usuario
            int opcion = lectura.nextInt();
            lectura.nextLine(); // Consumir el salto de línea

            // Salir si el usuario selecciona la opción 5
            if (opcion == 5) {
                System.out.println("Finalizando la aplicación.");
                break;
            }

            // Pedir al usuario la cantidad a convertir
            System.out.println("Ingrese la cantidad que desea convertir:");
            double cantidad = lectura.nextDouble();
            lectura.nextLine(); // Consumir el salto de línea

            // Realizar la conversión según la opción seleccionada
            switch (opcion) {
                case 1:
                    convertirMoneda(consulta, "USD", "COP", cantidad);
                    break;
                case 2:
                    convertirMoneda(consulta, "COP", "USD", cantidad);
                    break;
                case 3:
                    convertirMoneda(consulta, "USD", "EUR", cantidad);
                    break;
                case 4:
                    convertirMoneda(consulta, "EUR", "USD", cantidad);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    /**
     * Convierte una cantidad de una moneda a otra utilizando la API de ExchangeRate.
     *
     * @param consulta Instancia de ConsultaMoneda para realizar la consulta a la API.
     * @param from     Código de la moneda de origen (por ejemplo, "USD").
     * @param to       Código de la moneda de destino (por ejemplo, "COP").
     * @param cantidad Cantidad a convertir.
     */
    private static void convertirMoneda(ConsultaMoneda consulta, String from, String to, double cantidad) {
        try {
            // Realizar la consulta a la API para obtener la tasa de conversión
            Moneda moneda = consulta.buscaMoneda(from);
            Double tasaConversion = moneda.conversion_rates().get(to);

            // Calcular y mostrar el resultado de la conversión
            if (tasaConversion != null) {
                double resultado = cantidad * tasaConversion;
                System.out.printf("%.2f %s = %.2f %s%n", cantidad, from, resultado, to);
            } else {
                System.out.println("No se encontró la tasa de conversión de " + from + " a " + to);
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Finalizando la aplicación.");
        }
    }
}
