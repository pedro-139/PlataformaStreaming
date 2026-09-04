package vista;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CalificacionVista extends JFrame {

    private JLabel lblTituloPelicula;
    private JTextField txtCalificacion;
    private JTextArea txtComentario;
    private JButton btnGuardar;

    public CalificacionVista() {

        setTitle("Plataforma de Streaming - Calificar Película"); 
        setSize(650, 450); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout()); 
        getContentPane().setBackground(Color.WHITE); 
       
        ImageIcon image2 = new ImageIcon("src/recursos/Plataforma de Streaming.png"); // Ícono de la aplicación
        setIconImage(image2.getImage()); // Establecer ícono

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        lblTituloPelicula = new JLabel("Título de la Película");
        lblTituloPelicula.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 5, 20);
        panel.add(lblTituloPelicula, gbc);

        JLabel lblCalificacion = new JLabel("Calificación (1-10):");
        lblCalificacion.setFont(new Font("Arial", Font.PLAIN, 18));

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 20, 5, 20);
        panel.add(lblCalificacion, gbc);

        txtCalificacion = new JTextField();
        txtCalificacion.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCalificacion.setPreferredSize(new Dimension(200, 30));

        gbc.gridy = 2;
        panel.add(txtCalificacion, gbc);

        JLabel lblComentario = new JLabel("Comentario:");
        lblComentario.setFont(new Font("Arial", Font.PLAIN, 18));

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 20, 5, 20);
        panel.add(lblComentario, gbc);

        txtComentario = new JTextArea(5, 20);
        txtComentario.setFont(new Font("Arial", Font.PLAIN, 16));
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtComentario);

        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 20, 20);
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scroll, gbc);

        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 17));
        btnGuardar.setForeground(Color.WHITE);

        btnGuardar.setBackground(new Color(70,130,180));
        btnGuardar.setOpaque(true);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);

        btnGuardar.setPreferredSize(new Dimension(160, 45));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));



        gbc.gridy = 5;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        add(panel, BorderLayout.CENTER);
    }


    public void mostrarMensajeError(String string) {
        JOptionPane.showMessageDialog(this, string, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeInfo(String string) {
        JOptionPane.showMessageDialog(this, string, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

	public void mostrarDialogoExito(String string, String string2, int informationMessage) {
		 JOptionPane.showMessageDialog(this, string, string2, informationMessage);
	}

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JTextField getTxtCalificacion() {
        return txtCalificacion;
    }

    public JTextArea getTxtComentario() {
        return txtComentario;
    }

    public void setTituloPelicula(String titulo) {
        lblTituloPelicula.setText(titulo);
    }
}
