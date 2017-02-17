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
        Connection con;
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
 
 
 
 private void handleConnection() throws IOException {
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line;
         
    
                while((line = reader.readLine()) != null ){
                    
                    
                    Handler inputHandler = new Handler();
                    String type;
                    String messege;
                    String reciever;
                    String sender;
                    Connection recieverSocket;
                    
                    
                    type = inputHandler.findType(line);
                    reciever = inputHandler.findReciever(line);
                    
                    
                    switch (type) {
                        
                        case "LOGIN":
                            
                            //login
                            if (Server.checkLogin(reciever)) {//succesful login // in this case "reciever" is the sender of the message
                                
                              con = new Connection(reciever, connection);
                                Server.addUser(con);
                                
                                Server.replyClient("OK", connection);//Successful login followed by list of active user names
                                
                                //Update all clients when a single new user logs in
                                Server.updateAllClients("UPDATE", reciever);
                            } else {
                                Server.replyClient("FAIL", connection);//Failed login if user name is already taken or connection error
                            }
                            
                            break;
                            
                        case "MSG":
                            sender = inputHandler.findSender(Server.users, connection);
                            messege = inputHandler.findMessege(line);
                          
                            
                            
                            if (reciever.equals("ALL")) {
                            
                                Server.messageToAll(messege, sender);
                                
                            } else {
                                recieverSocket = inputHandler.findRecieverSocket(Server.users, reciever);
                                
                                Server.messageToClient(messege, recieverSocket);
                            }
                            
                            //send to users
                            break;
                            
                    }
                    
                    
                }
             
     Server.removeUser(this.con);
 
 
 }


    @Override
    public void run() {
            //disconnect user
          
            try {
                handleConnection();
            
                System.out.println("l 129");
            } catch (IOException ex) {
                
               
            }
     
    }
    
    
    
}
