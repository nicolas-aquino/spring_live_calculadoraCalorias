package org.calculadoracalorias.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.calculadoracalorias.entity.Ingrediente;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Repository
public class IngredientesRepositoryImp implements IRepository<Ingrediente>{
    List<Ingrediente> db;

    public IngredientesRepositoryImp() {
        this.db = List.of(loadIngredientes());
    }

    @Override
    public List<Ingrediente> getAll() {
        return db;
    }

    @Override
    public Ingrediente getByName(String nombre) {
        return db.stream()
                .filter(i -> i.getName().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    private Ingrediente[] loadIngredientes(){
        ObjectMapper mapperJSON  = new ObjectMapper();
        String ruta = "src/main/resources/food.json";

        try {
            return mapperJSON.readValue(new File(ruta), Ingrediente[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
