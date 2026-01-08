package service;

import models.Category;
import models.Task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Taskservice {
    private List<Task> taskList;
    private Long taskId;

    public Taskservice (){
        this.taskList = new ArrayList<>();
        this.taskId = 1L;
    }

    public void adicionarTarefas(String description,
                                 LocalDate startDate,
                                 LocalTime startHour,
                                 LocalTime endHour,
                                 Category category){

        if(startDate.isBefore(LocalDate.now())){ //verifica se a data de inicio inserida não é anterior a data de hoje
            throw new IllegalArgumentException("Não é possível adicionar tarefas com datas passadas");
        }

            for (Task t : taskList) {
                if (t.getDateStart().equals(startDate)) { // só acontece se as datas forem iguais
                    /*
                     - Se a nova tarefa não terminar antes da antiga iniciar
                     - Se a nova tarefa nao iniciar depois da antiga terminar
                     = vai ter conflito de horário, pois uma vai acabar sobrescrevendo
                     o horário da outra.
                     */
                    boolean conflito = !(endHour.isBefore(t.getHourStart()) || startHour.isAfter(t.getHourEnd()));

                    if (conflito) { // se a verificação for verdadeira então não será possível adicionar novas tarefas
                        throw new IllegalStateException("❌ Conflito de horário com a tarefa: " + t.getDescription());

                    }
                }
            }

            Task task = new Task();
            task.setId(taskId++);
            task.setDescription(description);
            task.setDateStart(startDate);
            task.setHourStart(startHour);
            task.setHourEnd(endHour);
            task.setCompleted(false);
            task.setCategory(category);
            taskList.add(task);
            System.out.println("Tarefa Adicionada com sucesso!!");

    }

    public void listarTarefas(){
        if(taskList.isEmpty()){
            System.out.println("A lista está vazia, adicione um item");
            return;
        }

        for(Task task : taskList){
            System.out.println(task);
        }
    }

    public void completarTarefas(Long id){
        Task toCompleted = validarID(id);
        toCompleted.setCompleted(true);
        System.out.println("Tarefa concluída");
    }

    public void removerTarefa(Long id){
        Task Toremoved = validarID(id);
        taskList.remove(Toremoved);
        System.out.println("Tarefa excluída");

    }

    public void alterarTarefa(Long id, String alterDescricion){
        Task updateDescricion = validarID(id);
        updateDescricion.setDescription(alterDescricion);
        System.out.println("Descrição da tarefa alterada");
    }

    private Task validarID(Long id){
        return taskList.stream() //percorre a lista
                .filter(t -> t.getId().equals(id)) //filtra apenas os elementos que possuem o 'ID' igual o passado no parâmetro
                .findFirst() //acha o primeiro encontrado
                .orElseThrow(() ->
                        new NoSuchElementException("ID não encontrado")); //se não encontrar nenhum, estoura a exceção
    }

    public List<Task> filtrarPorData (LocalDate dataParaFiltrar) {
        return taskList.stream()
                .filter(t -> t.getDateStart().equals(dataParaFiltrar))
                .collect(Collectors.toList());
    }
}
