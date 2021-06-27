package app.entities;

import lombok.Data;

@Data
public class Product {
    private int product_id;
    private String product_category;
    private String product_name;
    private String product_price;
    private String product_description;

    public Product(int product_id, String product_category, String product_name, String product_price, String product_description) {
        this.product_id = product_id;
        this.product_category = product_category;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_description = product_description;
    }
}
