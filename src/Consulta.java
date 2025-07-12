import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consulta {
    private String codigo;  // Ahora es String, generado automáticamente

    public Consulta() {
        this.codigo = IDGenerator.generarCodigoConsulta();
    }

    public void mostrarConsulta() {
    }
}
