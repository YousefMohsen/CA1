
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class MsgCon implements Runnable{
    private OutputStream output;
    private String message;
    public MsgCon(OutputStream out, String msg) {
        output = out;
        message = msg;
        
    }
    @Override
    public void run() {
        PrintWriter writer = new PrintWriter(output);
                writer.println(message);
                System.out.println(message);
                writer.flush();
    }
}
