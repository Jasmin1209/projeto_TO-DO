package service;

import models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryService {
    private List<Category> categoryList = new ArrayList<>();
    private Long categoryId = 1L;

    public void adicionarNovaCategoria(String nome_categoria){

        boolean exists = categoryList.stream()
                .anyMatch(c -> c.getDescricionCategory()
                        .equalsIgnoreCase(nome_categoria));

        if(exists){
            throw new IllegalArgumentException("Categoria já existe");
        }
        Category categoryservice = new Category();
        categoryservice.setId(categoryId++);
        categoryservice.setDescricionCategory(nome_categoria);
        categoryList.add(categoryservice);
        System.out.println("Categoria Inserida com sucesso");
    }

    public List<Category> listarCategoria(){
        if(categoryList.isEmpty()){
            throw new IllegalStateException("Lista de Categorias Vazia");
        }else{
            categoryList.forEach(System.out::println);
        }
        return categoryList;
    }

    public Category encontrar_categorias(Long id_categoria){
        return categoryList.stream()
                .filter(c -> c.getId().equals(id_categoria))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Categoria não encontrado"));
    }
}
