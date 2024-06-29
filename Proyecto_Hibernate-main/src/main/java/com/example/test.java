package com.example;

import org.hibernate.Session;

import com.example.Entidades.Categoria;
import com.example.Entidades.Datos_Profesionales;
import com.example.Entidades.Empleado;
import com.example.Entidades.Proyecto;
import com.example.Entidades.Proyecto_Empleado;

import Repositorios.Repo_DatosProf;
import Repositorios.Repo_Empleado;
import Repositorios.Repo_Proyecto;
import Repositorios.Repo_ProyectoEmpleado;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {

    private static Scanner sc;
    private static Session session = HibernateUtil.get().openSession();

    // Inicializar repositorios
    private static Repo_Empleado repo_Empleado = new Repo_Empleado(session);
    private static Repo_DatosProf repo_DatosProf = new Repo_DatosProf(session);
    private static Repo_Proyecto repo_Proyecto = new Repo_Proyecto(session);
    private static Repo_ProyectoEmpleado repo_ProyectoEmpleado = new Repo_ProyectoEmpleado(session);

    public static void main(String[] args) {
        System.out.println("Test");
        menuPrincipal();
        // Empleado empleado1 = new Empleado("2222", "jowei");
        // repo_Empleado.guardar(empleado1);
        System.out.println("Finalizando la conexion a MySQL");
        session.close();
        /**
         * Crear, borrar y modificar los datos de un empleado.
         * Crear, borrar y modificar los datos de un proyecto.
         * Asignar un empleado a un proyecto.
         * Indicar el fin de la participación de un empleado en un proyecto.
         * Cambiar el jefe de un proyecto.
         * Mostrar los datos de un proyecto (nombre, fechas, empleados…).
         * Mostrar los datos de los empleados que están en plantilla.
         * Mostrar los empleados que son jefes de proyecto.
         */

    }

    public static void menuPrincipal() {
        sc = new Scanner(System.in);
        int opcion = -1;
        try {
            do {
                System.out.println("1.Empleados\n2.Proyectos\n3.Asignar proyecto\n4.Terminar participacion" +
                        "\n5.Cambiar jefe proyecto\n6.Mostrar datos proyecto\n7.Empleados plantilla\n8.Mostrar jefe proyecto\n0.Salir");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        menuEmpleados();
                        break;
                    case 2:
                        menuProyectos();
                        break;
                    case 3:
                        asignarProyecto();
                        break;
                    case 4:
                        repo_ProyectoEmpleado.borrarParticipacion(
                                repo_Proyecto.buscarProyecto(pedirInt("Id del proyecto")),
                                repo_Empleado.buscarEmpleado(pedirString("Introducir dni")));

                        break;
                    case 5:
                        int id = pedirInt("id del proyecto :");
                        Empleado e = repo_Empleado.buscarEmpleado(pedirString("Dni empleado "));
                        if (e.getDatos_Profesionales() != null) {
                            repo_Proyecto.cambiarJefe(id, e);
                        } else
                            System.out.println("el empleado jefe debe ser de plantilla");

                        break;
                    case 6:
                        mostrarDatosProyecto(pedirInt("Id del proyecto :"));
                        break;
                    case 7:
                        mostrarPlantilla();
                        break;
                    case 8:
                        // System.out.println(repo_Proyecto.mostrarJefeProyecto(pedirInt("Id del
                        // proyecto")));
                        for (Proyecto p : repo_Proyecto.listarTodos()) {
                            System.out.println(p.getEmpleado_jefe().toString());
                        }
                        break;
                    default:
                        break;
                }
            } while (opcion != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void mostrarPlantilla() {
        for (Empleado e : repo_Empleado.listarTodos()) {
            System.out.println(e.toString());
        }
    }

    private static void mostrarDatosProyecto(int id) {

        System.out.println(repo_Proyecto.buscarProyecto(id).toString());
    }

    private static void asignarProyecto() {
        String dni = pedirString("Introduce el dni del empleado");
        Empleado e = repo_Empleado.buscarEmpleado(dni);
        if (e != null) {
            int id_proyecto = pedirInt("Introduce el id del proyecto");
            Proyecto p = repo_Proyecto.buscarProyecto(id_proyecto);
            if (p != null) {
                Date date = new Date();
                for (Proyecto_Empleado pe : p.getListaProyecto_empleado()) {
                    if (!pe.getEmpleado_asignado().getDni().equals(e.getDni())) {
                        Proyecto_Empleado proyecto_Empleado = new Proyecto_Empleado(date,
                                repo_Empleado.buscarEmpleado(dni),
                                repo_Proyecto.buscarProyecto(id_proyecto));
                        repo_ProyectoEmpleado.guardar(proyecto_Empleado);
                    }
                }

            } else {
                System.out.println("El proyecto con id " + id_proyecto + " no existe");
            }
        } else {
            System.out.println("El empleado con dni " + dni + " no existe");
        }

    }

    private static void menuProyectos() {
        int opcion = -1;
        do {
            System.out.println("1.Crear\n2.Borrar\n3.Modificar\n0.Atras");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Proyecto proyecto = crearProyecto();
                    repo_Proyecto.guardar(proyecto);

                    break;
                case 2:
                    repo_Proyecto.eliminar(repo_Proyecto.buscarProyecto(pedirInt("Id proyecyo")));
                    break;
                case 3:
                    Proyecto p = repo_Proyecto.buscarProyecto(pedirInt("id del proyecto"));
                    p.setNombre(pedirString("nuevo nombre proyecto "));
                    p.setEmpleado_jefe(
                            repo_Empleado.buscarEmpleado(pedirString("Introduce el dni del empleado jefe nuevo")));
                    repo_Proyecto.actualizar(p);
                    break;
                case 4:
                    menuPrincipal();
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }

    private static Proyecto crearProyecto() {
        Proyecto proyecto = new Proyecto();
        // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        proyecto.setNombre(pedirString("Introduce el nombre del proyecto"));
        proyecto.setFecha_inicio(date);

        System.out.println("creando proyecto... ");

        return proyecto;
    }

    private static void menuEmpleados() {
        int opcion = -1;
        do {
            System.out.println("1.Crear\n2.Borrar\n3.Modificar\n0.Atras");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Empleado empleado = crearEmpleado();
                    repo_Empleado.guardar(empleado);
                    System.out.println("El empleado sera de plantilla?\nS / N");
                    String opt = sc.next().trim().toUpperCase();
                    if (opt.equals("S")) {
                        Datos_Profesionales datos_Profesionales = crerDatosProfesionales();
                        empleado.setDatos_Profesionales(datos_Profesionales);
                        repo_DatosProf.guardar(datos_Profesionales);
                        repo_Empleado.guardar(empleado);
                    }

                    break;
                case 2:
                    repo_Empleado.eliminar(buscarEmpleado());
                    break;
                case 3:
                    String dni = pedirString("Dni empleado a modificar");
                    if (repo_Empleado.existeEmpleado(dni)) {
                        Empleado e = repo_Empleado.buscarEmpleado(dni);

                        e.setNombre(pedirString("Introduce nombre"));
                        e.setDatos_Profesionales(crerDatosProfesionales());

                        repo_DatosProf.actualizar(e.getDatos_Profesionales());
                        repo_Empleado.actualizar(e);
                    }
                    break;
                case 4:
                    menuPrincipal();
                    break;
                default:
                    break;
            }
        } while (opcion != 0);

    }

    private static Empleado buscarEmpleado() {
        String dni = pedirString("Introduce el DNI del empleado a buscar");
        return repo_Empleado.buscarEmpleado(dni);
    }

    private static Empleado crearEmpleado() {
        Empleado empleado = new Empleado();
        String dni = pedirString("Introduce DNI :");
        if (repo_Empleado.buscarEmpleado(dni) == null) {
            String nombre = pedirString("Introduce el nombre :");
            empleado.setDni(dni);
            empleado.setNombre(nombre);
        } else {
            System.out.println("El dni ya existe");
            crearEmpleado();
        }

        return empleado;
    }

    private static Datos_Profesionales crerDatosProfesionales() {
        Datos_Profesionales datos_Profesionales = new Datos_Profesionales();
        double sueldo_bruto = pedirDouble("Introduce el sueldo bruto");
        datos_Profesionales.setSueldo_bruto(sueldo_bruto);
        switch (pedirCategoria()) {
            case 1:
                datos_Profesionales.setCategoria(Categoria.A);
                break;
            case 2:
                datos_Profesionales.setCategoria(Categoria.B);
                break;
            case 3:
                datos_Profesionales.setCategoria(Categoria.C);
                break;
            case 4:
                datos_Profesionales.setCategoria(Categoria.D);
                break;
            default:
                break;
        }
        return datos_Profesionales;
    }

    private static int pedirCategoria() {
        int categoria = -1;
        do {
            System.out.println("Elige una categoria");
            System.out.println("\n1. A\n2. B\n3. C\n4. D");
            try {
                categoria = Integer.parseInt(sc.next());

            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Introduce una opcion valida");
            }
        } while (categoria > 4 || categoria < 1);
        return categoria;
    }

    private static double pedirDouble(String mensaje) {
        System.out.println(mensaje);
        double entrada = -1;
        do {
            try {
                entrada = Double.parseDouble(sc.next());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un valor numerico");
            }

        } while (entrada < 0);
        return entrada;
    }

    private static int pedirInt(String mensaje) {
        System.out.println(mensaje);
        int entrada = -1;
        do {
            try {
                entrada = Integer.parseInt(sc.next());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un valor numerico");
            }

        } while (entrada < 0);
        return entrada;
    }

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        String entrada = "";
        do {
            try {
                entrada = sc.next();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");
            }
        } while (entrada.equals(""));

        return entrada;
    }

}
