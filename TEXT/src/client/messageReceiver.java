/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.control.TextArea;

/**
 *
 * @author Lenovo
 */
public class messageReceiver implements Runnable {

    private TextArea messagelbl;
    Boolean running = true;

    messageReceiver(TextArea receivedMessage) {
        messagelbl = receivedMessage;
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        try {
            running = true;
            ServerSocket serverSocket = new ServerSocket(2588);
            while (running) {
                Socket socket = serverSocket.accept();
                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());

                String message = instream.readObject().toString();

                messagelbl.appendText(message + "\n");

                instream.close();
                socket.close();
            }
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

}