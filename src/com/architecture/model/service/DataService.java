package com.architecture.model.service;

import com.architecture.model.entity.Player;
import java.io.*;

/*
    数据持久化类：负责存档和读档
    使用Java文件流（FileInputStream/FileOutputStream）保存和读取玩家的属性和坐标
*/
public class DataService {
    private static final String SAVE_FILE = "save_data.dat";

    /*
        功能：保存游戏进度
    */
    public boolean saveGame(Player player) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
            oos.writeObject(player);

            System.out.println("您已存档成功!");
        }   catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
        功能：加载游戏进度
    */
    public Player loadGame() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player.dat"))) {
            System.out.println("飞速加载中......");

            Player player = (Player)ois.readObject();

            System.out.println("加载成功!");
            return player;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}