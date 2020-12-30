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
public class Sala implements Cloneable{
    public enum Lokalizacja {
        Polnoc,Poludnie,Zachod,Wschod
    }
    public enum Klasa {
        Premium,Standard,Ekonomiczna
    }
    @Id
    @EqualsAndHashCode.Include
    private String salaId;

    @NotNull
    @NotEmpty
    private String nrSali;

    @NotNull
    @NotEmpty
    private Sala.Lokalizacja lokalizacja;

    @NotNull
    @NotEmpty
    private Sala.Klasa klasa;


    @Override
    protected Sala clone() throws CloneNotSupportedException {
        return (Sala)super.clone();
    }
    @Override
    public String toString(){
        return nrSali +  " " + lokalizacja + " " + klasa;
    }
}