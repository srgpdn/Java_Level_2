package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        new Client();
    }
}

class Client {
    private final String SERVER_IP = "localhost";
    private final int SERVER_PORT = 8189;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;
    private Socket socket;

    public Client() {

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
            });
            t.setDaemon(true);
            t.start();
            while (true) {
                String msg = in.readUTF();
                System.out.println("server: " + msg);
                if (msg.equals("/end")) break;

            }

        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
