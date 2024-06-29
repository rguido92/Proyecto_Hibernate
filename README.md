Proyecto de Gestión de Proyectos de Empresa con Hibernate

En este proyecto desarrollaremos una aplicación que permita gestionar diversos proyectos de una empresa. Utilizaremos Hibernate para la capa de persistencia y PostgreSQL como sistema de gestión de bases de datos.
Descripción del Proyecto

El objetivo principal es gestionar una base de datos que incluya información sobre empleados, proyectos y la relación entre ambos. Además, se distinguirá entre empleados en plantilla y empleados contratados para proyectos específicos.
Diagrama Conceptual de la Base de Datos

El modelo conceptual de la base de datos incluye las siguientes entidades y relaciones:

    Empleado
        empleado_id: tipo serial y clave primaria
        nombre: tipo varchar
        edad: tipo entero
        es_en_plantilla: tipo booleano
        Relación opcional con DatosProf (solo para empleados en plantilla)

    DatosProf
        datos_prof_id: tipo serial y clave primaria
        puesto: tipo varchar
        salario: tipo decimal
        Relación uno a uno con Empleado

    Proyecto
        proyecto_id: tipo serial y clave primaria
        nombre: tipo varchar
        descripcion: tipo text
        jefe_proyecto: referencia a Empleado (solo empleados en plantilla)

    EmpleadoProyecto
        empleado_proyecto_id: tipo serial y clave primaria
        empleado_id: referencia a Empleado
        proyecto_id: referencia a Proyecto

Reglas de Negocio

    Los empleados pueden estar en plantilla o ser contratados solo para proyectos específicos.
    Los datos profesionales (DatosProf) son específicos para empleados en plantilla.
    Solo los empleados en plantilla pueden ser jefes de proyecto.
