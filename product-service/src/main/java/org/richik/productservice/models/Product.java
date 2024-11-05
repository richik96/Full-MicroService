package org.richik.productservice.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {

    private String name;
    private Double price;
    private String description;
}
