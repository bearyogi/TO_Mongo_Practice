package com.example.CollectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seans {

    @Id
    @EqualsAndHashCode.Include
    private Integer seansId;

    private String lektor;
    private String napisy;
    private Date data;
    private Integer godzina;
}