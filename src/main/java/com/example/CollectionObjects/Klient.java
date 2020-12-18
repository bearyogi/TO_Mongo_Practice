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
public class Klient {

    @Id
    @EqualsAndHashCode.Include
    private Integer klientId;

    private String imie;
    private String nazwisko;
    private String email;
    private String adres;
    private Integer tel;
}
