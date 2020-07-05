/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class UserManager {

    private Set<String> nickNamesSet = new HashSet<String>();
    private CheckAlive checkAlive;

    private Map<String, String> nickNamesMap = new HashMap<String, String>();

    private UserManager() {
        checkAlive = new CheckAlive(nickNamesMap);
        Thread receiverThread = new Thread(checkAlive);
        receiverThread.start();
    }

    private static class HolderClass {

        private final static UserManager instance = new UserManager();
    }

    public static UserManager getInstance() {
        return HolderClass.instance;
    }

    public boolean signin(String nickname, String IPwithPort) {
        if (nickNamesSet.contains(nickname)) {
            return false;
        }

        String IP = IPwithPort.substring(1, IPwithPort.indexOf(':'));

        nickNamesSet.add(nickname);
        nickNamesMap.put(nickname, IP);

        checkAlive.setNickNamesMap(nickNamesMap);

        return true;
    }

    public boolean signout(String nickname) {
        if (!nickNamesSet.contains(nickname)) {
            return false;
        }

        nickNamesSet.remove(nickname);
        nickNamesMap.remove(nickname);

        return true;
    }

    public Set<String> getAllNickNames() {
        return nickNamesSet;
    }

    public List<String> getIPs() {
        List<String> l = new ArrayList<String>();
        l.addAll(nickNamesMap.values());

        return l;
    }

    public String getIP(String nickname) {
        return nickNamesMap.get(nickname);
    }

}