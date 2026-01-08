package models;
import br.com.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends PersistenceEntity implements Serializable {

    @Column(name = "descripton")
    private String descricionCategory;


    @Override
    public String toString() {
        return id + " - " + descricionCategory + '\'' ;
    }
}
