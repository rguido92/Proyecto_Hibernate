package Repositorios;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.Entidades.Empleado;
import com.example.Entidades.Proyecto;
import com.example.Entidades.Proyecto_Empleado;

public class Repo_ProyectoEmpleado implements Repositorio<Proyecto_Empleado> {
    private Session session;

    public Repo_ProyectoEmpleado(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Proyecto_Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.save(t);
        trx.commit();
    }

    @Override
    public List<Proyecto_Empleado> listarTodos() {
        Transaction trx = this.session.beginTransaction();
        List<Proyecto_Empleado> lista_ProyectoEmpleado = session.createQuery("From asig_proyecto").getResultList();
        trx.commit();
        return lista_ProyectoEmpleado;
    }

    @Override
    public void actualizar(Proyecto_Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.update(t);
        trx.commit();
    }

    @Override
    public void eliminar(Proyecto_Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.delete(t);
        trx.commit();
    }

    public void borrarParticipacion(Proyecto p, Empleado e) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery(
                "From Proyecto_Empleado a where a.empleado_asignado.dni=:dni AND a.proyecto_asignado.id_proyecto=:id");
        query.setParameter("dni", e.getDni());
        query.setParameter("id", p.getId_proyecto());
        Proyecto_Empleado proyecto_Empleado = (Proyecto_Empleado) query.uniqueResult();
        Date date = new Date();
        proyecto_Empleado.setFecha_fin(date);
        session.update(proyecto_Empleado);
        trx.commit();
    }
}
