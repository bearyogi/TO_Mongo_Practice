package tutorial.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Zamowienie implements Cloneable{

public enum Platnosc {
    Karta,Gotowka
}
public enum Status{
    Zaplacone,DoZaplaty
}

    @Id
    @EqualsAndHashCode.Include
    private String zamowienieId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Zamowienie.Platnosc typPlatnosci;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Zamowienie.Status status;

    @NotNull
    @NotEmpty
    private String liczbaBiletow;

    @NotNull
    @NotEmpty
    private String kwota;

    @NotNull
    @NotEmpty
    private Klient klient;

    @NotNull
    @NotEmpty
    private Seans seans;

    @Override
    public Zamowienie clone() {
        try {
            return (Zamowienie)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String toString() {return typPlatnosci + " " + status + " " + liczbaBiletow + " " + kwota + " " + klient + " " + seans;}
}