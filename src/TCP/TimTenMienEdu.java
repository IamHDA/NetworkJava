package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TimTenMienEdu {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String code = "B22DCCN018;BHDC7Ufm";
        bw.write(code + "\n");
        bw.flush();
        String[] receive = br.readLine().replace(",", " ").split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(String s : receive){
            if(s.contains(".edu")) {
                if(!sb.isEmpty()) sb.append(", ");
                sb.append(s);
            }
        }
        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
        socket.close();
    }
}
