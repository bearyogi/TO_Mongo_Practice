package com.example.CollectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {

    @Id
    @EqualsAndHashCode.Include
    private Integer filmId;

    private String tytul;
    private Integer rokProdukcji;
    private String gatunek;
    private String rezyseria;
    private Integer czasTrwania;
    private Integer ograniczenieWiekowe;
}