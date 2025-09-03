package models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String descricion;
    private boolean completed;
    private LocalDateTime dateTimeSTART;
    private LocalTime timeEND;

    public  Task (int id, String descricion, LocalDateTime dateTimeSTART, LocalTime timeEND){
        this.id = id;
        this.descricion = descricion;
        this.completed = false;
        this.dateTimeSTART = dateTimeSTART;
        this.timeEND = timeEND;
    }

    public int getId() {
        return id;
    }

    public String getDescricion() {
        return descricion;
    }

    public LocalDateTime getTimeSTART() {
        return dateTimeSTART;
    }

    public LocalTime getTimeEND(){ return timeEND;}

    public void setTimeSTART(LocalDateTime dateTimeSTART) {
        this.dateTimeSTART = dateTimeSTART;
    }

    public void setDateTimeEND(LocalTime timeEND){this.timeEND = timeEND;}


    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public boolean getIsCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return (completed ? "[X]" : "[ ]") + id + " -" + descricion + "(Agendado: " + dateTimeSTART.format(formatter) + " - " +timeEND+ ")";
    }
}
