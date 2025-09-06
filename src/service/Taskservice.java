package service;

import models.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Taskservice {
    private List<Task> list_task;
    private int id_taskservice;

    public Taskservice (){
        this.list_task = new ArrayList<>();
        this.id_taskservice = 1;
    }

    public void adicionarTarefas(String descricions, LocalDateTime newDatetimeSTART, String newtimeEND){

        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
        if(newDatetimeSTART.isBefore(LocalDateTime.now())){
            System.out.println("Não é possível adicionar tarefas com datas passadas ");
            return;
        }
        for (Task t : list_task){
            if(t.getDateTimeSTART().toLocalDate().equals(newDatetimeSTART.toLocalDate())){
                LocalDateTime start = t.getDateTimeSTART();
                LocalDateTime end = LocalDateTime.of(start.toLocalDate(), LocalTime.parse(t.getTimeEND(), hourFormatter));

                LocalDateTime endNew = LocalDateTime.of(newDatetimeSTART.toLocalDate(), LocalTime.parse(newtimeEND, hourFormatter));
                boolean over = !(endNew.isBefore(start) || newDatetimeSTART.isAfter(end));

                if(over){
                    System.out.println("❌ Conflito de horário com a tarefa: " + t.getDescricion());
                    return;
                }
            }
        }
        Task task = new Task(id_taskservice++, descricions, newDatetimeSTART, newtimeEND);
        list_task.add(task);
        System.out.println("Tarefa Adicionada com sucesso!!");
    }

    public void listarTarefas(){
        if(list_task.isEmpty()){
            System.out.println("A lista está vazia, adicione um item");
            return;
        }

        for(Task task : list_task){
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
        list_task.remove(Toremoved);
        System.out.println("Tarefa excluída");

    }

    public void alterarTarefa(int id, String alterDescricion){
        Task updateDescricion = validarID(id);
        updateDescricion.setDescricion(alterDescricion);
        System.out.println("Descrição da tarefa alterada");
    }

    private Task validarID(int id){
        return list_task.stream() //percorre a lista
                .filter(t -> t.getId() == id) //filtra apenas os elementos que possuem o 'ID' igual o passado no parâmetro
                .findFirst() //acha o primeiro encontrado
                .orElseThrow(() -> new NoSuchElementException("ID não encontrado")); //se não encontrar nenhum, estoura a exceção
    }

    public List<Task> filtrarPorData (LocalDate dataParaFiltrar){
        return list_task.stream()
                .filter(t -> t.getDateTimeSTART().toLocalDate().equals(dataParaFiltrar))
                .collect(Collectors.toList());
    }
}
