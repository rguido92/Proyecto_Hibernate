package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Empleado")
@Table
public class Empleado {

    @Id
    @Column(name = "dni", columnDefinition = "char(9)")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "empleado_jefe")
    private List<Proyecto> listaProyecto_Empleados_Jefe = new ArrayList<Proyecto>();

    @OneToMany(mappedBy = "empleado_asignado")
    private List<Proyecto_Empleado> listaProyecto_Empleados = new ArrayList<Proyecto_Empleado>();

    @OneToOne(mappedBy = "empleado")
    private Datos_Profesionales datos_Profesionales;

    // Constructor
    public Empleado(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Empleado() {
        // TODO Auto-generated constructor stub
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Datos_Profesionales getDatos_Profesionales() {
        return datos_Profesionales;
    }

    public void setDatos_Profesionales(Datos_Profesionales datos_Profesionales) {
        datos_Profesionales.setEmpleado(this);
        this.datos_Profesionales = datos_Profesionales;
    }

    public List<Proyecto_Empleado> getListaProyecto_Empleados() {
        return listaProyecto_Empleados;
    }

    public void setListaProyecto_Empleados(List<Proyecto_Empleado> listaProyecto_Empleados) {
        this.listaProyecto_Empleados = listaProyecto_Empleados;
    }

    public void addListaProyectoEmpleado(Proyecto_Empleado proyecto_Empleado) {
        this.listaProyecto_Empleados.add(proyecto_Empleado);
    }

    public void removeListaProyectoEmpleado(Proyecto_Empleado proyecto_Empleado) {
        this.listaProyecto_Empleados.remove(proyecto_Empleado);
    }

    @Override
    public String toString() {
        return "Empleado [dni=" + dni + ", nombre=" + nombre + ", datos_Profesionales=" + datos_Profesionales + "]";
    }

    
}
