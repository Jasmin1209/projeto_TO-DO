package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id_task;
    private String descricion;
    private boolean completed;
    private LocalDateTime dateTimeSTART;
    private String timeEND;

    public  Task (int id_task, String descricion, LocalDateTime dateTimeSTART, String timeEND){
        this.id_task = id_task;
        this.descricion = descricion;
        this.completed = false;
        this.dateTimeSTART = dateTimeSTART;
        this.timeEND = timeEND;
    }

    public int getId() {
        return id_task;
    }

    public String getDescricion() {
        return descricion;
    }
    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public LocalDateTime getDateTimeSTART() {
        return dateTimeSTART;
    }
    public void setTimeSTART(LocalDateTime dateTimeSTART) {
        this.dateTimeSTART = dateTimeSTART;
    }

    public String getTimeEND(){ return timeEND;}
    public void setDateTimeEND(String timeEND){this.timeEND = timeEND;}

    public boolean getIsCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return (completed ? "[X]" : "[ ]") + id_task + " -" + descricion + "(Agendado: " + dateTimeSTART.format(formatter) + " - " +timeEND+ ")";
    }
}
