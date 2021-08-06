package Server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

    public static final int PORT = 9000;

    private BufferedReader read;

    private JTextArea serverText;

    public Server() {
        super("SERVER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,500);
        setVisible(true);
        serverText = new JTextArea();
        serverText.setSize(300,500);
        serverText.setEditable(false);
        add(serverText);

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            serverText.append("SERVER STARTED...");

            while(true) {
                Socket socket = serverSocket.accept();
                serverText.append("\nREQUEST ACCEPTED");
                ServerThread serverThread = new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) { Server server = new Server(); }
}
