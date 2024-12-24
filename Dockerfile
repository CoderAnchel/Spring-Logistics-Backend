# Usamos una imagen base de Java
FROM openjdk:17-jdk-slim

# Creamos un directorio para la aplicación
WORKDIR /app

# Copiamos el archivo JAR de la aplicación al contenedor
COPY target/mi-aplicacion.jar app.jar

# Exponemos el puerto en el que correrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
