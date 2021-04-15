import model.Locations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSide extends Thread {
    private final Socket socket;
    private final DataInputStream inFromClient;
    private final DataOutputStream outputStream;
    private String isHost;
    private final Locations locations;

    ServerSide(Socket socket, Locations locations, String isHost) throws IOException {
        this.socket = socket;
        this.locations = locations;
        inFromClient = new DataInputStream(this.socket.getInputStream());
        outputStream = new DataOutputStream(this.socket.getOutputStream());
        this.isHost = isHost;
    }

    @Override
    public void run() {

        try {
            outputStream.writeUTF("Server is running...");
            String option = "";

            while (!option.equals("END")) {
                option = inFromClient.readUTF();
                if (option.equals("HOST")) {
                    System.out.println("You are a " + option);
                    outputStream.writeUTF("Hi, Host! Where do you want to add a location?");
                    isHost = option;
                } else if (option.equals("CLIENT")) {
                    System.out.println("You are a " + option);
                    outputStream.writeUTF("Hi, Client! Where do you want to go in vacation?\n" + locations.getLocationsDetails());
                    isHost = option;
                } else {
                    if (isHost.equals("HOST")) {
                        if (option.contains("The location I want to add is")) {
                            String locationName = option.substring(option.lastIndexOf(" ") + 1);
                            locations.addNewLocation(locationName);
                            outputStream.writeUTF("Location added.");
                        } else if (option.equals("Show me all locations")) {
                            System.out.println("Locations:\n" + locations.getLocationsNames());
                        } else {
                            System.out.println("You don't have this option!:(");
                        }
                    } else if (isHost.equals("CLIENT")) {
                        if (option.contains("&")) { //option&months
                            String[] availableFilter = option.split("&");
                            outputStream.writeUTF(locations.bookLocationForMonth(Integer.parseInt(availableFilter[0]), Integer.parseInt(availableFilter[1])));
                        } else if (option.contains("Show me all locations")) {
                            System.out.println("Locations:\n" + locations.getLocationsDetails());
                        } else {
                            System.out.println("You don't have this option!:(");
                        }
                    } else {
                        System.out.println("I don't know who you are! :(");
                    }
                    System.out.println(option);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println("Socket Closing");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Locations locations = new Locations();
        String isHost = "UNKNOWN";

        while (true) {
            ServerSocket serverSocket = new ServerSocket(11111);

            System.out.println("Server is Awaiting");
            Socket socketClient = serverSocket.accept();
            ServerSide serverSide = new ServerSide(socketClient, locations, isHost);
            serverSide.start();

            Thread.sleep(2000);
            serverSocket.close();
        }
    }
}

