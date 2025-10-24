package RMI;

import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SinhToHop {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        DataService service = (DataService) registry.lookup("RMIDataService");
        Object receiveData = service.requestData("B22DCCN018", "bJjAOjoM");
        System.out.println(receiveData);
    }
}
