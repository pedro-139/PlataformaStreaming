package servicio;

import java.net.URI;
import java.net.http.*;
import org.json.JSONObject;
import excepcion.DatosNoEncontradosException;
import modelo.Pelicula;

public class ConsultaPeliculasOMDb {

    private static final String API_KEY = "a701ac4f";  

    public static Pelicula consultarPelicula(String titulo) throws Exception , DatosNoEncontradosException {
        Pelicula p = new Pelicula();
    	try {
    		if (titulo == null || titulo.isBlank())
    			return null;
    		String nuevoTitulo = titulo.trim().replace(" ", "+");
    		String url = "https://www.omdbapi.com/?t=" + nuevoTitulo + "&apikey=" + API_KEY;

    		HttpClient client = HttpClient.newHttpClient();
    		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    		HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

    		JSONObject json = new JSONObject(response.body());

    		// SI NO SE ENCONTRÓ LA PELÍCULA
    		if (json.optString("Response").equals("False")) {
    			throw new DatosNoEncontradosException(titulo + " no se encuentra disponible.");
    		}

     	  // Título, Resumen, Año
    		p.setTitulo(json.optString("Title", "Título no disponible"));
    		p.setResumen(json.optString("Plot", "Sin descripción disponible"));       
    		String anio = json.optString("Year", "N/A");
        
    		p.setAnio(Integer.parseInt(anio));
     
    		// Rating
    		String rating = json.optString("imdbRating", "N/A");
    		p.setRating_Promedio(Float.parseFloat(rating));

    		// Poster 
    		p.setPoster(json.optString("Poster", ""));
    	} catch (DatosNoEncontradosException e) {
    	    throw e;
    	} catch (Exception e) {
    	    throw e; 
    	}
    	return p;    
    }
}
