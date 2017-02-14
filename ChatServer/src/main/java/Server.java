
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
    ArrayList<Message> messages;
    ArrayList<Connection> users;
    
   

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

 public void startServer() throws IOException {
        // Create a new unbound socket
        ServerSocket socket = new ServerSocket();
        // Bind to a port number
        socket.bind(new InetSocketAddress(host, port));
 
        System.out.println("Server listening on port " + port);

        // Wait for a connection
      Socket connection;
      while ((connection = socket.accept()) != null) {
         
 
          }
    }
private void handleConnection(Socket connection) throws IOException {
        OutputStream output = connection.getOutputStream();
        InputStream input = connection.getInputStream();
        Handler inputHandler = new Handler();
        String type;
        String messege;
        String sender;
        // Read whatever comes in
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = reader.readLine();
     
        type = inputHandler.findType(line);
        sender = inputHandler.findSender(line);
       
        
      
       switch(type){
        
            case "LOGIN":
                //login
               if( checkLogin(sender)){//succesful login
               users.add(new Connection(sender,connection));
              //send ok to sender
              
              //send update to online users 
               }else{//faied login
               
               }
                
                break;
            
            case "MSG":
                 messege = inputHandler.findMessege(line);
                 
                 messages.add(new Message(sender,messege));
                 //send to users
            break;
       
        
        }
        
        
    
    }
 public boolean checkLogin(String username){
    
        for (Connection connections : users) {
            if(username.contains(connections.getUsername())){
            return false;
            }
        }
    return true;
    }
 private void replyClient(String reply, Socket connection) throws IOException{
     OutputStream output = connection.getOutputStream();
     // Print the same line we read to the client
        PrintStream writer = new PrintStream(output);
        writer.println(reply);
      System.out.println("SERVER: "+reply);
 }
 private void replyAllClient(String reply) throws IOException{
    
     
     OutputStream output;
     for (Connection connection : users) {
         output = connection.getSocket().getOutputStream();
     // Print the same line we read to the client
        PrintStream writer = new PrintStream(output);
        writer.println(reply);
      
     }
     
 }

    public static void main(String[] args) throws IOException {
     Server server = new Server("localhost",8081);
     server.startServer();
     
        
        
        
    }


}
