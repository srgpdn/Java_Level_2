package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {


    public static void main(String[] args) {
	// write your code here
        new Server();
    }
}

class Server {
    private final int SERVER_PORT = 8189;
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;

    public Server() {
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("Server started... wait client");
            socket = server.accept();

            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String s = sc.nextLine();
                            out.writeUTF(s);
                        }  catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }
            });
            t.setDaemon(true);
            t.start();
            while (true) {
                String msg = in.readUTF();
                System.out.println("client: " + msg);
                if (msg.equals("/end")) break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
