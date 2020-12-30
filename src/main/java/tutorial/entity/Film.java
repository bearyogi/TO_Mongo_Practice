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
public class Film implements Cloneable{
    public enum Ograniczenie {
        Brak,Siedem,Trzynascie,Szesnascie,Dorosly
    }
    @Id
    @EqualsAndHashCode.Include
    private String filmId;

    @NotNull
    @NotEmpty
    private String tytul;

    @NotNull
    @NotEmpty
    private String rokProdukcji;

    @NotNull
    @NotEmpty
    private String gatunek;

    @NotNull
    @NotEmpty
    private String rezyseria;

    @NotNull
    @NotEmpty
    private String czasTrwania;

    @NotNull
    @NotEmpty
    private Film.Ograniczenie ograniczenieWiekowe;

    @Override
    protected Film clone() throws CloneNotSupportedException {
        return (Film)super.clone();
    }

    @Override
    public String toString() {return tytul + " " + rokProdukcji + " " + gatunek + " " + rezyseria + " " + czasTrwania + " " + ograniczenieWiekowe;}
}