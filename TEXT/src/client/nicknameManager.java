/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class nicknameManager {

    private nicknameManager() {
    }

    private static class HolderClass {

        private final static nicknameManager instance = new nicknameManager();
    }

    public static nicknameManager getInstance() {
        return HolderClass.instance;
    }

    private String MyNickName = null;

    public String getMyNickName() {
        return MyNickName;
    }

    public boolean signin(String nickname) {
        String result = null;
        try {
            Socket socket = new Socket("127.0.0.1", 2587);
            socket.setSoTimeout(60000);
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            outStream.writeObject("signin " + nickname);
            outStream.flush();

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
            result = instream.readObject().toString();

            outStream.close();
            instream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        if (result.equals("success")) {
            MyNickName = nickname;
            return true;
        }

        return false;
    }

    public boolean signout(String nickname) {
        String result = null;
        try {
            Socket socket = new Socket("127.0.0.1", 2587);
            socket.setSoTimeout(60000);
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            outStream.writeObject("signout " + nickname);
            outStream.flush();

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
            result = instream.readObject().toString();

            outStream.close();
            instream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        if (result.equals("success")) {
            MyNickName = nickname;
            return true;
        }

        return false;
    }

    public Set<String> getNicknameList() {
        Set<String> result = null;
        try {
            Socket socket = new Socket("127.0.0.1", 2587);
            socket.setSoTimeout(60000);
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            outStream.writeObject("clients");
            outStream.flush();

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
            result = (Set<String>) instream.readObject();

            outStream.close();
            instream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        return result;
    }

}