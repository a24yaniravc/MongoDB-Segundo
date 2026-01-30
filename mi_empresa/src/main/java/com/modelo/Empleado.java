package com.modelo;

import java.sql.Date;

public class Empleado {
    // Datos de los documentos
    private String nombre;
    private int departamento;
    private int salario;
    private Date fecha;
    private String oficio;
    private int comision;

    // Constructores
    public Empleado(String nombre, int departamento, int salario, Date fecha, String oficio, int comision) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fecha = fecha;
        this.oficio = oficio;
        this.comision = comision;
    }

    public Empleado(String nombre, int departamento, int salario, Date fecha) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fecha = fecha;
    }

    public Empleado(String nombre, int departamento, int salario, Date fecha, String oficio) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fecha = fecha;
        this.oficio = oficio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getDepartamento() {
        return departamento;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getSalario() {
        return salario;
    }

    public String getOficio() {
        return oficio;
    }

    public int getComision() {
        return comision;
    }

    // Setters
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Empleado [nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario + ", fecha="
                + fecha + ", oficio=" + oficio + ", comision=" + comision + "]";
    }
}
