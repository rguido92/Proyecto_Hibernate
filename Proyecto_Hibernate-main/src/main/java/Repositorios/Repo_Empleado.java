package Repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.Entidades.Empleado;

public class Repo_Empleado implements Repositorio<Empleado> {
    private Session session;

    public Repo_Empleado(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.save(t);
        trx.commit();
    }

    public boolean existeEmpleado(String dni) {
        if (buscarEmpleado(dni) != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Empleado> listarTodos() {
        Transaction trx = this.session.beginTransaction();
        List<Empleado> listaEmpleados = session.createQuery("From Empleado").getResultList();
        trx.commit();
        return listaEmpleados;
    }

    public Empleado buscarEmpleado(String dni) {
        Transaction trx = this.session.beginTransaction();

        Query query = session.createQuery("From Empleado e where e.dni=:dni");
        query.setParameter("dni", dni);

        Empleado empleado = (Empleado) query.uniqueResult();
        trx.commit();
        return empleado;

    }

    @Override
    public void actualizar(Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.update(t);
        trx.commit();
    }

    @Override
    public void eliminar(Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.save(t);
        trx.commit();
    }

    public void modificar(Empleado e) {
       
    }

}
