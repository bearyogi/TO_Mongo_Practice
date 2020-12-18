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
public class Bilet {

    @Id
    @EqualsAndHashCode.Include
    private Integer biletId;
    private Boolean ulga;
    private Integer cena;
}