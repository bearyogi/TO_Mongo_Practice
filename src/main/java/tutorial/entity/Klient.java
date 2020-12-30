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
public class Klient implements Cloneable{

    @Id
    @EqualsAndHashCode.Include
    private String klientId;

    @NotNull
    @NotEmpty
    private String imie;

    @NotNull
    @NotEmpty
    private String nazwisko;

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String adres;

    private String tel;

    public String getId(){return klientId;}
    @Override
    public Klient clone() {
        try {
            return (Klient)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String toString() {return imie + " " + nazwisko + " " + email + " " + adres + " " + tel;}
}
