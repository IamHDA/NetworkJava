package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TongCuaCacSoNguyenTrongChuoi {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        String code = "B22DCCN018;YuORCM5U";
        os.write(code.getBytes());
        os.flush();
        byte[] receiveByte = new byte[1024];
        int bytesRead = is.read(receiveByte);
        String[] receive = new String(receiveByte, 0, bytesRead).replace("|", " ").split("\\s+");
        Long sum = 0L;
        for(String s : receive){
            sum += Long.parseLong(s);
        }
        os.write(sum.toString().getBytes());
        os.flush();
        is.close();
        os.close();
        socket.close();
    }
}
