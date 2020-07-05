/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class fileSender {

    public fileSender() {
    }
    
    private static class HolderClass {

        private final static fileSender instance = new fileSender();
    }
    
    public static fileSender getInstance() {
        return HolderClass.instance;
    }
    
    public boolean sendOneFile(String receiver, String filePath){
        
        boolean ret = false;
        try{
            
            Socket socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 2590);

        File file = new File(filePath);

        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
            
        }
        catch(Exception e){
            System.out.println("Exception:" + e);
            ret = false;
        }
        
        return ret;
        
    }

}