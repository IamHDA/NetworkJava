package RMI;

import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class DemSoLanXuatHienCuaKyTu {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");
        String receiveData = service.requestCharacter("B22DCCN018", "cIQ62aOg");
        Map<Character, Integer> map = new HashMap<>();
        char[] cArray = receiveData.toCharArray();
        for(char c : cArray){
            if(!map.containsKey(c)) map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(char c : cArray){
            if(map.containsKey(c)){
                if(!sb.isEmpty()) sb.append(" ");
                sb.append('"').append(c).append('"').append(": ").append(map.get(c)).append(",");
                map.remove(c);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.insert(0, "{");
        sb.append("}");
        System.out.println(sb.toString());
        service.submitCharacter("B22DCCN018", "cIQ62aOg", sb.toString());
    }
}
