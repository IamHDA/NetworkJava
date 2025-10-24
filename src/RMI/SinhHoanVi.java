package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class SinhHoanVi {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        DataService service = (DataService) registry.lookup("RMIDataService");
        Object receiveData = service.requestData("B22DCCN018", "bJjAOjoM");
        int[] arr = Arrays.stream(receiveData.toString().replace(",", " ").split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int i = arr.length - 2;

        while(i >= 0 && arr[i] >= arr[i + 1]) --i;

        if(i < 0) Arrays.sort(arr);
        else{
            int j = arr.length - 1;
            while(arr[j] <= arr[i]) --j;

            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;

            int left = i + 1;
            int right = arr.length - 1;

            while(left < right){
                int tmp1 = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp1;
                ++left;
                --right;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int n : arr){
            if(!sb.isEmpty()) sb.append(",");
            sb.append(n);
        }

        service.submitData("B22DCCN018", "bJjAOjoM", sb.toString());
    }
}
