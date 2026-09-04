package vista;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class BusquedaVista extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblAnio;
    private JTextArea txtResumen;
    private JButton btnCerrar;

    public BusquedaVista() {

        setTitle("Información de la Película");  // TÍTULO VENTANA
        setSize(600, 350); // Tamaño VENTANA
        setLocationRelativeTo(null); // Centrar VENTANA
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana
   
        ImageIcon image2 = new ImageIcon("src/recursos/Plataforma de Streaming.png"); // Ícono de la aplicación
        this.setIconImage(image2.getImage()); // Establecer ícono

        //PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));  
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes
        add(panel); // Agregar panel a la ventana

        //    TÍTULO
        lblTitulo = new JLabel("Título de la Película"); // TÍTULO PELÍCULA
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22)); 
        panel.add(lblTitulo, BorderLayout.NORTH); // Agregar al norte

        //     CENTRO
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        panel.add(centro, BorderLayout.CENTER);

        lblAnio = new JLabel("Año: ");
        lblAnio.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAnio.setAlignmentX(Component.LEFT_ALIGNMENT);
        centro.add(lblAnio);

        centro.add(Box.createVerticalStrut(10));

        JLabel lblResumenTitulo = new JLabel("Resumen:");
        lblResumenTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblResumenTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        centro.add(lblResumenTitulo);

        txtResumen = new JTextArea();
        txtResumen.setEditable(false); 
        txtResumen.setLineWrap(true); 
        txtResumen.setWrapStyleWord(true); 
        txtResumen.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane sp = new JScrollPane(txtResumen);
        sp.setPreferredSize(new Dimension(550, 300));
        sp.setAlignmentX(Component.LEFT_ALIGNMENT);
        centro.add(sp);

        btnCerrar = new JButton("Continuar");
        btnCerrar.setPreferredSize(new Dimension(120, 40));

        btnCerrar.setAlignmentX(CENTER_ALIGNMENT); // Centrar el botón
        btnCerrar.setForeground(Color.WHITE); // Color de fondo
        btnCerrar.setBackground(new Color(70,130,180));  // Azul
        btnCerrar.setFocusPainted(false);
        panel.add(btnCerrar, BorderLayout.SOUTH);
    }

    public JButton getBtnCerrar() { 
    	return btnCerrar; 
    }
    
    public void setTitulo(String t) { 
    	lblTitulo.setText(t);
    }
    
    public void setAnio(String a) { 
    	lblAnio.setText("Año: " + a); 
    }
    
    public void setResumen(String r) {
    	txtResumen.setText(r); 
    }
    
    public void noDisponible(String titulo) {
		 JOptionPane.showMessageDialog(this, titulo, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
    public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
    
    
}