package thread;
import javax.swing.table.DefaultTableModel;

import modelo.Pelicula;

import java.net.URL;
import java.util.List;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class PosterLoaderTask implements Runnable {
    private List<Pelicula> peliculas;
    private DefaultTableModel model;

    public PosterLoaderTask(List<Pelicula> peliculas, DefaultTableModel model) {
        this.peliculas = peliculas;
        this.model = model;
    }
    

	@SuppressWarnings("deprecation")
	@Override
    public void run() {
    	 URL url;
         Image img;
         ImageIcon no_img;
         Image scaled;
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula p = peliculas.get(i);
            ImageIcon poster;    
            try {
            	url = new URL(p.getPoster());
            	img = ImageIO.read(url);
            	scaled = img.getScaledInstance(120, 180, Image.SCALE_SMOOTH);
                poster = new ImageIcon(scaled);
            } catch (Exception e) {
            	no_img = new ImageIcon("src/recursos/no_image.jpg");
            	img = no_img.getImage();
            	scaled = img.getScaledInstance(120, 180, Image.SCALE_SMOOTH);
                poster = new ImageIcon(scaled);
            }

            final int fila = i;
            final ImageIcon posterFinal = poster;
            model.setValueAt(posterFinal, fila, 0); // Actualiza la columna del póster
        }
    }
}
