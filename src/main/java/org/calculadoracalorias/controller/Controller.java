package org.calculadoracalorias.controller;

import org.calculadoracalorias.dto.request.PlatoReqDTO;
import org.calculadoracalorias.dto.response.IngredienteDTO;
import org.calculadoracalorias.dto.response.IngredienteDePlatoDTO;
import org.calculadoracalorias.dto.response.PlatoResDTO;
import org.calculadoracalorias.entity.Ingrediente;
import org.calculadoracalorias.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class Controller {
    @Autowired
    MenuService menuService;

    @GetMapping("/getIngredientes")
    public ResponseEntity<List<IngredienteDTO>> getIngredientes() {
        List<IngredienteDTO> res = new ArrayList<>();
        List<Ingrediente> ingredientes = menuService.getAllIngredientes();
        ingredientes.forEach(i -> res.add(new IngredienteDTO(i)));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getPlatos")
    public ResponseEntity<List<PlatoResDTO>> getPlatos() {
        List<PlatoResDTO> res = menuService.getAllPlatos();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/receta")
    public ResponseEntity<List<IngredienteDePlatoDTO>> getReceta(@RequestBody PlatoReqDTO p) {
        List<IngredienteDePlatoDTO> res = menuService.getReceta(p);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
