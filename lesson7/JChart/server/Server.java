package lesson7.JChart.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {
    private final String SERVER_START = "Сервер запущен... ожидает подключения клиентов";
    private final String AUTH_ERROR = "Не удалось запустить сервис авторизации";
    private final int SERVER_PORT = 8189;

    private Vector<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }


    public Server() {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            clients = new Vector<>();
            authService = new AuthService();
            authService.connect();
            System.out.println(SERVER_START);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected " + socket.getInetAddress() +
                        " " + socket.getPort() + " " + socket.getLocalPort());
                new ClientHandler(this, socket);
            }


        } catch (IOException e) {

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(AUTH_ERROR);
        }

    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public boolean isNickBusy(String nick){
        for (ClientHandler o: clients){
            if (o.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(String msg){
        for (ClientHandler o: clients){
            o.sendMsg(msg);
        }
    }

    public void privateMsg(String nick, String msg) {
        String nick_reciver;
        nick_reciver = msg.substring(2, msg.length());
        nick_reciver = nick_reciver.trim();
        String pmsg = nick_reciver.substring(nick_reciver.indexOf(" "), nick_reciver.length());
        pmsg = pmsg.trim();
        nick_reciver = nick_reciver.substring(0, nick_reciver.indexOf(" "));

        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick_reciver)) {
                o.sendMsg(nick + ": " + pmsg);
                return;
            }

        }
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                o.sendMsg("Клиент " + nick_reciver + " недоступен");
                return;
            }
        }
    }

}
