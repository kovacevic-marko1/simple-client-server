package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame{

    private static final int PORT = 9000;

    private BufferedReader read;
    private PrintWriter write;

    private JButton request;
    private JTextArea response;

    public Client() {
        super("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        getContentPane().setLayout(new GridLayout(2, 1));
        request = new JButton(("SEND TIME REQUEST"));
        request.addActionListener(new ButtonAction());
        response = new JTextArea();
        response.setEditable(false);
        add(request);
        add(response);
    }

    public static void main(String[] args) { Client client = new Client(); }


    public class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                Socket clientSocket = new Socket(inetAddress,PORT);
                read = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                write = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(clientSocket.getOutputStream())), true);

                write.println("TIME");
                response.append(read.readLine() + "\n");

                read.close();
                write.close();
                clientSocket.close();

            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
