package server;


import entity.Connection;
import entity.Message;
import java.net.Socket;
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
public class Handler {
    
    
    
    public String findType(String in){
    return  in.split("#")[0];  
    }
    public String findReciever(String in){
    
    return  in.split("#")[1];}
    
    public String findMessege(String in){
    
    return  in.split("#")[2];}
    
       public String findSender(ArrayList<Connection> userlist,Socket sender){
    String result = "noName";
           for (Connection connection : userlist) {
              if( connection.getSocket()==sender){
              result = connection.getUsername();
              }
           }
      return result; 
       }
       
           
public Socket findRecieverSocket(ArrayList<Connection> userlist,String recieverName){
  
    
           for (Connection connection : userlist) {
               
              if( connection.getUsername()==recieverName){
              return connection.getSocket();
              }
           
           }
      return null; 
       }
       
    
    

}
