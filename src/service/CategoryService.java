package service;

import models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private List<Category> lista_de_categorias = new ArrayList<>();
    private int id_categoryService = 1;

    public void adicionarNovaCategoria(String nome_categoria){
        Category categoryservice = new Category(id_categoryService++, nome_categoria);
        lista_de_categorias.add(categoryservice);
        System.out.println("Categoria Inserida com sucesso");
    }

    public List<Category> listarCategoria(){
        if(lista_de_categorias.isEmpty()){
            System.out.println("Lista de Categorias Vazia");
        }else{
            lista_de_categorias.forEach(System.out::println);
        }
        return lista_de_categorias;
    }

    public Category encontrar_categorias(int id_categoria){
        return lista_de_categorias.stream()
                .filter(c -> c.getId_categoria() == id_categoria)
                .findFirst()
                .orElse(null);
    }
}
