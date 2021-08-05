package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategory {
    private int categoryId;
    private String categoryName;

    public ProductCategory(String newCategoryName) {
        this.categoryName = newCategoryName;
    }
}
