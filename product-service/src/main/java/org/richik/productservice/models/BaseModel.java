package org.richik.productservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    boolean isDeleted;
}
