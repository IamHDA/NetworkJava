package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChuyenDoiSoNguyenSangHeNhiPhan {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2207);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String code = "B22DCCN018;1cHc50mW";
        out.writeUTF(code);
        out.flush();
        int n = in.readInt();
        String binary = Integer.toBinaryString(n);
        String hex = Integer.toHexString(n).toUpperCase();
        out.writeUTF(binary + ";" + hex);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
