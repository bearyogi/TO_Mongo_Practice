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
public class Miejsce implements Cloneable{
    @Id
    @EqualsAndHashCode.Include
    private String miejsceId;

    @NotNull
    @NotEmpty
    private String numerMiejsca;

    @NotNull
    @NotEmpty
    private String numerRzedu;

    @Override
    protected Miejsce clone() throws CloneNotSupportedException {
        return (Miejsce)super.clone();
    }
    @Override
    public String toString(){return numerMiejsca + " " + numerRzedu;}
}