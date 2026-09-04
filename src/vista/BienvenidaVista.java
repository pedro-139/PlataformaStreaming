package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import render.*;
import thread.*;

import javax.swing.table.*;

import modelo.*;

@SuppressWarnings("serial")

public class BienvenidaVista extends JFrame {

    private JLabel imagen;
    private JPanel panelPrincipal;
    private JLabel datosCliente;
    private JButton btnCerrarSesion;
    private JTextField buscar;
    private JButton btnBuscar;
    private JTable tabla;

    
    public BienvenidaVista() {
    	
    	// CONFIGURACION JFRAME PRINCIPAL
        this.setTitle("Plataforma de Streaming - Bienvenida"); // Título ventana
        this.setSize(1500, 800); // Tamaño ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); // Centrar ventana
        
        ImageIcon image2 = new ImageIcon("src/recursos/Plataforma de Streaming.png"); // Ícono de la aplicación
        this.setIconImage(image2.getImage()); // Establecer ícono

        
        panelPrincipal = new JPanel(new BorderLayout()); 

        this.add(panelPrincipal); // Agregar panel principal al JFrame

        
        
        // CONFIGURACION IMAGEN DE CARGA
        ImageIcon image = new ImageIcon("src/recursos/loading_icon.gif");
        imagen = new JLabel();
        imagen.setText("Cargando...");
        imagen.setFont(new Font("Arial", Font.BOLD, 20));
        imagen.setIcon(image);
        imagen.setSize(new Dimension(100, 200));
        imagen.setHorizontalAlignment(JLabel.CENTER);
        imagen.setVerticalAlignment(JLabel.CENTER);
        imagen.setHorizontalTextPosition(JLabel.CENTER);
        imagen.setVerticalTextPosition(JLabel.TOP);
        imagen.setIconTextGap(20);
        
        
        //COMPONENTES
        
        datosCliente = new JLabel(""); //Se inicializa vacio y lo actualiza el controlador
        btnCerrarSesion = new JButton("Cerrar Sesion");
        buscar = new JTextField(25);
        btnBuscar = new JButton();
        tabla = new JTable();

        // CONFIGURACION BOTONES

        btnBuscar.setBackground(new Color(70,130,180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);

        btnCerrarSesion.setForeground (Color.WHITE);
        btnCerrarSesion.setBackground (new Color(70,130,180));
        btnCerrarSesion.setFocusPainted(false); 
    }



    public void mostrarCarga() {
        panelPrincipal.removeAll();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.add(imagen, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
        this.setVisible(true);
    }

    public void ocultarCarga() {
        panelPrincipal.removeAll();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    public void mostrarContenidoPrincipal(List<Pelicula> peliculas, Cliente c) {

        panelPrincipal.removeAll();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // PANEL SUPERIOR
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.WHITE);
        
        
        //BIENVENIDA
        JLabel bienvenida = new JLabel("Bienvenido a la Plataforma de Streaming");
        bienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        bienvenida.setHorizontalAlignment(SwingConstants.LEFT);
        
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30,80,0,0)); // Márgenes

        bienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        panelTitulo.add(bienvenida);
        
        // DEJA RESEÑA
        JLabel resenia = new JLabel("Seguro viste alguna de estas películas, haznos saber que te parecio dejando tu reseña");
        resenia.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel panelTexto = new JPanel();
        panelTexto.setBackground(Color.WHITE);
        panelTexto.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0)); 
        panelTexto.add(resenia);


        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new BoxLayout(panelUsuario, BoxLayout.Y_AXIS));
        panelUsuario.setBackground(Color.WHITE);

        // Nombre y boton alineados a la derecha
        datosCliente.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnCerrarSesion.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        
        panelUsuario.add(datosCliente);
        panelUsuario.add(btnCerrarSesion);

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.setBackground(Color.WHITE);
        ImageIcon im = new ImageIcon("src/recursos/Icono_busqueda.PNG");
        Image img = im.getImage();
        Image scaled = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // icono chico
        ImageIcon iconoChico = new ImageIcon(scaled);

        btnBuscar.setIcon(iconoChico);

        panelBuscar.add(buscar);
        panelBuscar.add(btnBuscar);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

        JPanel contenedorDerecha = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        contenedorDerecha.setBackground(Color.WHITE);
        contenedorDerecha.add(panelUsuario);
        
        panelDerecho.add(contenedorDerecha);
        panelDerecho.add(panelBuscar);

        panelSuperior.add(panelTitulo, BorderLayout.WEST);
        panelSuperior.add(panelDerecho, BorderLayout.EAST);

		 
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS)); 
        panelNorte.setBackground(Color.WHITE);
        
        panelNorte.add(panelSuperior);
        panelNorte.add(panelTexto);
        
        
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        
        
        
        // TABLA
        String[] columnas = { "Poster", "Título", "Género", "Resumen", "" };
        //Se crea el modelo personalizado para la tabla se le pasan las columnas y null como datos iniciales
        ComponenteTablaBienvenida model = new ComponenteTablaBienvenida (null,columnas);
        tabla.setModel(model);
        //CONFIGURAR TAMAÑO DEL BOTON CALIFICAR
        tabla.getColumnModel().getColumn(4).setMinWidth(130);
        tabla.getColumnModel().getColumn(4).setMaxWidth(130);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(130);
         
       
        tabla.setRowHeight(160); // Altura de las filas para los posters
        tabla.setShowGrid(false); // Sin líneas en las celdas
        tabla.setIntercellSpacing(new Dimension(0,0)); // Sin espacio entre celdas
        
     // Cambiar estilo del header del JTable
        JTableHeader header = tabla.getTableHeader(); // Obtener el header de la tabla
        header.setFont(new Font("Arial", Font.BOLD, 18)); // Fuente del header

        

        ImageIcon placeholder = new ImageIcon("src/recursos/no_image.jpg");

        //SE CARGAN SOLO LAS PRIMERAS 10 PELICULAS EN LA TABLA
        int limite = Math.min(10, peliculas.size());
        for (int i = 0; i < limite; i++) {
            Pelicula p = peliculas.get(i);
            model.addRow(new Object[]{
            		placeholder,
            		p.getTitulo(),
            		p.getGenero().toString(),
            		"<html><body style='margin:0; padding:0;'>" + p.getResumen() + "</body></html>",
            		"Calificar"});
        }
        
        
        tabla.getColumn("").setCellRenderer(new ButtonRender());
        tabla.getColumn("Poster").setCellRenderer(new PosterRender());
        
        //PERMITE EL ORDENAMIENTO DE LAS COLUMNAS DE LA TABLA POR TITULO O GENERO.
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);
        sorter.setSortable(0, false); // Poster NO
        sorter.setSortable(1, true);  // Título SÍ
        sorter.setSortable(2, true);  // Género SÍ
        sorter.setSortable(3, false); // Resumen NO
        sorter.setSortable(4, false); // Botón NO
        

        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        //Carga los posters en la tabla
        PosterLoaderTask task = new PosterLoaderTask(peliculas, model);
        Thread t = new Thread(task, "PosterLoaderTask");
        t.start();


        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    
    public void mostrarMensajeError(String string) {
        JOptionPane.showMessageDialog(this, string, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeInfo(String string) {
        JOptionPane.showMessageDialog(this, string, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setNombreCliente(String nombre, String apellido) {
        datosCliente.setText(nombre + " " + apellido);
    }


    public JButton getBtnCerrarSesion() { 
    	return btnCerrarSesion; 
    	}
    public JTextField getBuscar() {
    	return buscar; 
    	}
    public JButton getBtnBuscar() { 
    	return btnBuscar; 
    	}
    public JTable getTabla() {
    	return tabla;
    }



	public TableModel getModeloTabla() {
		return tabla.getModel();
	}

}