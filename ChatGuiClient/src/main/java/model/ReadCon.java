package model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Janus
 */
public class ReadCon implements Runnable {

    private InputStream input;

    public ReadCon(InputStream in) {
        input = in;
    }

    @Override
    public void run() {
        try {
            // Read from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String fromServer;
            while (true) {
                while ((fromServer = reader.readLine()) == null) {
                    // Wait until the server says something interesting
                }
                protocolDecoder(fromServer);
            }
        } catch (IOException ex) {
        }
    }

    public void protocolDecoder(String s) {
        switch (s.split("#")[0]) {
            case "OK":

                String rest = "You are now connected to the server\nUsers Online:";
                for (int i = 1; i < s.split("#").length; i++) {
                    rest += "\n" + s.split("#")[i];
                }
                System.out.println(rest);
                break;
            case "MSG":
                String sender;
                String message;
                sender = s.split("#")[1];
                message = s.split("#")[2];
                System.out.println(sender + " " + message);
                break;
            case "FAIL":
                System.out.println("Connection failed or username already taken");
                break;
            case "UPDATE":
                String besked = "Users Online:";
                for (int i = 1; i < s.split("#").length; i++) {
                    besked += "\n" + s.split("#")[i];
                }
                System.out.println(besked);
                break;
            case "DELETE":
                System.out.println(s.split("#")[1]+" disconnected from the server");
                break;
        }
    }

}
