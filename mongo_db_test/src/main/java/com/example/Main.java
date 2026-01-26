package com.example;
// Nombre, ciudad, entrenador(nombre, direccion)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /**
         * Para ver los pedidos cuya sua de las cantidades de las lineas cumplen >100
         */
        /*db.pedidos.aggregate([
            {
                $project: {
                    _id: 0,
                    idPedido: 1,
                    idCliente: 1,
                    fecha: 1,
                    estado: 1,
                    cantidadTotal: { $sum: "$lineas.cantidad" }
                }
            },
            {
                $match: {
                    cantidadTotal: { $gt: 100 }
                }
            }
        ])*/

        /**
         * Segundo método
         */
        /* db.pedidos.aggregate([
            { $unwind: "$lineas" },
            { $match: { "lineas.cantidad" : { $gt: 100 } } },
            { $project: {
                _id: 0,
                idPedido: 1,
                idCliente: 1,
                fecha: 1,
                estado: 1,
                linea: "$lineas" }}
        ])*/
    }
}