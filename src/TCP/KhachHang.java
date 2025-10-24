package TCP;

import TCP.Customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class KhachHang {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B22DCCN018;M32aN9Aj");
        out.flush();
        Customer customer = (Customer) in.readObject();
        String[] names = customer.name.toLowerCase().split("\\s+");
        String[] dates = customer.dayOfBirth.split("-");

        StringBuilder name = new StringBuilder(names[names.length - 1].toUpperCase() + ",");
        StringBuilder dayOfBirth = new StringBuilder(dates[1] + "/" + dates[0] + "/" + dates[2]);
        StringBuilder userName = new StringBuilder();

        for(int i = 0; i < names.length - 1; ++i){
            name.append(" ");
            String tmp = Character.toUpperCase(names[i].charAt(0)) + names[i].substring(1);
            name.append(tmp);
            userName.append(Character.toLowerCase(names[i].charAt(0)));
        }

        userName.append(names[names.length - 1]);

        customer.name = name.toString();
        customer.dayOfBirth = dayOfBirth.toString();
        customer.userName = userName.toString();

        System.out.println(customer);

        out.writeObject(customer);
        out.flush();

        out.close();
        in.close();
        socket.close();

    }
}
