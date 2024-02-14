package org.calculadoracalorias.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.calculadoracalorias.entity.Plato;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlatoResDTO {
    String nombre;
    Double peso;
    List<IngredienteDePlatoDTO> ingredientes;
}
