package org.calculadoracalorias.service;

import org.calculadoracalorias.dto.request.PlatoReqDTO;
import org.calculadoracalorias.dto.response.IngredienteDePlatoDTO;
import org.calculadoracalorias.dto.response.PlatoResDTO;
import org.calculadoracalorias.entity.Ingrediente;
import org.calculadoracalorias.entity.Plato;
import org.calculadoracalorias.repository.IngredientesRepositoryImp;
import org.calculadoracalorias.repository.PlatosRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    IngredientesRepositoryImp repoIngr;
    @Autowired
    PlatosRepositoryImp repoPlatos;

    //Plato pizza = new Plato("Pizza", List.of(
    //        new IngredienteDelPlato(repo.getByName("Harina de Trigo Refinada"), 200.0),
    //        new IngredienteDelPlato(repo.getByName("Salsa de Tomate en Conserva"), 100.0),
    //        new IngredienteDelPlato(repo.getByName("Queso crema"), 150.0)
    //));

    public List<Ingrediente> getAllIngredientes() {
        return repoIngr.getAll();
    }

    public List<PlatoResDTO> getAllPlatos() {
        List<PlatoResDTO> res = new ArrayList<>();

        repoPlatos.getAll().forEach(p -> {
            List<IngredienteDePlatoDTO> ingredientes = new ArrayList<>();
            String nombre = p.getNombre();
            Double peso = p.getPeso();

            fillIngredienteDePlatoDTOList(p, ingredientes);

            res.add(new PlatoResDTO(nombre, peso, ingredientes));
        });

        return res;
    }

    private void fillIngredienteDePlatoDTOList(Plato p, List<IngredienteDePlatoDTO> ingredientes) {
        p.getIngredientes().forEach((ingrName, cantEnGramos) -> {
            Ingrediente ingr = repoIngr.getByName(ingrName);
            if (ingr != null) {
                Double cals = (ingr.getCalories() * cantEnGramos) * 0.01;
                ingredientes.add(new IngredienteDePlatoDTO(ingrName, cantEnGramos, cals));
            } else {
                ingredientes.add(new IngredienteDePlatoDTO(ingrName, cantEnGramos, null));
            }
        });
    }

    public List<IngredienteDePlatoDTO> getReceta(PlatoReqDTO p) {
        List<IngredienteDePlatoDTO> resDTO = new ArrayList<>();
        String nombrePlato = p.getNombre();
        Plato plato = repoPlatos.getByName(nombrePlato);

        if (plato != null) {
            fillIngredienteDePlatoDTOList(plato, resDTO);
        }

        return resDTO;
        //Double totalCals = 0.0;

        //if (p.getNombre().equalsIgnoreCase("Pizza")){
        //    for (IngredienteDelPlato i : pizza.getIngredientes()) {
        //        resDTO.getIngredientes().add(new IngredienteDTO(i.getIngrediente(),
        //                i.getIngrediente().getCalories() * 0.01 * i.getPeso(),
        //                i.getPeso()));
        //    }
        //}
        //totalCals = resDTO.getIngredientes().stream()
        //        .mapToDouble(IngredienteDTO::getCalorias)
        //        .sum();

        //resDTO.setIngredienteMasCalorico(
        //        resDTO.getIngredientes().stream().max(IngredienteDTO::getCalorias);
        //);
        //resDTO.setTotalCalories(totalCals);

        //return resDTO;
    }


}
