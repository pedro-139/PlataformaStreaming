package render;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class PosterRender extends DefaultTableCellRenderer {

@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean seleccionado,boolean hasFocus, int fila, int columna) {

        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        if (value instanceof ImageIcon) {
            label.setIcon((ImageIcon) value);
        }
        label.setOpaque(true);
        if (seleccionado) {
            label.setBackground(table.getSelectionBackground());
        } else {
            label.setBackground(table.getBackground());
        }

        return label;
    }
}
