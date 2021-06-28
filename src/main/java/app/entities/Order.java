package app.entities;

import lombok.Data;

@Data
public class Order {
//version on laptop
    private int orderId;
    private int customerId;
    private String dateOfOrder;
    private int productId;
    private String status;

    public Order(int customerId, String dateOfOrder, int productId, String status) {
        this.customerId = customerId;
        this.dateOfOrder = dateOfOrder;
        this.productId = productId;
        this.status = status;
    }
}


