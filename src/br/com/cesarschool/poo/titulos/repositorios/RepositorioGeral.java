package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.daogenerico.DAOSerializadorObjetos;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface RepositorioGeral {

    default Class<?> getClasseEntidade() {
        Type tipo = getClass().getGenericSuperclass();
        if (tipo instanceof ParameterizedType) {
            ParameterizedType parametroTipo = (ParameterizedType) tipo;
            return (Class<?>) parametroTipo.getActualTypeArguments()[0];
        }
        throw new IllegalArgumentException("Não foi possível determinar a classe da entidade.");
    }

    DAOSerializadorObjetos getDao();
}
