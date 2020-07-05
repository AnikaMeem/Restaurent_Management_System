/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class messageSender {

    private messageSender() {
    }

    private static class HolderClass {

        private final static messageSender instance = new messageSender();
    }

    public static messageSender getInstance() {
        return HolderClass.instance;
    }

    public boolean sendOneMessage(String receiver, String message) {
        boolean ret = false;
        try {
            Socket socket = new Socket("127.0.0.1", 2587);
            socket.setSoTimeout(60000);

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject("getIp " + receiver);
            outStream.flush();

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
            String Ip = instream.readObject().toString();

            if (Ip == null) {
                ret = false;
            }

            outStream.close();
            instream.close();
            socket.close();

            Socket socketClient = new Socket(Ip, 2588);
            socketClient.setSoTimeout(60000);

            ObjectOutputStream outStreamClient = new ObjectOutputStream(socketClient.getOutputStream());
            outStreamClient.writeObject(nicknameManager.getInstance().getMyNickName() + ":" + message);
            //System.out.println(nicknameManager.getInstance().getMyNickName() + ":" + message);

            outStreamClient.flush();
            outStreamClient.close();
            socketClient.close();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            ret = false;
        }

        return ret;
    }

}