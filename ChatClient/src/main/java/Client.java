
import java.io.IOException;
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
 *
 * @author Janus
 */
public class Client {

    private final String host = "localhost";
    private final int port = 8080;
    private Socket clientSocket;
    public Boolean connected = false;

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

                default:
                    if (cl.connected) {
                        cl.sendMessage("MSG#" + input);
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
        sendMessage("LOGIN#" + username);
    }
    

    public void sendMessage(String message) throws IOException {
        // Creates new Thread and writes to the server
        new Thread(new MsgCon(clientSocket.getOutputStream(),message)).start();
    }
    
}
