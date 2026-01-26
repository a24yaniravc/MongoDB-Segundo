package com.example;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.model.Filters;

// Nombre, edad, ciclo

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        insertarAlumno("Juan Perez", 20, "DAM");
        insertarAlumno("Maria Lopez", 22, "DAW");
        insertarAlumno("Juan Perez", 24, "DAM");
        
        ArrayList<Document> alumnos = obtenerAlumnos();
        System.out.println("Alumnos en la base de datos:");
        for (Document alumno : alumnos) {
            System.out.println(alumno.toJson());
        }

        long borrados = borrarPorNombre("Juan Perez");
        System.out.println("Alumnos borrados con nombre 'Juan Perez': " + borrados);
    }

    public static void insertarAlumno(String nombre, int edad, String ciclo) {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            Document nuevoAlumno = new Document("nombre", nombre)
                    .append("edad", edad)
                    .append("ciclo", ciclo);

            mongoProvider.alumnado().insertOne(nuevoAlumno);
            System.out.println("Alumno insertado: " + nuevoAlumno.toJson());
        }
    }

    public static ArrayList<Document> obtenerAlumnos() {
        ArrayList<Document> alumnos = new ArrayList<>();
        try (MongoProvider mongoProvider = new MongoProvider()) {
            mongoProvider.alumnado().find().into(alumnos);
        }
        return alumnos;
    }

    public static long borrarPorNombre(String nombre) {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            return mongoProvider.alumnado().deleteOne(Filters.eq("nombre", nombre)).getDeletedCount();
        }
    }
}