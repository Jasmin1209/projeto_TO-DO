package br.com.controller;

import br.com.models.Task;
import br.com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping //acessado via GET
    public List<Task> listar(){
        return taskService.listarTarefas();
    }

    @PostMapping //acessado via POST (enviando JSON no corpo)
    public ResponseEntity<Task> criar (@RequestBody Task task){
        return ResponseEntity.ok(taskService.adicionarTarefas(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        taskService.removerTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
