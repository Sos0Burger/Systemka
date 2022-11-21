import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int number = 0;
        try {
            ServerSocket ss = new ServerSocket(22);
            Socket s = ss.accept();
            DataInputStream input = new DataInputStream(s.getInputStream());
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            while(number<10) {
                number = input.readInt();
                System.out.println(number);
                number++;
                output.writeInt(number);
                output.flush();
                if(number<10) {
                    System.out.println(number);
                }
            }
            input.close();
            output.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
