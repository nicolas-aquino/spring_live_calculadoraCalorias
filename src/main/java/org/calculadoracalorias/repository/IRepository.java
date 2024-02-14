package org.calculadoracalorias.repository;

import java.util.List;

public interface IRepository<T>{
    public List<T> getAll();

    public T getByName(String nombre);
}
