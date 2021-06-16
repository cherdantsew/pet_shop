package app.entities;


import java.util.Objects;

public class ProductCategory {

    private int category_id;
    private String category_name;

    public ProductCategory (int category_id, String category_name){
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return category_id == that.category_id && category_name.equals(that.category_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, category_name);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
