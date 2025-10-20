package RMI;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 20241132L;
    String id, customerCode, orderDate, shippingType, orderCode;

    public Order() {
    }

    public Order(String id, String customerCode, String orderDate, String shippingType) {}
}
