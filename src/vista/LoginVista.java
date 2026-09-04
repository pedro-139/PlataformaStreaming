package vista;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginVista extends JFrame {

    private JLabel lblEmail;
    private JLabel lblContrasenia;
    private JLabel lblRegistro;
    private JTextField txtEmail;
    private JPasswordField txtContrasenia;
    private JButton btnLogin;
    private JButton btnRegistro;

    public LoginVista() {

        this.setTitle("Plataforma de Streaming");
        this.setSize(1000, 425);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("src/recursos/Plataforma de Streaming.png");
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());

        // Imagen
        JLabel panelImagen = new JLabel(new ImageIcon("src/recursos/Plataforma de Streaming2.png"));
        panelImagen.setHorizontalAlignment(JLabel.CENTER);
        panelImagen.setVerticalAlignment(JLabel.CENTER);
        panelImagen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelImagen, BorderLayout.WEST);

        // Panel login
        JPanel panelLogin = new JPanel();
        panelLogin.add(Box.createVerticalStrut(30));
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelLogin.setAlignmentY(Component.TOP_ALIGNMENT);
        panelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Título
        JLabel titulo = new JLabel("Iniciar sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        panelLogin.add(titulo);

        panelLogin.add(Box.createVerticalStrut(8));

        // Form , BOX donde estan email y contraseña
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // EMAIL

        lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
        lblEmail.setPreferredSize(new Dimension(120, 30));

        txtEmail = new JTextField(18);
        txtEmail.setPreferredSize(new Dimension(300, 30));


        // Contraseña
        lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(new Font("Arial", Font.BOLD, 20));
        lblContrasenia.setPreferredSize(new Dimension(120, 30));

        txtContrasenia = new JPasswordField(18);
        txtContrasenia.setPreferredSize(new Dimension(300, 30));
        //FILA EMAIL
        JPanel filaEmail = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filaEmail.setOpaque(false);
        filaEmail.setAlignmentY(Component.CENTER_ALIGNMENT);
        filaEmail.add(lblEmail);
        filaEmail.add(txtEmail);
     
        // FILA CONTRASEÑA
        JPanel filaPass = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filaPass.setOpaque(false);
        filaPass.setAlignmentY(Component.CENTER_ALIGNMENT);
        filaPass.add(lblContrasenia);
        filaPass.add(txtContrasenia);

        // ADD
        form.add(filaEmail);
     
        form.add(filaPass);
        
        
        
        
        panelLogin.add(form);

        panelLogin.add(Box.createVerticalStrut(30));

        // Botón login
        btnLogin = new JButton("Ingresar");
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setFocusPainted(false);

        panelLogin.add(btnLogin);
        panelLogin.add(Box.createVerticalStrut(20));

        // Registro
        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        lblRegistro = new JLabel("¿Aún no sos usuario?");
        panelRegistro.add(lblRegistro);

        btnRegistro = new JButton("Crear cuenta nueva");
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setBackground(new Color(66, 183, 42));
        btnRegistro.setFocusPainted(false);
        panelRegistro.add(btnRegistro);

        panelLogin.add(panelRegistro);

        add(panelLogin, BorderLayout.CENTER);

        setVisible(true);
    }

    public String getTxtEmail() {
        return txtEmail.getText();
    }

    public String getTxtContrasenia() {
        return new String(txtContrasenia.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
