package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private int id;
    private String name;
    private String price;
    private String description;
}
