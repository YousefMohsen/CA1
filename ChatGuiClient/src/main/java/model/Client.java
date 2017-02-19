package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
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
//138.68.93.230

    private final String host = "138.68.93.230";
    private final int port = 8081;
    private Socket clientSocket;
    public Boolean connected;
    private String reciever;
    public Reader reader;
    public String name;

    public Client() {
    
        reciever = "ALL";
        connected  = false;
        name ="noName";
    }

    public static void main(String[] args) throws IOException {
        Client cl = new Client();
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
    
    public void addObserver(Observer o){
        reader.addObserver(o);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void login() throws IOException {
        //connects to the server with username
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(host, port));
        connected = true;
        //creating thread to listen from server
        reader = new Reader(clientSocket.getInputStream());
        new Thread(reader).start();
        sendMessage("LOGIN#" + name);
    }

    public void sendMessage(String message) throws IOException {
        // Creates new Thread and writes to the server
        new Thread(new Sender(clientSocket.getOutputStream(), message)).start();
    }

}
