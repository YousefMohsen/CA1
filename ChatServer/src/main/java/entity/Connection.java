/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.nio.channels.SocketChannel;

/**
 *
 * @author Yousinho
 */
public class Connection {
   String username;
SocketChannel socketChannel;

    public Connection(String username, SocketChannel socketChannel) {
        this.username = username;
        this.socketChannel = socketChannel;
    }

    public String getUsername() {
        return username;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }


}
