package UDP;

import UDP.Book;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class ChuanHoaSach {
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2209;
        DatagramSocket socket = new DatagramSocket();
        String code = ";B22DCCN018;L2pObvgX";
        DatagramPacket sendPacket = new DatagramPacket(code.getBytes(), code.getBytes().length, ip, port);
        socket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(new byte[4096], 4096);
        socket.receive(receivePacket);
        byte[] requestIdBytes = Arrays.copyOfRange(receivePacket.getData(), 0, 8);
        byte[] objectBytes = Arrays.copyOfRange(receivePacket.getData(), 8, receivePacket.getLength());
        ByteArrayInputStream bais = new ByteArrayInputStream(objectBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();
        String[] titles = book.title.toLowerCase().split("\\s+");
        String[] names = book.author.toLowerCase().split("\\s+");
        String[] dates = book.publishDate.split("-");
        String isbn = book.isbn;
        StringBuilder title = new StringBuilder();
        StringBuilder name = new StringBuilder(names[0].toUpperCase() + ",");
        for(String s : titles) {
            if(!title.isEmpty()) title.append(" ");
            String tmp = Character.toUpperCase(s.charAt(0)) + s.substring(1);
            title.append(tmp);
        }
        for(int i = 1; i < names.length; i++) {
            name.append(" ");
            name.append(Character.toUpperCase(names[i].charAt(0)) + names[i].substring(1));
        }
        String ISBN = isbn.substring(0, 3) + '-' + isbn.charAt(3) + '-' + isbn.substring(4, 6) + '-' + isbn.substring(6, 12) + '-' + isbn.charAt(12);
        String date = dates[1] + "/" + dates[0];
        book.author = name.toString();
        book.isbn = ISBN;
        book.title = title.toString();
        book.publishDate = date;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(book);
        ByteArrayOutputStream finalBos = new ByteArrayOutputStream();
        finalBos.write(requestIdBytes);
        finalBos.write(bos.toByteArray());
        System.out.println(book.toString());
        DatagramPacket response = new DatagramPacket(finalBos.toByteArray(), finalBos.size(), ip, port);
        socket.send(response);
        socket.close();
    }
}
