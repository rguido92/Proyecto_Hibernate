package Repositorios;

import java.util.List;

public interface Repositorio<T> {
    void guardar(T t);

    List<T> listarTodos();

    void actualizar(T t);

    void eliminar(T t);
}
