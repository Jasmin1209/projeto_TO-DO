package br.com.models;

import br.com.infrastructure.entity.PersistenceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category extends PersistenceEntity implements Serializable {

    @Column(name = "descripton", nullable = false)
    private String descricionCategory;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Task> tasks;

    @Override
    public String toString(){
        return id + " - " + descricionCategory;
    }

}
