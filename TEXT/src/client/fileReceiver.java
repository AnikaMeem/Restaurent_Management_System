/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class fileReceiver implements Runnable {

    public fileReceiver() {
    }
    
    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(2590);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();

                OutputStream out = socket.getOutputStream();

                byte[] bytes = new byte[16 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }

                out.close();
                in.close();
                socket.close();
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }
    
}