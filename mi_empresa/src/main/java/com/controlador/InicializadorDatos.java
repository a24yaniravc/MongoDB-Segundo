package com.controlador;

import java.sql.Date;

import com.modelo.Empleado;

public class InicializadorDatos {
    public static void insertarEmpleados() {
        Empleado e1 = new Empleado("Juan", 10, 1000, Date.valueOf("1999-10-10"));
        Empleado e2 = new Empleado("Alicia", 10, 1400, Date.valueOf("2000-08-07"), "Profesora");
        Empleado e3 = new Empleado("María Jesús", 20, 1500, Date.valueOf("2005-01-05"), "Analista", 100);
        Empleado e4 = new Empleado("Alberto", 20, 1100, Date.valueOf("2001-11-15"));
        Empleado e5 = new Empleado("Fernando", 30, 1400, Date.valueOf("1999-11-20"), "Analista", 200);

        GestorEmpleados.insertarEmpleado(e1.getNombre(), e1.getDepartamento(), e1.getSalario(), e1.getFecha());
        GestorEmpleados.insertarEmpleado(e2.getNombre(), e2.getDepartamento(), e2.getSalario(), e2.getFecha(), e2.getOficio());
        GestorEmpleados.insertarEmpleado(e3.getNombre(), e3.getDepartamento(), e3.getSalario(), e3.getFecha(), e3.getOficio(), e3.getComision());
        GestorEmpleados.insertarEmpleado(e4.getNombre(), e4.getDepartamento(), e4.getSalario(), e4.getFecha());
        GestorEmpleados.insertarEmpleado(e5.getNombre(), e5.getDepartamento(), e5.getSalario(), e5.getFecha(), e5.getOficio(), e5.getComision());
    }
}
