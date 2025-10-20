package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class LoaiBoKyTuTrongChuoi1 {
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2208;
        DatagramSocket socket = new DatagramSocket();
        String code = ";B22DCCN018;EGqBAqkp";
        DatagramPacket sendPacket = new DatagramPacket(code.getBytes(), code.getBytes().length, ip, port);
        socket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        socket.receive(receivePacket);
        byte[] idBytes = Arrays.copyOfRange(receivePacket.getData(), 0, 9);
        String id = new String(idBytes);
        byte[] dataBytes = Arrays.copyOfRange(receivePacket.getData(), 9, receivePacket.getLength());
        String[] data = new String(dataBytes).split(";");
        StringBuilder sb = new StringBuilder();
        for(char c : data[0].toCharArray()){
            if(!data[1].contains(String.valueOf(c))) sb.append(c);
        }
        String response = id + sb.toString();
        DatagramPacket responseData = new DatagramPacket(response.getBytes(), response.getBytes().length, ip, port);
        socket.send(responseData);
        socket.close();
    }
}
