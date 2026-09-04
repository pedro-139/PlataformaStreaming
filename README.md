# Plataforma de Streaming

Se desarrollo una Plataforma de Streaming en Java para la materia Taller de lenguajes II.
La aplicación permite registrar usuarios, iniciar sesión, buscar películas y calificar películas.
Se utilizó SQLite para realizar una base de datos. Aplicación del patrón DAO y uso de API JDBC.

El proyecto utiliza Java Swing para la interfaz gráfica, se aplico el modelo MVC para organizar la aplicación y concurrencia (threads)

## Ejecución

Requiere Java 21 o superior. Desde la carpeta principal del proyecto, ejecutar en la terminal:

java -cp "PlataformaStreaming.jar;recursos/*" main.MainGUI
