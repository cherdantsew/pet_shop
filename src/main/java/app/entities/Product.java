package app.entities;

import java.util.Objects;

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

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_category() {
        return product_category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product products = (Product) o;
        return product_id == products.product_id && product_category.equals(products.product_category) && product_name.equals(products.product_name) && product_price.equals(products.product_price) && product_description.equals(products.product_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_category, product_name, product_price, product_description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_category='" + product_category + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_description='" + product_description + '\'' +
                '}';
    }
}
