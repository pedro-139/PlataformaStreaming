package render;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ButtonRender extends DefaultTableCellRenderer {

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean seleccionado,boolean hasFocus, int fila, int columna) {

        JPanel panel = new JPanel(new GridBagLayout()); 
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);

        JLabel boton = new JLabel("", SwingConstants.CENTER);
        boton.setOpaque(true);
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(70,130,180));
        
        // 
        if (value != null && value.toString().equals("Calificado")) {
            boton.setText("Calificado");
            boton.setBackground(new Color(180,180,180));
            boton.setForeground( new Color(60,60,60)); 
            // borde gris
            boton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(150,150,150), 2),BorderFactory.createEmptyBorder(8, 22, 8, 22)));
            
        } else {
            boton.setText("Calificar");
            boton.setBackground(new Color(70,130,180)); // azul
            boton.setForeground(Color.WHITE);
            // borde azul
            boton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(50,100,150), 2),BorderFactory.createEmptyBorder(8, 22, 8, 22)));
        }
        panel.add(boton); 
        return panel;
    }
}
