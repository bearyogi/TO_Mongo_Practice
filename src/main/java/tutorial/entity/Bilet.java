package tutorial.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bilet implements Cloneable{
    public enum Ulga {
        Brak,Uczen,Senior
    }
    @Id
    @EqualsAndHashCode.Include
    private String biletId;

    @NotNull
    @NotEmpty
    private Bilet.Ulga ulga;

    @NotNull
    @NotEmpty
    private String cena;

    public Bilet clone() {
        try {
            return (Bilet)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String toString(){
        return ulga + " " + cena;
    }
}