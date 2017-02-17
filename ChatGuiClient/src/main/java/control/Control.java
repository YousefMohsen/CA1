/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.Observer;
import model.Client;
import model.Reader;

/**
 *
 * @author Janus
 */
public class Control {
    Client cl = new Client();
    
    public boolean getConnected(){
    return cl.getConnected();
}
    public void login(String s) throws IOException{
        cl.login(s);
    }
    
    public String getReciever(){
        return cl.getReciever();
    }
    
    public void setReciever(String s){
        cl.setReciever(s);
    }
    
    public void sendMessage(String s) throws IOException{
        cl.sendMessage(s);
    }
    
    public void addObserver(Observer o){
        cl.addObserver(o);
    }
}

