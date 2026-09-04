package vista;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class RegistroVista extends JFrame {
		private JLabel lblEmail;
		private JLabel lblContrasenia;
		private JLabel lblNombre;
		private JLabel lblApellido;
		private JLabel lblDNI;
		private JLabel lblnombreUsuario;
		
		private JTextField txtNombre;
		private JTextField txtApellido;
		private JTextField txtDNI;
		private JTextField txtNombreUsuario;
		private JTextField txtEmail;
		private JPasswordField txtContrasenia; //Implementamos esta variable para que el texto en la contraseña no sea visible.
		private JButton btnRegistro;
		private JButton btnVolver;

		public  RegistroVista() {
			this.setTitle("Plataforma de Streaming - Registro");
			this.setSize(500, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
			ImageIcon image = new ImageIcon ("src/recursos/Plataforma de Streaming.png");
		 	this.setIconImage(image.getImage());
			
			// Panel Principal
			JPanel panel = new JPanel (new GridLayout(6, 2, 10,10) );
			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interno
			
			
			//
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
			
			lblApellido = new JLabel("Apellido:");
			lblApellido.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
			
			lblDNI = new JLabel("DNI:");
			lblDNI.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
			
			lblnombreUsuario = new JLabel("Nombre de usuario:");
			lblnombreUsuario.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
			
			lblEmail = new JLabel("Email:"); 
			lblEmail.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto
			
			lblContrasenia = new JLabel("Contraseña:");
			lblContrasenia.setFont(new Font("Arial", Font.BOLD, 16)); 
			
			
			txtEmail = new JTextField();
			txtContrasenia = new JPasswordField();
			txtNombre = new JTextField();
			txtApellido = new JTextField();
			txtDNI = new JTextField(); //DEBE SER UNICO
			txtNombreUsuario = new JTextField();
			
	
			panel.add(lblNombre);			panel.add(txtNombre); 	
			panel.add(lblApellido);			panel.add(txtApellido);	
			panel.add(lblDNI);				panel.add(txtDNI);		
			panel.add(lblnombreUsuario);	panel.add(txtNombreUsuario);	
			panel.add(lblEmail);			panel.add(txtEmail);
			panel.add(lblContrasenia);		panel.add(txtContrasenia);
			
			// BOTON
			
			btnRegistro = new JButton("Registrarte");

			btnRegistro.setForeground(Color.WHITE);
			btnRegistro.setBackground(new Color(66, 183, 42)); 
			btnRegistro.setFocusPainted(false); 
			
			btnVolver = new JButton("¿Ya tienes una cuenta?");
			btnVolver.setBorderPainted(false);
			btnVolver.setContentAreaFilled(false);
			btnVolver.setFocusPainted(false);
			btnVolver.setForeground(new Color(70,130,180));
			btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 16));
			btnVolver.setMaximumSize(new Dimension(250, 20));	
			
			JPanel panelBoton = new JPanel();
			panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.Y_AXIS));
			panelBoton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80)); 
			panelBoton.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
			
			btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panelBoton.add(btnRegistro);
			panelBoton.add(Box.createVerticalStrut(10));
			panelBoton.add(btnVolver);
			
			this.add(panel,BorderLayout.CENTER);
			this.add(panelBoton, BorderLayout.SOUTH);
			
			setVisible(true);
		}

		public String getTxtNombre() {
			return txtNombre.getText();
		}
		public String getTxtApellido() {
			return txtApellido.getText();
		}
		public String getTxtDNI() {
			return txtDNI.getText();
		}
		public String getTxtNombreUsuario() {
			return txtNombreUsuario.getText();
		}
		public String getTxtEmail() {
			return txtEmail.getText();
		}
		public String getTxtContrasenia() {
			return new String(txtContrasenia.getPassword()); 
		}
		public JButton getBtnRegistro() {
			return btnRegistro;
		}
		public JButton getBtnVolver() {
			return btnVolver;
		}
		public void mostrarMensajeError(String mensaje) {
			JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}

		public void mostrarMensaje(String string, String string2, int informationMessage) {
			 JOptionPane.showMessageDialog(this, string, string2, informationMessage);
		}
}
