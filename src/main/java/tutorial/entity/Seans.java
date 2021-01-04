package tutorial.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seans implements Cloneable{
    public enum Lektor {
        Brak,Tak
    }

    public enum Napisy {
        Brak,Tak
    }

    @Id
    @EqualsAndHashCode.Include
    private String seansId;


    @NotNull
    @NotEmpty
    private Seans.Lektor lektor;

    @NotNull
    @NotEmpty
    private Seans.Napisy napisy;

    @NotNull
    @NotEmpty
    private LocalDate data;

    @NotNull
    @NotEmpty
    private String godzina;

    @NotNull
    @NotEmpty
    private Sala sala;

    @NotNull
    @NotEmpty
    private Film film;

    @Override
    protected Seans clone() throws CloneNotSupportedException {
        return (Seans)super.clone();
    }

    @Override
    public String toString(){
        return lektor + " " + napisy + " " + data + " " + godzina + " " + sala + " " + film;
    }
}