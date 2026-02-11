package br.com.service;

import br.com.models.Category;
import br.com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository; // Injeção do repositório para persistência real

    public void adicionarNovaCategoria(String nome_categoria) {
        // Busca no banco se já existe uma categoria com esse nome [cite: 12]
        boolean exists = categoryRepository.findAll().stream()
                .anyMatch(c -> c.getDescricionCategory().equalsIgnoreCase(nome_categoria));

        if (exists) {
            throw new IllegalArgumentException("Categoria já existe");
        }

        Category category = new Category();
        // Não definimos o ID manualmente (categoryId++), o banco cuidará disso
        category.setDescricionCategory(nome_categoria);

        categoryRepository.save(category); // Salva permanentemente no PostgreSQL
        System.out.println("Categoria Inserida com sucesso");
    }

    public List<Category> listarCategoria() {
        List<Category> categoryList = categoryRepository.findAll();

        if (categoryList.isEmpty()) {
            throw new IllegalStateException("Lista de Categorias Vazia");
        }

        categoryList.forEach(System.out::println);
        return categoryList;
    }

    public Category encontrar_categorias(Long id_categoria) {
        // Utiliza o método nativo do JpaRepository para buscar por ID [cite: 18]
        return categoryRepository.findById(id_categoria)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));
    }
}
