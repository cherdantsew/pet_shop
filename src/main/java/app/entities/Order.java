package app.entities;

import lombok.Data;

@Data
public class Order {
//version on laptop
    private int orderId;
    private int customer_id;
    private String dateOfOrder;
    private int product_id;
    private String status;

    public Order(int customer_id, String dateOfOrder, int product_id, String status) {
        this.customer_id = customer_id;
        this.dateOfOrder = dateOfOrder;
        this.product_id = product_id;
        this.status = status;
    }
}


