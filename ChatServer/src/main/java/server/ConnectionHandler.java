package server;


import entity.Connection;
import entity.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yousinho
 */
public class ConnectionHandler extends Thread {
        Socket connection;
        OutputStream output;
        InputStream input ;
  
        

    public ConnectionHandler(Socket connection) {
        this.connection = connection;
        
    
             try {
                 output = connection.getOutputStream();
                 input = connection.getInputStream();
                
             } catch (IOException ex) {
                 Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
    
    }
 
 
 
 private void handleConnection() throws IOException{
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line;
         
                 while((line = reader.readLine()) != null ){
                     
        
        Handler inputHandler = new Handler();
        String type;
        String messege;
        String reciever;
        String sender;
        Socket recieverSocket;
        System.out.println("handleConnection");
     
    
        System.out.println("line from client(58) "+line);
        type = inputHandler.findType(line);
        reciever = inputHandler.findReciever(line);

        System.out.println("l 71 type: " + type);

        switch (type) {

            case "LOGIN":

                //login
               if (Server.checkLogin(reciever)) {//succesful login // in this case "reciever" is the sender of the message
                   Server.addUser(new Connection(reciever, connection));

                    Server.replyClient("OK", connection);//Successful login followed by list of active user names

                    //Update all clients when a single new user logs in
                    Server.updateAllClients("UPDATE", reciever);
               } else {
                    Server.replyClient("FAIL", connection);//Failed login if user name is already taken or connection error
                }

                break;

            case "MSG":
                System.out.println("msg login");
                sender = inputHandler.findSender(Server.users, connection);
                messege = inputHandler.findMessege(line);
                System.out.println("sender " + sender);
                System.out.println("messege " + messege);

               Server.addMessage(new Message(sender, messege));

                if (reciever.equals("ALL")) {
                    System.out.println("l 103 all");
                    Server.messageToAll(messege, sender);

                } else {
                    recieverSocket = inputHandler.findRecieverSocket(Server.users, reciever);

                   Server.messageToClient(messege, sender, recieverSocket);
                }

                //send to users
                break;

        }

                     
                 }       
    
 
 
 }


    @Override
    public void run() {
            try {
                handleConnection();
            } catch (IOException ex) {
                //disconnect user
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }
    
    
    
}
