package GUI;
import java.awt.event.*; 
import javax.swing.*;
public class ContadorAWT extends JFrame {
 private JTextArea area1, area2;
 private JButton copiar;
 public ContadorAWT() {
	 super("Ejemplo de JTextArea");
	 Box cuadro = Box.createHorizontalBox();
	 String texto = "Del Potro festejó \n"
	 		+ "en la casa de Federer \n";
	 area1 = new JTextArea(texto, 10, 15);
	 cuadro.add(new JScrollPane(area1));
	 copiar = new JButton("COPIAR >>");
	 cuadro.add(copiar);
	 copiar.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent evento){
 area2.setText(area1.getSelectedText());
 }
 });
 area2 = new JTextArea(10, 15);
 area2.setEditable(false);
 cuadro.add(new JScrollPane(area2));
 this.add(cuadro);
 this.setSize(400, 200);
 this.setVisible(true);
 }
 public static void main(String[] args) {
	 ContadorAWT app = new ContadorAWT();
}		
}