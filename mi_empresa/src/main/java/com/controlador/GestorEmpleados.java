package com.controlador;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.vista.Vista;

/**
 * Gestor de la tabla Empleados
 */
public class GestorEmpleados {
    /**
     * Método para insertar un Empleado
     * 
     * @param nombre
     * @param departamento
     * @param salario
     * @param fechaalta
     */
    public static void insertarEmpleado(String nombre, int departamento, int salario, Date fechaalta) {
        try (MongoProvider mongo = new MongoProvider()) {
            List<Document> arrayEmpleados = new ArrayList<>();

            int id = mongo.getCollection("empleados").find().into(arrayEmpleados).size();

            Document empleado = new Document()
                    .append("Emp_no", id)
                    .append("nombre", nombre)
                    .append("dep", departamento)
                    .append("salario", salario)
                    .append("fechaalta", fechaalta);

            mongo.getCollection("empleados").insertOne(empleado);
        } catch (Exception e) {
            System.err.println("Ha habido un error a la hora de insertar el empleado: " + e);
        }
    }

    /**
     * Método para insertar un Empleado
     * 
     * @param nombre
     * @param departamento
     * @param salario
     * @param fechaalta
     * @param oficio
     */
    public static void insertarEmpleado(String nombre, int departamento, int salario, Date fechaalta,
            String oficio) {
        try (MongoProvider mongo = new MongoProvider()) {
            List<Document> arrayEmpleados = new ArrayList<>();

            int id = mongo.getCollection("empleados").find().into(arrayEmpleados).size();

            Document empleado = new Document()
                    .append("Emp_no", id)
                    .append("nombre", nombre)
                    .append("dep", departamento)
                    .append("salario", salario)
                    .append("fechaalta", fechaalta)
                    .append("oficio", oficio);

            mongo.getCollection("empleados").insertOne(empleado);
        } catch (Exception e) {
            System.err.println("Ha habido un error a la hora de insertar el empleado: " + e);
        }
    }

    /**
     * Método para insertar un Empleado
     * 
     * @param nombre
     * @param departamento
     * @param salario
     * @param fechaalta
     * @param oficio
     * @param comision
     */
    public static void insertarEmpleado(String nombre, int departamento, int salario, Date fechaalta,
            String oficio, int comision) {
        try (MongoProvider mongo = new MongoProvider()) {
            List<Document> arrayEmpleados = new ArrayList<>();

            int id = mongo.getCollection("empleados").find().into(arrayEmpleados).size();

            Document empleado = new Document()
                    .append("Emp_no", id)
                    .append("nombre", nombre)
                    .append("dep", departamento)
                    .append("salario", salario)
                    .append("fechaalta", fechaalta)
                    .append("oficio", oficio)
                    .append("comision", comision);

            mongo.getCollection("empleados").insertOne(empleado);
        } catch (Exception e) {
            System.err.println("Ha habido un error a la hora de insertar el empleado: " + e);
        }
    }

    /**
     * Método que devuelve a todos los empleados
     * 
     * @return
     */
    public static List<Document> obtenerEmpleados() {
        List<Document> empleados = new ArrayList<>();

        try (MongoProvider mongoProvider = new MongoProvider()) {
            mongoProvider.getCollection("empleados").find().into(empleados);
        }

        return empleados;
    }

    /**
     * Método que devuelve empleados según una búsqueda
     * 
     * @param busqueda
     * @param valorBusqueda
     * @return
     */
    public static List<Document> obtenerEmpleadosPorBusqueda() {
        List<Document> empleados = new ArrayList<>();
        int numBusqueda = Vista.menuBusquedaEmpleados();

        try (MongoProvider mongoProvider = new MongoProvider()) {
            while (numBusqueda-- >= 1) {
                String siguienteBusqueda = Vista.pedirCampoBusqueda();

                String siguienteValor = Vista.pedirValorBusqueda(siguienteBusqueda);

                if (siguienteBusqueda.equals("salario") || siguienteBusqueda.equals("dep")) {
                    int valor = Integer.parseInt(siguienteValor);
                    mongoProvider.getCollection("empleados").find(Filters.eq(siguienteBusqueda, valor)).into(empleados);
                } else {
                    mongoProvider.getCollection("empleados").find(Filters.eq(siguienteBusqueda, siguienteValor))
                            .into(empleados);
                }
            }
        }

        return empleados;
    }

    /**
     * Método para borrar un empleado por nombre
     * 
     * @param nombre
     * @return
     */
    public static long borrarEmpleadoPorNombre(String nombre) {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            return mongoProvider.getCollection("empleados").deleteOne(Filters.eq("nombre", nombre)).getDeletedCount();
        }
    }

    /**
     * Método para modificar un empleado por busqueda
     * 
     * @param busqueda
     * @param valorBusqueda
     * @return
     */
    public static long modificarEmpleadoPorBusqueda(String busqueda, String valorBusqueda) {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            int opcion = Vista.getModificarMenu();

            if (opcion != -1) {
                String nuevoValor = Vista.pedirNuevoValor(opcion);

                if (nuevoValor != "") {
                    String campo = "";

                    switch (opcion) {
                        case 1:
                            campo = "nombre";
                            break;
                        case 2:
                            campo = "dep";
                            break;
                        case 3:
                            campo = "salario";
                            break;
                        case 4:
                            campo = "fechaalta";
                            break;
                        case 5:
                            campo = "oficio";
                            break;
                    }

                    Document updateDocument = new Document("$set", new Document(campo, nuevoValor));
                    return mongoProvider.getCollection("empleados")
                            .updateOne(Filters.eq(busqueda, valorBusqueda), updateDocument).getModifiedCount();
                }
            }
        }
        return 0;
    }

    /**
     * Visualiza por departamento el número de empleados, el salario medio y el
     * máximo salario.
     * 
     * @param dep
     */
    public static void verEmpleadosPorDep(int dep) {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            List<Document> empleados = new ArrayList<>();
            mongoProvider.getCollection("empleados").find(Filters.eq("dep", dep)).into(empleados);

            int sumaSalarios = 0;
            int maxSalario = 0;
            for (Document empleado : empleados) {
                int salario = empleado.getInteger("salario");
                sumaSalarios += salario;
                if (salario > maxSalario) {
                    maxSalario = salario;
                }
            }

            double mediaSalario = sumaSalarios / empleados.size();

            Vista.mostrarEstadisticasDep(dep, empleados.size(), mediaSalario, maxSalario);
        }
    }

    /**
     * Método para obtener la media del salario de los empleados
     * 
     * @return
     */
    public static int obtenerMediaSalario() {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            List<Document> empleados = new ArrayList<>();
            mongoProvider.getCollection("empleados").find().into(empleados);

            int sumaSalarios = 0;
            for (Document empleado : empleados) {
                sumaSalarios += empleado.getInteger("salario");
            }

            return sumaSalarios / empleados.size();
        }
    }

    /**
     * Método para obtener el nombre del empleado con más salario
     * 
     * @return
     */
    public static String obtenerNombreEmpleadoMaxSalario() {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            Document empleadoMax = mongoProvider.getCollection("empleados")
                    .find()
                    .sort(new Document("salario", -1))
                    .first();

            if (empleadoMax != null) {
                return empleadoMax.getString("nombre");
            } else {
                return null;
            }
        }
    }

    /**
     * Método para subir o bajar un valor (salario/comision) por Oficio
     * 
     * @param oficio
     */
    public static void subirBajarValorPorOficio(String oficio) {
        Boolean subirBajar = Vista.menuSubirBajarValor();
        String campoModificar = Vista.pedirCampoModificar();
        int cantidadModificar = Vista.pedirCantidadModificar();

        if (subirBajar) {
            try (MongoProvider mongoProvider = new MongoProvider()) {
                Document updateDocument = new Document("$inc", new Document(campoModificar, cantidadModificar));
                mongoProvider.getCollection("empleados").updateMany(new Document("oficio", oficio), updateDocument);
                System.out.println(campoModificar + " subido con éxito!");
            } catch (Exception e) {
                System.err.println("Ha habido un error al subir el valor: " + e);
            }
        } else {
            try (MongoProvider mongoProvider = new MongoProvider()) {
                Document updateDocument = new Document("$inc", new Document(campoModificar, -cantidadModificar));
                mongoProvider.getCollection("empleados").updateMany(new Document("oficio", oficio), updateDocument);
                System.out.println(campoModificar + " bajado con éxito!");
            } catch (Exception e) {
                System.err.println("Ha habido un error al bajar el valor: " + e);
            }
        }
    }

    public static List<Document> obtenerEmpleadosSalarioMenorYOficio(int numCheck, String oficio) {
        List<Document> empleados = new ArrayList<>();

        try (MongoProvider mongoProvider = new MongoProvider()) {
            mongoProvider.getCollection("empleados")
                    .find(Filters.and
                            (Filters.eq("oficio", oficio), 
                            Filters.lt("salario", 1300))).into(empleados);
        } catch (Exception e) {
            System.err.println("Ha habido un error al buscar: " + e);
        }

        return empleados;
    }
}
