package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int identifica_a_tarefa;
    private String descricao_da_tarefa;
    private boolean completou;
    private LocalDate data_de_inicio;
    private LocalTime hora_de_inicio;
    private LocalTime hora_de_termino;
    private Category category;

    public  Task (int identifica_a_tarefa, String descricao_da_tarefa, LocalDate data_de_inicio, LocalTime hora_de_inicio, LocalTime hora_de_termino, Category category){
        this.identifica_a_tarefa = identifica_a_tarefa;
        this.descricao_da_tarefa = descricao_da_tarefa;
        this.completou = false;
        this.data_de_inicio = data_de_inicio;
        this.hora_de_inicio = hora_de_inicio;
        this.hora_de_termino = hora_de_termino;
        this.category = category;
    }

    public int getId() {return identifica_a_tarefa;}

    public String getDescricion() {
        return descricao_da_tarefa;
    }
    public void setDescricion(String descricao_da_tarefa) {
        this.descricao_da_tarefa = descricao_da_tarefa;
    }

    public LocalDate getDateSTART() {return data_de_inicio;}
    public void setDateSTART(LocalDate data_de_inicio) {
        this.data_de_inicio = data_de_inicio;
    }

    public LocalTime getTimeSTART() {return hora_de_inicio;}
    public void setTimeSTART(LocalTime hora_de_inicio) {this.hora_de_inicio = hora_de_inicio;}

    public LocalTime getTimeEND(){ return hora_de_termino;}
    public void setDateTimeEND(LocalTime hora_de_termino){this.hora_de_termino = hora_de_termino;}

    public boolean getIsCompleted() {
        return completou;
    }
    public void setCompleted(boolean completou) {
        this.completou = completou;
    }

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}

    @Override
    public String toString() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        return (completou ? "[X]" : "[ ]") +
                identifica_a_tarefa + " -" +
                descricao_da_tarefa + "(Agendado: " +
                data_de_inicio.format(formatterDate) + " | " +
                hora_de_inicio.format(formatterHour) + " - " +
                hora_de_termino.format(formatterHour) + ") " +
                "CATEGORIA: " + category.getNome_categoria();
    }
}
