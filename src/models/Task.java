package models;

public class Task {
    private int id;
    private String descricion;
    private boolean completed;

    public  Task (int id, String descricion){
        this.id = id;
        this.descricion = descricion;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getDescricion() {
        return descricion;
    }

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
        return (completed ? "[X]" : "[ ]") + id + " -" + descricion;
    }
}
