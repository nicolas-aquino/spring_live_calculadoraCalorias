package org.calculadoracalorias.repository;


@org.springframework.stereotype.Repository
public class IngredientesRepository {

    public PersonajeRepository() {
        this.personajesSW = List.of(loadPersonajes());
    }


    private Personaje[] loadPersonajes(){
        ObjectMapper mapperJSON  = new ObjectMapper();
        String ruta ="src/main/resources/static/starwars_data.json";

        try {
            return mapperJSON.readValue(new File(ruta), Personaje[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
