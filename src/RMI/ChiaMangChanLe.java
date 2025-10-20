package RMI;

import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ChiaMangChanLe {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");
        byte[] receiveData = byteService.requestData("B22DCCN018", "MjLDGzjo");
        List<Byte> even = new ArrayList<>();
        List<Byte> odd = new ArrayList<>();
        for(byte b : receiveData){
            if(b%2==0) even.add(b);
            else odd.add(b);
        }
        byte[] result = new byte[even.size() + odd.size()];
        int idx = 0;
        for(byte b : even){
            result[idx++] = b;
        }
        for(byte b : odd){
            result[idx++] = b;
        }
        byteService.submitData("B22DCCN018", "MjLDGzjo", result);
    }
}
