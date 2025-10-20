package RMI;

import RMI.ObjectService;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TaoOrderCode {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService service = (ObjectService) registry.lookup("RMIObjectService");
        Serializable receiveData = service.requestObject("B22DCCN018", "VbAEkw5g");
        Order order = (Order) receiveData;
        String[] date = order.orderDate.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(order.shippingType.toUpperCase().substring(0, 2));
        sb.append(order.customerCode.substring(order.customerCode.length() - 3));
        sb.append(date[2]).append(date[1]);
        order.orderCode = sb.toString();
        service.submitObject("B22DCCN018", "VbAEkw5g", order);
    }
}
