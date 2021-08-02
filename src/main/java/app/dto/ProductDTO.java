package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private int productId;
    private String productName;
    private String productPrice;
    private String productDescription;
}
