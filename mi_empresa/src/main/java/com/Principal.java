package com;

import java.util.List;

import org.bson.Document;

import com.controlador.GestorEmpleados;
import com.controlador.InicializadorDatos;
import com.vista.Vista;

public class Principal {
    public static void main(String[] args) {
        String respuesta = Vista.mostrarMensajeInicializarDatos();

        if (respuesta.equalsIgnoreCase("S")) {
            InicializadorDatos.insertarEmpleados();
        }

        // Todos los empleados
        List<Document> empleadosTodos = GestorEmpleados.obtenerEmpleados();
        System.out.println();
        System.out.println("Todos los empleados:");
        for (Document empleado : empleadosTodos) {
            System.out.println(empleado);
        }
        System.out.println();
        /* 
        // -- Empleados con departamento 10 [1 y luego dep + 10]--
        List<Document> empleados = GestorEmpleados.obtenerEmpleadosPorBusqueda();

        System.out.println();
        System.out.println("Empleados del departamento 10:");
        for (Document empleado : empleados) {
            System.out.println(empleado);
        }
        System.out.println();

        // -- Empleados con departamento 10 y 20 [2 y luego dep + 10, dep + 20]--
        empleados = GestorEmpleados.obtenerEmpleadosPorBusqueda();
        System.out.println();
        System.out.println("Empleados del departamento 10 y 20:");
        for (Document empleado : empleados) {
            System.out.println(empleado);
        }
        System.out.println();

        // Empleados con salario > 1300 y oficio Profesora
        empleados = GestorEmpleados.obtenerEmpleadosPorBusqueda();
        System.out.println();
        System.out.println("Empleados con salario > 1300 y oficio Profesora:");
        for (Document empleado : empleados) {
            System.out.println(empleado);
        } */

        // Sube el salario de los analistas en 100€, a todos los analistas
        GestorEmpleados.subirBajarValorPorOficio("Analista");

        // Media salario
        double mediaSalario = GestorEmpleados.obtenerMediaSalario();
        System.out.println();
        System.out.println("Media de salario de los empleados: " + mediaSalario + " €");

        // Visualiza por departamento el número de empleados, el salario medio y el máximo salario.
        int dep = 10;
        GestorEmpleados.verEmpleadosPorDep(dep);

        // Visualiza el nombre del empleado que tiene el máximo salario
        System.out.println("Empleado con salario máximo: " + GestorEmpleados.obtenerNombreEmpleadoMaxSalario());
    }
}