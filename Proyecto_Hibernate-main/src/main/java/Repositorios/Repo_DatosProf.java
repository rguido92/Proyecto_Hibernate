package Repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.Entidades.Datos_Profesionales;
import com.example.Entidades.Proyecto_Empleado;

public class Repo_DatosProf implements Repositorio<Datos_Profesionales> {

    private Session session;

    public Repo_DatosProf(Session session) {
        this.session = session;
    }
    

    @Override
    public void guardar(Datos_Profesionales t) {
        Transaction trx = this.session.beginTransaction();
        session.save(t);
        trx.commit();
    }

    @Override
    public List<Datos_Profesionales> listarTodos() {
        Transaction trx = this.session.beginTransaction();
        List<Datos_Profesionales> lista_datosProf = session.createQuery("From datos_profesionales").getResultList();
        trx.commit();
        return lista_datosProf;
    }

    @Override
    public void actualizar(Datos_Profesionales t) {
        Transaction trx = this.session.beginTransaction();
        session.update(t);
        trx.commit();
    }

    @Override
    public void eliminar(Datos_Profesionales t) {
        Transaction trx = this.session.beginTransaction();
        session.save(t);
        trx.commit();
    }

}
