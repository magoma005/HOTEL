public class Reservas {
    private String habitacion;
    private String fecha;
    private int noches;
    private Historial historial;

    public Reservas(String habitacion, String fechaTexto, int noches) {
        setHabitacion(habitacion);
        setFecha(fechaTexto);
        setNoches(noches);
        this.historial = new Historial();
    }

    public void mostrarHistorial() {
        System.out.println("📋 Habitacion: " + habitacion + " | Fecha: " + fecha + " | Noches: " + noches);
        System.out.println("Historial de reservas:");
        historial.mostrarReservas();
    }

    public void setHabitacion(String habitacion) {
        if (habitacion == null || habitacion.isBlank()) {
            throw new IllegalArgumentException("Debes de digitar el numero de habitacion que deseas registrar.");
        }
        this.habitacion = habitacion;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("Debes de digitar una fecha.");
        }
        this.fecha = fecha;
    }

    public void setNoches(int noches) {
        if (noches < 0) {
            throw new IllegalArgumentException("La noche no puede ser negativa.");
        }
        this.noches = noches;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getNoches() {
        return noches;
    }

}