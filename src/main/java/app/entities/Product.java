package app.entities;

import lombok.Data;

@Data
public class Product {
    private int productId;
    private String productCategory;
    private String productName;
    private String productPrice;
    private String productDescription;

    public Product(int productId, String productCategory, String productName, String productPrice, String productDescription) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }
}
