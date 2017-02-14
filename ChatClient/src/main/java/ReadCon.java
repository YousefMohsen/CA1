
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
                System.out.println(fromServer);
            }
        } catch (IOException ex) {
        }
    }

    public ReadCon() {
    }
..
    .
    .
    .
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    
    .
    .
    .
    .
    
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    .
    
    .
    .
    .
    .
    .
    .
    .
    
}
