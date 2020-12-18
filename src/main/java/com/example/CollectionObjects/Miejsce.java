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
public class Miejsce {

    @Id
    @EqualsAndHashCode.Include
    private Integer miejsceId;

    private Integer numerMiejsca;
    private Integer numerRzedu;
}