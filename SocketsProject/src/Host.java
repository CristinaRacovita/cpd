import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Host {
    public static void main(String[] arg) {
        try {

            Socket socketConnection = new Socket("127.0.0.1", 11111);

            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());
            DataInputStream input = new DataInputStream(socketConnection.getInputStream());

            String serverStringReceived = input.readUTF();
            System.out.println("SERVER: " + serverStringReceived);

            outToServer.writeUTF("HOST");

            outToServer.writeUTF("The location I want to add is Australia");

            serverStringReceived = input.readUTF();
            System.out.println("SERVER: " + serverStringReceived);

            outToServer.writeUTF("The location I want to add is Reykjavik");

            serverStringReceived = input.readUTF();
            System.out.println("SERVER: " + serverStringReceived);

            outToServer.writeUTF("Show me all locations");

            outToServer.writeUTF("END");


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
