package br.com.service;

import br.com.models.Task;
import br.com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository; // Injeção de dependência correta

    public Task adicionarTarefas(Task task) {
        if (task.getDateStart().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível adicionar tarefas com datas passadas");
        }

        // A lógica de conflito agora consulta o banco de dados
        List<Task> tarefasNoDia = taskRepository.findAll().stream()
                .filter(t -> t.getDateStart().equals(task.getDateStart()))
                .toList();

        for (Task t : tarefasNoDia) {
            boolean conflito = !(
                    task.getHourEnd().isBefore(t.getHourStart()) || task.getHourStart().isAfter(t.getHourEnd())
            );

            if (conflito) {
                throw new IllegalStateException("❌ Conflito de horário com a tarefa: " + t.getDescription());
            }
        }
        task.setCompleted(false);

       return taskRepository.save(task); // O JPA salva no banco e gera o ID automaticamente

    }

    public List<Task> listarTarefas() {
        return taskRepository.findAll();
    }

    public void removerTarefa(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new NoSuchElementException("ID não encontrado");
        }
        taskRepository.deleteById(id);
    }
}
