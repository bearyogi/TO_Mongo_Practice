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
public class Sala {

    @Id
    @EqualsAndHashCode.Include
    private Integer salaId;

    private Integer nrSali;
    private String lokalizacja;
    private String klasa;
}