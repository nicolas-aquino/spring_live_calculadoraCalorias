package org.calculadoracalorias.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.calculadoracalorias.entity.Ingrediente;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredienteDTO {
    String nombreIngrediente;
    Double caloriesPer100grams;

    public IngredienteDTO(Ingrediente ingrediente) {
        nombreIngrediente = ingrediente.getName();
        caloriesPer100grams = ingrediente.getCalories();
    }
}
