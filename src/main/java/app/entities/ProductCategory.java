package app.entities;

import lombok.Data;

@Data
public class ProductCategory {
    private int categoryId;
    private String categoryName;

    public ProductCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
