package com;

import com.controlador.Controlador;
import com.controlador.InicializadorDatos;
import com.vista.Vista;

public class Principal {
    private static Controlador controlador = new Controlador();
    public static void main(String[] args) {
        String respuesta = Vista.mostrarMensajeInicializarDatos();

        if (respuesta.equalsIgnoreCase("S")) {
            InicializadorDatos.insertarEmpleados();
        }

        controlador.comenzarApp();
    }
}