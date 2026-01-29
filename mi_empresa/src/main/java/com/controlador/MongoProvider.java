package com.controlador;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Proveedor de acceso a MongoDB.
 * 
 * Lee la configuracion desde variables de entorno y crea un cliente de MongoDB.
 * Proporciona acceso a la coleccion donde se almacenan los documentos de alumnos.
 */
public final class MongoProvider implements AutoCloseable {

    /** Cliente de MongoDB (pool de conexiones interno). */
    private final MongoClient client;

    /** Base de datos seleccionada. */
    private final MongoDatabase database;

    /**
     * Constructor que inicializa el cliente de MongoDB.
     * Lee la configuracion desde variables de entorno o usa valores por defecto.
     * Variables de entorno usadas:
     * - MONGO_URI: URI de conexion a MongoDB
     * - MONGO_DB: nombre de la base de datos
     * - MONGO_COLLECTION: nombre de la coleccion donde se almacenan los empleados
     */
    public MongoProvider() {
        // 1) Leer configuracion del entorno o usar valores por defecto.
        String uri = envOrDefault(
                "MONGO_URI",
                "mongodb://admin:admin123@localhost:27017/?authSource=admin"
        );
        String dbName = envOrDefault("MONGO_DB", "Nintendo");

        // 2) Crear settings a partir de la URI.
        //    Aqui podrias configurar timeouts, SSL, etc. Para aula lo dejamos simple.
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .build();

        // 3) Crear el cliente y seleccionar base de datos y coleccion.
        this.client = MongoClients.create(settings);
        this.database = client.getDatabase(dbName);
    }

    public void createCollection(String nombre) {
        envOrDefault("MONGO_COLLECTION", "Empleados");
    }

    /**
     * Devuelve la coleccion donde guardamos empleados.
     *
     * @return coleccion MongoDB que almacena documentos de empleados
     */
    public MongoCollection<Document> getCollection(String nombre) {
        return database.getCollection(nombre);
    }

    /**
     * Cierra el cliente de MongoDB y libera recursos.
     */
    @Override
    public void close() {
        client.close();
    }

    /**
     * Lee una variable de entorno o devuelve un valor por defecto.
     *
     * @param key nombre de la variable de entorno
     * @param def valor por defecto si no existe o esta en blanco
     * @return valor final para usar en la configuracion
     */
    private static String envOrDefault(String key, String def) {
        String variable = System.getenv(key);
        if (variable == null || variable.isBlank()) {
            variable=def;//Devolvemos el valor por defecto pasado por parámetro si no recupera ningún valor
        }
        return variable;
    }
}
