package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDTO {
    private int categoryId;
    private String categoryName;
}
