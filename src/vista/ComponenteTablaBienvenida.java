package vista;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ComponenteTablaBienvenida extends DefaultTableModel {
	public ComponenteTablaBienvenida (final Object[][] datos, final String[] titulos) {
		super(datos,titulos);
	}
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return ImageIcon.class;   // Poster
            case 1: return String.class;     // Título
            case 2: return String.class;      // le llega el .toString() del ENUM genero
            case 3: return String.class;      // Resumen
            case 4: return String.class;    // "Botón" Calificar
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 4;     
    }
}
