package Main;

import java.util.List;
import java.util.Scanner;

import Comparadores.ComparadorDuracion;
import Comparadores.ComparadorGenero;
import Comparadores.ComparadorTitulo;
import Modelo.POO.Genero;
import Modelo.POO.Pelicula;
import clasesDAO.FactoryDAO;
import clasesDAO.PeliculaDAO;

public class ValidacionesPelicula {
	
	public static Pelicula registrarPelicula(Scanner s) {
        Pelicula p = new Pelicula();
        boolean valido;
        String res;

        // TÍTULO
        valido = false;
        while (!valido) {
            System.out.print("Ingrese el título: ");
            p.setTitulo(s.nextLine());
            if(p.getTitulo() != null && !p.getTitulo().isBlank()) {
                System.out.println("Se ingresó: '" + p.getTitulo() + "'. ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine(); 
                if(res.equalsIgnoreCase("y")) valido = true;
            } else {
                System.out.println("Se introdujo un nombre inválido.");
            }
        }

        // RESUMEN (opcional)
        System.out.print("Ingrese el resumen (opcional): ");
        p.setResumen(s.nextLine());

        // DIRECTOR
        valido = false;
        while (!valido) {
            System.out.print("Ingrese el nombre del director: ");
            p.setDirector(s.nextLine());
            if(p.getDirector() != null && !p.getDirector().isBlank()) {
                System.out.println("Se ingresó: '" + p.getDirector() + "'. ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine(); 
                if(res.equalsIgnoreCase("y")) valido = true;
            } else {
                System.out.println("Se introdujo un nombre inválido.");
            }
        }

        // DURACIÓN
        valido = false;
        while (!valido) {
            System.out.print("Ingrese la duración (en minutos): ");
            if(s.hasNextFloat()) {
                p.setDuracion(s.nextFloat());
                s.nextLine(); 
                if(p.getDuracion() >= 0) {
                    System.out.println("Se ingresó: " + p.getDuracion() + " Minutos. ¿Es correcto? (y/n)");
                    res = s.next();
                    s.nextLine(); 
                    if(res.equalsIgnoreCase("y")) valido = true;
                } else {
                    System.out.println("Se introdujo una duración inválida.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                s.nextLine(); 
            }
        }

        // GÉNERO
        valido = false;
        while (!valido) {
            System.out.println("Ingrese el género según las siguientes opciones:");
            System.out.println("(1) ACCION - (2) COMEDIA - (3) DRAMA - (4) TERROR - (5) ROMANCE");

            if(s.hasNextInt()) {
                int gen = s.nextInt();
                s.nextLine(); 
                switch (gen) {
                    case 1 -> { p.setGenero(Genero.ACCION); }
                    case 2 -> { p.setGenero(Genero.COMEDIA);  }
                    case 3 -> { p.setGenero(Genero.DRAMA);  }
                    case 4 -> { p.setGenero(Genero.TERROR);  }
                    case 5 -> { p.setGenero(Genero.ROMANCE);  }
                    default -> System.out.println("Opción inválida. Intentá nuevamente.");
                }
                if (gen > 0 && gen<6) {
                System.out.println("Se ingresó: " + p.getGenero() + ". ¿Es correcto? (y/n)");
                res = s.next();
                s.nextLine(); 
                if(res.equalsIgnoreCase("y")) valido = true;
                }
                
            } else {
                System.out.println("Entrada inválida. Debe ser un número del 1 al 5.");
                s.nextLine();
            }
        }
        return p;
    }
	
	public static List<Pelicula> listarPeliculas(Scanner s){
		 PeliculaDAO pDAO = FactoryDAO.getPeliculaDAO();
		 List<Pelicula> lista = pDAO.listar();
		 int opcion;
         do {
        	 System.out.println("Ordenar la lista por: ");
             System.out.println("0. Ningun orden");
             System.out.println("1. Titulo ");
             System.out.println("2. Genero");      
             System.out.println("3. Duracion"); 
       
             opcion = s.nextInt();
             s.nextLine(); 

             switch (opcion) {
             		case 0: {
             			System.out.println("Lista de peliculas (sin orden): ");
             			break;
             		}
             		case 1:{ 
             			lista.sort(new ComparadorTitulo());
             			System.out.println("Lista de peliculas (ordenado por Titulo): ");
             		break;
             		}
             		case 2:{ 
             			lista.sort(new ComparadorGenero());
             			System.out.println("Lista de peliculas (ordenado por Genero): ");
             		break;
             		}
             		case 3:{
             			lista.sort(new ComparadorDuracion());
             			System.out.println("Lista de peliculas (ordenado por Duracion): ");
             		break;
             		}
             		default :{
             			System.out.println("Opción inválida");
	                    } 
             }
         } while (opcion < 0 && opcion > 3);
             return lista;
	}
}
