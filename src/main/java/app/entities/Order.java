package app.entities;

import java.util.Objects;

public class Order {

    private int order_id;
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

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", dateOfOrder='" + dateOfOrder + '\'' +
                ", product_id=" + product_id +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order orders = (Order) o;
        return order_id == orders.order_id && customer_id == orders.customer_id && product_id == orders.product_id && dateOfOrder.equals(orders.dateOfOrder) && status.equals(orders.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, customer_id, dateOfOrder, product_id, status);
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getStatus() {
        return status;
    }
}
