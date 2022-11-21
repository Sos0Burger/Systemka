import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int number = 0;
        try {
            Socket s = new Socket("localhost", 22);
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            while (number<10) {
                number++;
                dataOutputStream.writeInt(number);
                dataOutputStream.flush();
                System.out.println(number);

                number = dataInputStream.readInt();
                System.out.println(number);
            }
            dataOutputStream.close();
            s.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
