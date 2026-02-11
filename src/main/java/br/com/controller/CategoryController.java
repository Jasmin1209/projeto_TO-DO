package br.com.controller;

import br.com.models.Category;
import br.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listarTodas(){
        return ResponseEntity.ok(categoryService.listarCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.encontrar_categorias(id));
    }

    @PostMapping
    public ResponseEntity<String> salvar (@RequestBody String nome){
        categoryService.adicionarNovaCategoria(nome);
        return ResponseEntity.ok("Categoria '" + nome + "' criada com sucesso!");
    }
}
