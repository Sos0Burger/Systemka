import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        String message;
        try {
            ServerSocket ss = new ServerSocket(22);
            Socket s = ss.accept();
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

            message = dataInputStream.readUTF();
            System.out.println("Сервер получил сообщение: " + message);

            dataOutputStream.writeUTF(message);
            dataInputStream.close();
            dataOutputStream.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
