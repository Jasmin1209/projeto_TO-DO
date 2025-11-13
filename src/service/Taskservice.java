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
    private List<Task> lista_das_tarefas;
    private int identifica_a_tarefa_service;

    public Taskservice (){
        this.lista_das_tarefas = new ArrayList<>();
        this.identifica_a_tarefa_service = 1;
    }

    public void adicionarTarefas(String descricao, LocalDate dataInicio, LocalTime hora_inicio, LocalTime horaFinal){

        if(dataInicio.isBefore(LocalDate.now())){ //verifica se a data de inicio inserida não é anterior a data de hoje
            System.out.println("Não é possível adicionar tarefas com datas passadas ");
            return;
        }

            for (Task t : lista_das_tarefas) {
                if (t.getDateSTART().equals(dataInicio)) { // só acontece se as datas forem iguais
                    /*
                     - Se a nova tarefa não terminar antes da antiga iniciar
                     - Se a nova tarefa nao iniciar depois da antiga terminar
                     = vai ter conflito de horário, pois uma vai acabar sobrescrevendo
                     o horário da outra.
                     */
                    boolean conflito = !(horaFinal.isBefore(t.getTimeSTART()) || hora_inicio.isAfter(t.getTimeEND()));

                    if (conflito) { // se a verificação for verdadeira então não será possível adicionar novas tarefas
                        System.out.println("❌ Conflito de horário com a tarefa: " + t.getDescricion());
                        return;
                    }
                }
            }

            Task task = new Task(identifica_a_tarefa_service++, descricao, dataInicio, hora_inicio, horaFinal);
            lista_das_tarefas.add(task);
            System.out.println("Tarefa Adicionada com sucesso!!");

    }

    public void listarTarefas(){
        if(lista_das_tarefas.isEmpty()){
            System.out.println("A lista está vazia, adicione um item");
            return;
        }

        for(Task task : lista_das_tarefas){
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
        lista_das_tarefas.remove(Toremoved);
        System.out.println("Tarefa excluída");

    }

    public void alterarTarefa(int id, String alterDescricion){
        Task updateDescricion = validarID(id);
        updateDescricion.setDescricion(alterDescricion);
        System.out.println("Descrição da tarefa alterada");
    }

    private Task validarID(int id){
        return lista_das_tarefas.stream() //percorre a lista
                .filter(t -> t.getId() == id) //filtra apenas os elementos que possuem o 'ID' igual o passado no parâmetro
                .findFirst() //acha o primeiro encontrado
                .orElseThrow(() -> new NoSuchElementException("ID não encontrado")); //se não encontrar nenhum, estoura a exceção
    }

    public List<Task> filtrarPorData (LocalDate dataParaFiltrar) {
        return lista_das_tarefas.stream()
                .filter(t -> t.getDateSTART().equals(dataParaFiltrar))
                .collect(Collectors.toList());
    }
}
