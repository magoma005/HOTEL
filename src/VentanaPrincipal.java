import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Reservas> listaReservas = new ArrayList<>();

    public VentanaPrincipal() {
        //CONFIGURACIÓN DE LA VENTANA PRINCIPAL
        setTitle("Hotel: Buen Dia - Sistema de Gestión del Hotel");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Colores
        Color colorFondo = new Color(240, 248, 255);
        getContentPane().setBackground(colorFondo);

        //PANEL IZQUIERDO: JTree de servicios
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servicios");
        raiz.add(new DefaultMutableTreeNode("Habitaciones"));
        raiz.add(new DefaultMutableTreeNode("Nuestro Personal"));
        raiz.add(new DefaultMutableTreeNode("Direccion"));
        raiz.add(new DefaultMutableTreeNode("Precios"));
        raiz.add(new DefaultMutableTreeNode("Reseñas"));

        JTree arbolServicios = new JTree(raiz);
        JScrollPane scrollArbol = new JScrollPane(arbolServicios);
        add(scrollArbol, BorderLayout.WEST);

        //PANEL CENTRAL CON ESCRITORIO
        escritorio = new JDesktopPane();
        escritorio.setBackground(Color.WHITE);
        add(escritorio, BorderLayout.CENTER);

        //PIE DE PÁGINA CON INFORMACIÓN
        JLabel piePagina = new JLabel("© 2025 Hotel: Buen Dia. Todos los derechos reservados.", SwingConstants.CENTER);
        piePagina.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(piePagina, BorderLayout.SOUTH);

        //BARRA DE MENÚ
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemNuevoRegistro = new JMenuItem("Nueva reservacion");
        itemNuevoRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        // Acción salir
        itemSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "👋 Gracias por visitar el Hotel: Buen Dia. ¡Hasta pronto!");
            System.exit(0);
        });
        // Acción nuevo registro
        itemNuevoRegistro.addActionListener(e -> crearFormularioRegistracion());
        menuArchivo.add(itemNuevoRegistro);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        JMenu menuVista = new JMenu("Vista");
        menuVista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemReservaciones = new JMenuItem("Reservaciones");
        itemReservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        // Acción reservaciones
        itemReservaciones.addActionListener(e -> mostrarTablaReservaciones());
        menuVista.add(itemReservaciones);
        barraMenu.add(menuArchivo);
        barraMenu.add(menuVista);
        setJMenuBar(barraMenu);
        setVisible(true);
    }

    // Creacion de un Splash Screen con su logo y carga
    public static void mostrarSplashScreen() {
        JWindow splash = new JWindow();
        // Panel con BorderLayout para texto arriba y logo abajo
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        // Texto de carga
        JLabel lblTexto = new JLabel("Cargando un buen dia...", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(lblTexto, BorderLayout.NORTH);
        ImageIcon icono = new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/logo.jpg"));
        JLabel lblImagen = new JLabel(icono, SwingConstants.CENTER);
        panel.add(lblImagen, BorderLayout.CENTER);
        splash.getContentPane().add(panel);
        splash.setSize(400, 400);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        try {
            Thread.sleep(2500); // duración del splash
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.setVisible(false);
        splash.dispose();
    }

    //FORMULARIO DE REGISTRO
    private void crearFormularioRegistracion() {
        JInternalFrame form = new JInternalFrame("Formulario de reservacion", true, true, true, true);
        form.setSize(500, 300);
        form.setLayout(new GridBagLayout());
        form.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo habitacion
        JLabel lblHabitacion = new JLabel("Numero de Habitacion:");
        lblHabitacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField txtHabitacion = new JTextField();
        txtHabitacion.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(lblHabitacion, gbc);
        gbc.gridx = 1;
        form.add(txtHabitacion, gbc);

        // Fecha
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField txtFecha = new JTextField();
        txtFecha.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblFecha, gbc);
        gbc.gridx = 1;
        form.add(txtFecha, gbc);

        // Noches
        JLabel lblNoches = new JLabel("Noches:");
        lblNoches.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JSpinner spinnerNoches = new JSpinner(new SpinnerNumberModel(1, 0, 500, 1));
        ((JSpinner.DefaultEditor) spinnerNoches.getEditor()).getTextField().setPreferredSize(new Dimension(50, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblNoches, gbc);
        gbc.gridx = 1;
        form.add(spinnerNoches, gbc);

        // Botón reservar
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReservar.setPreferredSize(new Dimension(100, 30));

        btnReservar.addActionListener(e -> {
            String habitacion = txtHabitacion.getText();
            String fecha = txtFecha.getText();
            int noches = (int) spinnerNoches.getValue();

            Reservas r = new Reservas(habitacion, fecha, noches);
            listaReservas.add(r);

            JOptionPane.showMessageDialog(form,
                    " Reservacion registrada:\n" +
                            "Numero Habitacion: " + habitacion +
                            "\nFecha: " + fecha +
                            "\nNoches: " + noches,
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnReservar, gbc);

        escritorio.add(form);
        form.setVisible(true);
    }

    //TABLA DE RESERVACIONES CON PROGRESS BAR
    private void mostrarTablaReservaciones() {
        JInternalFrame frameTabla = new JInternalFrame("Lista de reservaciones", true, true, true, true);
        frameTabla.setSize(600, 350);
        frameTabla.setLayout(new BorderLayout());
        JPanel panelCarga = new JPanel(new BorderLayout());
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        panelCarga.add(barraProgreso, BorderLayout.NORTH);
        frameTabla.add(panelCarga, BorderLayout.CENTER);
        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            int valor = barraProgreso.getValue();
            if (valor < 100) {
                barraProgreso.setValue(valor + 5);
            } else {
                timer.stop();
                String[] columnas = {"Habitacion", "Fecha", "Noches"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                for (Reservas r : listaReservas) {
                    Object[] fila = {r.getHabitacion(), r.getFecha(), r.getNoches()};
                    modelo.addRow(fila);
                }
                JTable tabla = new JTable(modelo);
                tabla.getTableHeader().setBackground(new Color(173, 216, 230));
                tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                JScrollPane scrollTabla = new JScrollPane(tabla);
                frameTabla.getContentPane().removeAll();
                frameTabla.add(scrollTabla, BorderLayout.CENTER);
                frameTabla.revalidate();
                frameTabla.repaint();
            }
        });
        timer.start();
        escritorio.add(frameTabla);
        frameTabla.setVisible(true);
    }

    public static void main(String[] args) {
        // Mostrar splash antes de iniciar app
        mostrarSplashScreen();

        // Lanzar ventana principal
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
