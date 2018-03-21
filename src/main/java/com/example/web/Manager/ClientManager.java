package com.example.web.Manager;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 16:34
 * @Version 1.0
 */
public class ClientManager {

    private static ClientManager manager = new ClientManager();

    public ClientManager() {
    }

    public static ClientManager getInstance() {return manager;}

}
