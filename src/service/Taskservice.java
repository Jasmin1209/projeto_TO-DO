package service;

import models.Task;

import java.util.ArrayList;
import java.util.List;

public class Taskservice {
    private List<Task> tasks;
    private int id_next;

    public Taskservice (){
        this.tasks = new ArrayList<>();
        this.id_next = 1;
    }

    public void adicionarTarefas(String descricions){
        Task task = new Task(id_next++, descricions);
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
        for(Task t : tasks){
            if(t.getId() == id){
                t.setCompleted(true);
                System.out.println("Tarefa concluída");
                return;
            }
        }
        System.out.println("A tarefa não foi encontrada");
    }

    public void removerTarefa(int id){
        Task removed = null;
        for(Task t : tasks){
            if(t.getId() == id){
                removed = t;
                break;
            }
        }

        if(removed != null){
            tasks.remove(removed);
            System.out.println("Tarefa excluída");
        }else {
            System.out.println("A tarefa não foi encontrada");
        }
    }

    public void alterarTarefa(int id, String alterDescricion){
        Task updateDescricion = null;
        for(Task t : tasks){
            if(t.getId() == id){
                updateDescricion = t;
                break;
            }
        }

        if(updateDescricion != null){
            updateDescricion.setDescricion(alterDescricion);
            System.out.println("Descrição da tarefa alterada");
        }else{
            System.out.println("A tarefa não foi encontrada");
        }
    }
}
