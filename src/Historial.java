//Se creó la clase Historial para delegar el manejo de reservas en el hotel

import java.util.ArrayList;

public class Historial {
    private ArrayList<Consulta> consultas;

    public Historial() {
        this.consultas = new ArrayList<>();
    }

    //Se encapsula la lista de consultas.

    public void agregarConsulta(Consulta consulta) {
        if (consulta != null) {
            consultas.add(consulta);
        }
    }

    //Método mostrarConsultas() recorre e imprime cada consulta de forma ordenada.

    public void mostrarReservas() {
        if (consultas.isEmpty()) {
            System.out.println("⚠️ Sin reservas registradas.");
        } else {
            for (Consulta c : consultas) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
    }
}



