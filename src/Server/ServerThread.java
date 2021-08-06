package Server;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader read;
    private PrintWriter write;

    public ServerThread (Socket socket) {
        this.socket = socket;

        try {
            read = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            write = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            String request = read.readLine();
            if (request.equals("TIME")) {
                Date currentTime = new Date();
                write.println(currentTime);
                read.close();
                write.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
