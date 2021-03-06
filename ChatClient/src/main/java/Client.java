
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *l
 * @author Janus
 */
public class Client {
//138.68.93.230
    private final String host = "localhost";
    private final int port = 8081;
    private Socket clientSocket;
    public Boolean connected = false;
    private String reciever;

    public Client() {
        reciever = "ALL";
    }

    public static void main(String[] args) throws IOException {
        Client cl = new Client();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNext()) {
            input = (String) sc.next();

            switch (input) {
                case "login":
                    System.out.println("Skriv dit username:");
                    cl.login((String) sc.next());
                    break;
                case "chat":
                    System.out.println("Type name of the reciever or ALL for everyone");
                    cl.reciever = (String) sc.next();
                    break;
                default:
                    if (cl.connected) {
                        cl.sendMessage("MSG#" + cl.reciever + "#" + input);
                    } else {
                        System.out.println("Please login to the server by typing: login");
                    }
                    break;
            }
        }
    }

    public void login(String username) throws IOException {
        //connects to the server with username
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(host, port));
        connected = true;
        System.out.println("Connecting to chatserver");
        //creating thread to listen from server
        new Thread(new Reader(clientSocket.getInputStream())).start();
        sendMessage("LOGIN#" + username);
    }

    public void sendMessage(String message) throws IOException {
        // Creates new Thread and writes to the server
        new Thread(new Sender(clientSocket.getOutputStream(), message)).start();
    }

}
