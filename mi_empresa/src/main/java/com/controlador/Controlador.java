package com.controlador;

import java.util.List;

import org.bson.Document;

import com.vista.Vista;

public class Controlador {
    private int opcion;
    private List<Document> empleados;

    public void comenzarApp() {
        while (opcion != 8) {
            opcion = Vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    empleados = GestorEmpleados.obtenerEmpleados();
                    Vista.imprimirListas(empleados);
                    break;
                case 2:
                    empleados = GestorEmpleados.obtenerEmpleadosPorBusqueda();
                    Vista.imprimirListas(empleados);
                    break;
                case 3:
                    empleados = GestorEmpleados.obtenerEmpleadosSalarioMayorYOficio(1300, "Profesora");
                    Vista.imprimirListas(empleados);
                    break;
                case 4:
                    GestorEmpleados.subirBajarValorPorOficio("Analista");
                    break;
                case 5:
                    GestorEmpleados.subirBajarValorPorOficio("Analista");
                    break;
                case 6:
                    GestorEmpleados.verEmpleadosPorDep(10);
                    break;
                case 7:
                    String nombreMaxSalario = GestorEmpleados.obtenerNombreEmpleadoMaxSalario();
                    System.out.println("Empleado con el máximo salario: " + nombreMaxSalario);
                    break;
                case 8:

                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
