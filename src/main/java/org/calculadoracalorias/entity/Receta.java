package org.calculadoracalorias.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Receta {
    String nombre;
    Map<String, Double> ingredientes; //TODO: Cambiar esto a List para poder deserializar

    public Double calcularPeso() {
        return ingredientes.values().stream()
                .mapToDouble(d -> d)
                .sum();
    }
}
