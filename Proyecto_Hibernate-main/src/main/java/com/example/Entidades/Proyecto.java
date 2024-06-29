package com.example.Entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proyecto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(name = "dni_jefe_proyecto")
    private Empleado empleado_jefe;

    @OneToMany(mappedBy = "proyecto_asignado")
    List<Proyecto_Empleado> listaProyecto_empleado;

    public Proyecto(int id_proyecto, String nombre, Date fecha_inicio) {
        this.id_proyecto = id_proyecto;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
    }
    

    @Override
    public String toString() {
        return "Proyecto [id_proyecto=" + id_proyecto + ", nombre=" + nombre + ", fecha_inicio=" + fecha_inicio
                + ", fecha_fin=" + fecha_fin + ", empleado_jefe=" + empleado_jefe + ", listaProyecto_empleado="
                + listaProyecto_empleado + "]";
    }


    public Proyecto() {
        // TODO Auto-generated constructor stub
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(java.util.Date date) {
        this.fecha_inicio = date;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Empleado getEmpleado_jefe() {
        return empleado_jefe;
    }

    public void setEmpleado_jefe(Empleado empleado_jefe) {
        this.empleado_jefe = empleado_jefe;
    }

    public List<Proyecto_Empleado> getListaProyecto_empleado() {
        return listaProyecto_empleado;
    }

    public void setListaProyecto_empleado(List<Proyecto_Empleado> listaProyecto_empleado) {
        this.listaProyecto_empleado = listaProyecto_empleado;
    }

    public void addListaProyectoEmpleado(Proyecto_Empleado proyecto_Empleado) {
        this.listaProyecto_empleado.add(proyecto_Empleado);
    }

    public void removeListaProyectoEmpleado(Proyecto_Empleado proyecto_Empleado) {
        this.listaProyecto_empleado.remove(proyecto_Empleado);
    }

}
