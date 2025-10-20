package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class TimGiaTriLonNhatVaNhoNhat {

    public static void main(String[] args) throws IOException {
        InetAddress ip =  Inet4Address.getByName("203.162.10.109");
        int port = 2207;
        DatagramSocket socket = new DatagramSocket();
        String data = ";B22DCCN018;CgkmbreN";
        DatagramPacket sendPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, ip, port);
        socket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        socket.receive(receivePacket);
        byte[] requestIdBytes = Arrays.copyOfRange(receivePacket.getData(), 0, 9);
        String requestId = new String(requestIdBytes);
        byte[] numArrayBytes = Arrays.copyOfRange(receivePacket.getData(), 9, receivePacket.getLength());
        String[] numArray = new String(numArrayBytes).split(",");
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(String num : numArray){
            long n = Long.parseLong(num);
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
        String response = requestId + max + "," + min;
        System.out.println(response);
        DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length, ip, port);
        socket.send(responsePacket);

        socket.close();
    }
}
