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
public class Zamowienie {

    @Id
    @EqualsAndHashCode.Include
    private Integer zamowienieId;

    private String typPlatnosci;
    private String status;
    private Integer liczbaBiletow;
    private Integer kwota;
}