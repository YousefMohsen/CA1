/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Yousinho
 */
public class Message {
   private String sender;
    private String messege;

    public Message(String sender, String messege) {
        this.sender = sender;
        this.messege = messege;
    }

    public String getSender() {
        return sender;
    }

    public String getMessege() {
        return messege;
    }
    
    
}
