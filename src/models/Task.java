package models;

import br.com.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table (name = "tarefas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends PersistenceEntity implements Serializable {


    @Column(nullable = false)
    private String description;

    @Column(name = "status")
    private boolean completed;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "hour_start")
    private LocalTime hourStart;

    @Column(name = "hour_end")
    private LocalTime hourEnd;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        return (completed ? "[X]" : "[ ]") +
                id + " -" +
                description + "(Agendado: " +
                dateStart.format(formatterDate) + " | " +
                hourStart.format(formatterHour) + " - " +
                hourEnd.format(formatterHour) + ") " +
                "CATEGORIA: " + category.getDescricionCategory();
    }
}
