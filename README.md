# Plataforma de Streaming

Se desarrolló una Plataforma de Streaming en Java para la materia Taller de Lenguajes II. La aplicación permite registrar usuarios, iniciar sesión, buscar películas y calificarlas. Se utilizó SQLite para la base de datos, aplicando el patrón DAO y la API JDBC.

El proyecto utiliza Java Swing para la interfaz gráfica, el modelo MVC para organizar la aplicación y concurrencia mediante threads.

## Ejecución

Requiere **Java 21 o superior**. Desde la carpeta principal del proyecto, ejecutar en la terminal:

```bash
java -cp "PlataformaStreaming.jar;recursos/*" main.MainGUI
```
