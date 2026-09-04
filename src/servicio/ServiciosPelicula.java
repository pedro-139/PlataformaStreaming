package servicio;

import java.io.*;
import java.util.*;
import comparador.*;
import dao.*;
import factoryDAO.FactoryDAO;
import modelo.*;


public class ServiciosPelicula {
	
    private PeliculaDAO peliculaDAO;
    private List<Pelicula> peliculas;
    
    
    public ServiciosPelicula() {
        this.peliculaDAO = FactoryDAO.getPeliculaDAO();
    }
    

	// Inicializa usando DB si existe, sino usando CSV.
    public List<Pelicula> obtenerPeliculas() throws Exception {
    	
        this.peliculas = peliculaDAO.listar();

        if (this.peliculas == null || this.peliculas.isEmpty()) {
            this.peliculas = cargarCSV();
        }
        return this.peliculas;
    }

    
    //Recibe la instancia del cliente y las peliculas y devuelve una lista de peliculas para mostrar en la bienvenida,
    //las peliculas ya estan filtradas y cargadas por el metodo obtenerPeliculas()   
    	public List<Pelicula> obtenerListadoBienvenida(Cliente c , List<Pelicula> peliculas) {

        ReseniaDAO rDAO = FactoryDAO.getReseniaDAO(); 
        
        // verificar si es la primera vez que inicia sesion 
        boolean esPrimeraVez = c.getPrimeraVez();
   
 	   // primera vez: top 10 por rating_promedio

        if (esPrimeraVez) {
        		
        		peliculas.sort(new ComparadorRating_Promedio());
        		c.setPrimeraVez(false); // actualizar estado para futuras llamadas
        		ServiciosCliente.actualizarCliente(c); // guardar el cambio en la base de datos
        		return peliculas.subList(0, Math.min(peliculas.size(), 10)); //devuelve las 10 peliculas con mejor rating_promedio
        }
        
        // 10 random NO calificadas
        List<Pelicula> noCalificadas = new ArrayList<>();
        for (Pelicula p : peliculas) {  
            // si NO está calificada, la agrego
            if (!rDAO.clienteCalificoPelicula(c.getId(), p.getId())) {
                noCalificadas.add(p);
            }
        }
  
        // mezclar random
        
        Collections.shuffle(noCalificadas);
        return noCalificadas.subList(0, Math.min(noCalificadas.size(), 10)); //devuelve las 10 peliculas random no calificadas
    }


    // CARGA CSV

    public List<Pelicula> cargarCSV() {
    	
        List<Pelicula> peliculasCargadas = new LinkedList<>();
        String linea;
        int contadorLineas = 0;
        
        try (BufferedReader br  = new BufferedReader(new FileReader("src/recursos/movies_database.csv"))) {

            br.readLine(); // saltar cabecera


            while ((linea  = br.readLine()) != null) {
                contadorLineas++;

                try {
                	List<String> fields = parseCSVLine(linea);


                    Pelicula p = new Pelicula();

                    // Año
                    String anioStr = fields.get(0);
                    p.setAnio(Integer.parseInt(anioStr.substring(0, 4)));    
                    
                    // Título
                    p.setTitulo(fields.get(1));

                    // Resumen
                    p.setResumen(fields.get(2));
                    
                    // Rating
                    String ratingStr = fields.get(5).replace(',', '.');
                    if (ratingStr.isEmpty() || ratingStr.equalsIgnoreCase("N/A") || ratingStr.equalsIgnoreCase("nan"))
                        p.setRating_Promedio(0f);
                    else {
                        p.setRating_Promedio(Float.parseFloat(ratingStr));
                    }

                    // Poster
                    p.setPoster(fields.get(8));

                    // Género, solo el primero
                    String genero = fields.get(7).split(",")[0].trim();
                    p.setGenero(mapeoGenero(genero));

                    peliculaDAO.guardar(p); // guardar individualmente para obtener ID
                    peliculasCargadas.add(p);

                } catch (Exception e) {
                    System.err.println("ERROR EN LÍNEA " + contadorLineas + ": " + e.getMessage());
                    continue;
                }
            }

        } catch (Exception e) {
            System.err.println("Error grave en la carga del CSV: " + e.getMessage());
        }
        return peliculasCargadas;
    }

    
 // Parsea una línea CSV respetando comillas y comas internas.
 // Devuelve una lista de campos limpios.
 private List<String> parseCSVLine(String line) {
     List<String> fields = new ArrayList<>(); // para almacenar los campos
     StringBuilder sb = new StringBuilder(); // para construir cada campo
     boolean insideQuotes = false; // indica si estamos dentro de comillas

     for (int i = 0; i < line.length(); i++) {
         char c = line.charAt(i);

         if (c == '"') {
             insideQuotes = !insideQuotes; // alternar estado
         } 
         else if (c == ',' && !insideQuotes) {
             fields.add(sb.toString().trim()); // agregar campo a la lista
             sb.setLength(0); // resetear StringBuilder
         } 
         else {
             sb.append(c); // agregar caracter al campo actual
         }
     }
     fields.add(sb.toString().trim()); // agregar el último campo
     return fields;
 }

	
	public Genero mapeoGenero(String generoStr) {
	    if (generoStr == null || generoStr.isBlank())
	        return Genero.OTRO;

        generoStr = generoStr.toLowerCase().replace(" ", "");
        switch (generoStr){
        case "action": 
        	return Genero.ACCION;
        case "comedy":
        	return Genero.COMEDIA;
        case "drama":
        	return Genero.DRAMA;
        case "fantasy":
        	return Genero.FANTASIA;
        case "terror":
        	return Genero.TERROR;
        case "thriller":
        	 return Genero.THRILLER;
        case "romance":
        	return Genero.ROMANCE;
        case "scifi":
        case "sci-fi":
        	return Genero.CIENCIA_FICCION;
        case "documentary":
        	return Genero.DOCUMENTAL;
        case "family":
			return Genero.FAMILIA;
        case "adventure": 
        	return Genero.AVENTURA;
        case "animation": 
        	return Genero.ANIMACION;
        case "mystery": 
        	return Genero.MISTERIO;
        case "crime": 
        	return Genero.CRIMEN;
        case "war":
            return Genero.WAR;
        case "music":
        case "musical":
            return Genero.MUSICAL;
        case "history":
        case "historical":
            return Genero.HISTORIA;
        case "biography":
        case "biopic":
            return Genero.BIOGRAFIA;
        case "western":
            return Genero.WESTERN;
        case "sport":
        case "sports":
            return Genero.DEPORTE;
        
        default: 
        	   return Genero.OTRO;
        }
    }
	
	
	public List<Pelicula> getListaPeliculas(){
    	return this.peliculas;
    }
}
