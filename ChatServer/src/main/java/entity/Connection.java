/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Yousinho
 */
public class Connection {
   String username;
Socket socket;

    public Connection(String username, Socket socketChannel) {
        this.username = username;
        this.socket = socketChannel;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }


}
