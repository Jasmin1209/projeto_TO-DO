package service;

import models.Task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class Taskservice {
    private List<Task> tasks;
    private int id_next;

    public Taskservice (){
        this.tasks = new ArrayList<>();
        this.id_next = 1;
    }

    public void adicionarTarefas(String descricions, LocalDateTime dateTimeSTART, LocalTime timeEND){
        for (Task t : tasks){
            // Verifica se é no mesmo dia
            if (t.getTimeSTART().toLocalTime().equals(dateTimeSTART.toLocalTime())) {
                // Conflito se horários se sobrepõem
                if (!(timeEND.isBefore(t.getTimeSTART().toLocalTime()) ||
                        dateTimeSTART.toLocalTime().isAfter(t.getTimeEND()))) {
                    System.out.println("❌ Conflito de horário com a tarefa: " + t.getDescricion());
                    return;
                }
            }
        }
        Task task = new Task(id_next++, descricions, dateTimeSTART, timeEND);
        tasks.add(task);
        System.out.println("Tarefa Adicionada com sucesso!!");
    }

    public void listarTarefas(){
        if(tasks.isEmpty()){
            System.out.println("A lista está vazia, adicione um item");
            return;
        }

        for(Task task : tasks){
            System.out.println(task);
        }
    }

    public void completarTarefas(int id){
        Task toCompleted = validarID(id);
        toCompleted.setCompleted(true);
        System.out.println("Tarefa concluída");
    }

    public void removerTarefa(int id){
        Task Toremoved = validarID(id);
        tasks.remove(Toremoved);
        System.out.println("Tarefa excluída");

    }

    public void alterarTarefa(int id, String alterDescricion){
        Task updateDescricion = validarID(id);
        updateDescricion.setDescricion(alterDescricion);
        System.out.println("Descrição da tarefa alterada");
    }

    private Task validarID(int id){
        return tasks.stream() //percorre a lista
                .filter(t -> t.getId() == id) //filtra apenas os elementos que possuem o 'ID' igual o passado no parâmetro
                .findFirst() //acha o primeiro encontrado
                .orElseThrow(() -> new NoSuchElementException("ID não encontrado")); //se não encontrar nenhum, estoura a exceção
    }
}
