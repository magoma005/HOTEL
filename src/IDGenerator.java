//Se creó la clase IDGenerator para generar códigos automáticos únicos.

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorReservas = 0;

    //Método generarCodigoConsulta() devuelve un código con prefijo 'C' y número incremental.

    public static String generarCodigoConsulta() {
        return "C" + (++contadorConsulta);
    }

    //Método generarCodigoReservas() devuelve un código con prefijo 'r' y número incremental.

    public static String generarCodigoReservas() {
        return "r" + (++contadorReservas);
    }
}
