import java.util.ArrayList;
//Se mantuvieron los atributos privados para cumplir con el principio de encapsulamiento
public class Huesped {
    private String nombre;
    private String documento;
    private ArrayList<Reservas> reservas = new ArrayList<>();

    // Usamos setters con validación para centralizar la lógica
    public Huesped(String nombre, String documento) {
        setNombre(nombre);
        setDocumento(documento);
        this.reservas = new ArrayList<>();
    }

    //Se agregaron setters con validación para nombre, documento y teléfono
    //Se agregaron validaciones mínimas de negocio (ej. nombre no vacío, documento y teléfono con mínimo de dígitos).
    // Setter con validación de nombre
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.length() < 5) {
            throw new IllegalArgumentException("Documento inválido, debe tener mínimo 5 caracteres.");
        }
        this.documento = documento;
    }

    public void agregarReservas(Reservas r) {
        if (r != null) {
            reservas.add(r);
        }
    }


    //Método para mostrar la información completa
    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA DEL HUESPED =====");
        System.out.println("\uD83D\uDC64 Huesped: " + nombre);
        System.out.println("\uD83C\uDD94 Documento: " + documento);
        System.out.println();

        for (Reservas r : reservas) {
            r.mostrarHistorial();
        }
    }
}

