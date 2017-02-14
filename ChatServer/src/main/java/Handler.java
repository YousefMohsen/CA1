
import entity.Connection;
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
    public String findSender(String in){
    
    return  in.split("#")[1];}
    public String findMessege(String in){
    
    return  in.split("#")[2];}
    
   
    
    
    public static void main(String[] args) {
       Handler ha = new Handler(); 
        System.out.println("type "+ ha.findType("MSG#Messi#Hej G"));
        System.out.println( "sender "+ha.findSender("MSG#Messi#Hej G"));
        System.out.println( "messege "+ha.findMessege("MSG#Messi#Hej G"));

        
        
    }
}
