
import entity.Connection;
import entity.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yousinho
 */
public class Server {

   private final String host;
   private final int port;
   public static ArrayList<Message> messages;
   public static ArrayList<Connection> users;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        users = new ArrayList();
        messages = new ArrayList();
    }

    public void startServer() throws IOException {
        // Create a new unbound socket
        ServerSocket socket = new ServerSocket();
        // Bind to a port number
        socket.bind(new InetSocketAddress(host, port));

        System.out.println("Server listening on port " + port);

        // Wait for a connection
        Socket connection;
        
        while ((connection = socket.accept()) != null) {//
         new ConnectionHandler(connection).start();

        }
    }
     
 public static boolean checkLogin(String username) {

        for (Connection connections : users) {
            if (username.contains(connections.getUsername())) {
                return false;
            }
        }
        return true;
    }
 public static  void replyClient(String re, Socket connection) throws IOException {
        OutputStream output = connection.getOutputStream();
        // Print the same line we read to the client
        String reply = re;

        switch (reply) {

            case "OK"://send
                if (users.size() == 1) {
                    reply += "#";
                } else {
                    for (Connection con : users) {
                        reply += "#" + con.getUsername();
                    }
                }
                break;
            case "FAIL":

                break;

        }
        PrintStream writer = new PrintStream(output);
        writer.println(reply);
        System.out.println("SERVER: " + reply);
    }

    public static void updateAllClients(String type, String username) throws IOException {
        String reply = type + "#" + username;
        OutputStream output;

        for (Connection connection : users) {
            output = connection.getSocket().getOutputStream();
            // Print the same line we read to the client
            PrintStream writer = new PrintStream(output);
            writer.println(reply);

        }

    }

    public static void messageToAll(String message, String sender) throws IOException {
        String reply = "MSG#" + message + "#" + sender;
        OutputStream output;

        for (Connection connection : users) {
            output = connection.getSocket().getOutputStream();
            // Print the same line we read to the client
            PrintStream writer = new PrintStream(output);
            writer.println(reply);

        }
    }

    public static void messageToClient(String msg, String sender, Socket connection) throws IOException {
        OutputStream output;
        String reply = "";
        output = connection.getOutputStream();
        PrintStream writer = new PrintStream(output);
        writer.println(reply);
        System.out.println("SERVER: " + reply);
    }
    
    public static void addUser(Connection con){
    users.add(con);
    }
   public static void addMessage(Message ms){
    messages.add(ms);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server("localhost", 8081);
        server.startServer();

    }

}
