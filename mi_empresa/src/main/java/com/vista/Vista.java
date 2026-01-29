package com.vista;

import java.util.Scanner;

public class Vista {
    private static Scanner scanner = new Scanner(System.in);

    public static int getModificarMenu() {
        boolean salir = false;

        while (salir == false) {
            System.out.println("----- Modificar Empleado -----");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar departamento");
            System.out.println("3. Modificar salario");
            System.out.println("4. Modificar fecha de alta");
            System.out.println("5. Modificar oficio");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Lógica para modificar el nombre
                    return 1;
                case "2":
                    // Lógica para modificar el departamento
                    return 2;
                case "3":
                    // Lógica para modificar el salario
                    return 3;
                case "4":
                    // Lógica para modificar la fecha de alta
                    return 4;
                case "5":
                    // Lógica para modificar el oficio
                    return 5;
                default:
                    System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
            }
        }
        return -1;
    }

    public static String pedirNuevoValor(int opcion) {
        System.out.println("Ingrese el nuevo valor:");
        String nuevoValor = "";

        switch (opcion) {
            case 1:
                System.out.println("Nuevo nombre: ");
                nuevoValor = scanner.nextLine();
                break;
            case 2:
                System.out.println("Nuevo departamento: ");
                nuevoValor = scanner.nextLine();
                break;
            case 3:
                System.out.println("Nuevo salario: ");
                nuevoValor = scanner.nextLine();
                break;
            case 4:
                System.out.println("Nueva fecha de alta (YYYY-MM-DD): ");
                nuevoValor = scanner.nextLine();

                while (!nuevoValor.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Formato de fecha no válido. Use YYYY-MM-DD.");
                    nuevoValor = scanner.nextLine();
                }
                break;
            case 5:
                System.out.println("Nuevo oficio: ");
                nuevoValor = scanner.nextLine();
                break;
            default:
                System.out.println("Ha ocurrido un error.");
                nuevoValor = "";
                break;
        }
        return nuevoValor;
    }

    public static String mostrarMensajeInicializarDatos() {
        System.out.println("Desea inicializar los datos de la empresa? (S/N)");
        String respuesta = scanner.nextLine().toUpperCase();

        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.println("Respuesta no válida. Por favor, ingrese 'S' para sí o 'N' para no.");
            respuesta = scanner.nextLine().toUpperCase();
        }

        return respuesta;
    }

    public static int menuBusquedaEmpleados() {
        System.out.println();
        System.out.println("----- Búsqueda de Empleados -----");
        System.out.print("Por cuantos campos desea buscar? ");
        String opcion = scanner.nextLine();

        try {
            int numCampos = Integer.parseInt(opcion);
            return numCampos;
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida. Se considerará una búsqueda por un solo campo.");
            return 1;
        }
    }

    public static String pedirCampoBusqueda() {
        System.out.println("");
        System.out.println("Ingrese el campo de búsqueda (nombre, dep, salario, fechaalta, oficio):");
        String campo = scanner.nextLine();

        while (!campo.equals("nombre") && !campo.equals("dep") && !campo.equals("salario") && !campo.equals("fechaalta")
                && !campo.equals("oficio")) {
            System.out.println(
                    "Campo no válido. Por favor, ingrese uno de los siguientes campos: nombre, dep, salario, fechaalta, oficio.");
            campo = scanner.nextLine();
        }

        return campo;
    }

    public static String pedirValorBusqueda(String campo) {
        System.out.println();
        System.out.println("Ingrese el valor para " + campo + ":");
        String valor = scanner.nextLine();
        boolean valido = false;

        if (campo.equals("salario") || campo.equals("dep")) {
            while (!valido) {
                try {
                    Integer.parseInt(valor);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Valor no válido. Por favor, ingrese un número entero para " + campo + ":");
                    valor = scanner.nextLine();
                }
            }
        }

        return valor;
    }

    public static void mostrarEstadisticasDep(int dep, int numEmpleados, double mediaSalario, int maxSalario) {
        System.out.println();
        System.out.println("----- Estadísticas por Departamento -----");
        System.out.println("Departamento: " + dep);
        System.out.println("Número de empleados: " + numEmpleados);
        System.out.println("Salario medio: " + mediaSalario);
        System.out.println("Máximo salario: " + maxSalario);
        System.out.println();
    }

    public static boolean menuSubirBajarValor() {
        Boolean devolver = false;

        System.out.println("----- Modificar Valor -----");
        System.out.println("1. Subir valor");
        System.out.println("2. Bajar valor");
        System.out.println("Seleccione una opción: ");

        String opcion = scanner.nextLine();

        while (!opcion.equals("1") && !opcion.equals("2")) {
            System.out.println("Opción no válida. Por favor, seleccione 1 o 2:");
            opcion = scanner.nextLine();
        }

        if (opcion.equals("1")) {
            devolver = true;
        } else {
            devolver = false;
        }

        return devolver;
    }

    public static String pedirCampoModificar() {
        System.out.println("Ingrese el campo a modificar (salario/comision):");
        String campo = scanner.nextLine().toLowerCase();

        while (!campo.equals("salario") && !campo.equals("comision")) {
            System.out.println("Campo no válido. Por favor, ingrese 'salario' o 'comision':");
            campo = scanner.nextLine().toLowerCase();
        }

        return campo;
    }

    public static int pedirCantidadModificar() {
        int cantidad = 0;
        System.out.println("Ingrese la cantidad a modificar:");
        String input = scanner.nextLine();

        boolean valido = false;

        while (!valido) {
            try {
                cantidad = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Cantidad no válida. Por favor, ingrese un número:");
                input = scanner.nextLine();
            }
        }

        return cantidad;
    }

    public static void cerrarScanner() {
        scanner.close();
    }
}
