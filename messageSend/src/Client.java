import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            Socket s = new Socket("localhost", 22);
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            System.out.println("Введите сообщение");
            dataOutputStream.writeUTF(in.nextLine());
            dataOutputStream.flush();

            System.out.println("Клиент получил ответ от сервера: "+dataInputStream.readUTF());

            dataInputStream.close();
            dataOutputStream.close();
            s.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
