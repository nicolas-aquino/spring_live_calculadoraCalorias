package org.calculadoracalorias.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.calculadoracalorias.entity.Plato;
import org.calculadoracalorias.entity.Receta;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class PlatosRepositoryImp implements IRepository<Plato>{
    List<Plato> db;

    public PlatosRepositoryImp() {
        this.db = loadPlatos();
    }

    @Override
    public List<Plato> getAll() {
        return db;
    }

    @Override
    public Plato getByName(String nombre) {
        return db.stream()
                .filter(plato -> plato.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    private List<Plato> loadPlatos() {
        ObjectMapper mapperJSON  = new ObjectMapper();
        List<Plato> platos;
        List<Receta> receta = null;
        File jsonFile;
        String path = "src/main/resources/plates.json";

        try {
            jsonFile = ResourceUtils.getFile(path);
            receta = mapperJSON.readValue(jsonFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("No existe el archivo. " + e.getMessage());
        }

        platos = receta.stream()
                .map(r -> new Plato(r.getNombre(), r.calcularPeso(), r.getIngredientes()))
                .toList();

        return platos;
    }
}
