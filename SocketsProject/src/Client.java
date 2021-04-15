import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] arg) {
        try {

            Socket socketConnection = new Socket("127.0.0.1", 11111);

            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());

            DataInputStream input = new DataInputStream(socketConnection.getInputStream());


            outToServer.writeUTF("CLIENT");

            String serverStringReceived = input.readUTF();
            System.out.println(serverStringReceived);

            outToServer.writeUTF("1&3");

            serverStringReceived = input.readUTF();
            System.out.println(serverStringReceived);

            outToServer.writeUTF("2&3");

            serverStringReceived = input.readUTF();
            System.out.println(serverStringReceived);

            outToServer.writeUTF("1&3");

            serverStringReceived = input.readUTF();
            System.out.println(serverStringReceived);

            outToServer.writeUTF("Show me all locations");

            serverStringReceived = input.readUTF();
            System.out.println(serverStringReceived);

            outToServer.writeUTF("END");


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
