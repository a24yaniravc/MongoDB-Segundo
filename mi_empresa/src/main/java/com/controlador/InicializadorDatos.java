package com.controlador;

import java.sql.Date;

public class InicializadorDatos {
    public static void insertarEmpleados() {
        GestorEmpleados.insertarEmpleado("Juan", 10, 1000, Date.valueOf("1999-10-10"));
        GestorEmpleados.insertarEmpleado("Alicia", 10, 1400, Date.valueOf("2000-08-07"), "Profesora");
        GestorEmpleados.insertarEmpleado("María Jesús", 20, 1500, Date.valueOf("2005-01-05"), "Analista", 100);
        GestorEmpleados.insertarEmpleado("Alberto", 20, 1100, Date.valueOf("2001-11-15"));
        GestorEmpleados.insertarEmpleado("Fernando", 30, 1400, Date.valueOf("1999-11-20"), "Analista", 200);
    }
}
