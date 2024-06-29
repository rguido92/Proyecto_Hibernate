package com.example.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "asig_proyecto")
public class Proyecto_Empleado implements Serializable {

    @Id
    private Date fecha_inicio;

    private Date fecha_fin;

    @ManyToOne
    @Id
    @JoinColumn(name = "dni_emp")
    private Empleado empleado_asignado;

    public Proyecto_Empleado() {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto_asignado;

    public Proyecto_Empleado(Date fecha_inicio, Empleado empleado, Proyecto proyecto) {

        this.fecha_inicio = fecha_inicio;
        this.empleado_asignado = empleado;
        this.proyecto_asignado = proyecto;

        empleado.addListaProyectoEmpleado(this);
        proyecto.addListaProyectoEmpleado(this);
    }

    @Override
    public String toString() {
        return "Proyecto_Empleado [fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + "]";
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Empleado getEmpleado_asignado() {
        return empleado_asignado;
    }

    public void setEmpleado_asignado(Empleado empleado) {
        this.empleado_asignado = empleado;
    }

    public Proyecto getProyecto_asignado() {
        return proyecto_asignado;
    }

    public void setProyecto_asignado(Proyecto proyecto) {
        this.proyecto_asignado = proyecto;
    }

}
