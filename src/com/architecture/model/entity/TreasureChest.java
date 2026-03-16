package com.architecture.model.entity;

public class TreasureChest {
    private int id;
    private int x, y; // 宝箱在地图上的坐标
    private boolean isOpened = false;
    private int rewardGold; // 奖励金币

    public TreasureChest(int id, int x, int y, int rewardGold) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rewardGold = rewardGold;
    }

    // Getter & Setter
    public boolean isOpened() { return isOpened; }
    public void setOpened(boolean opened) { isOpened = opened; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRewardGold() { return rewardGold; }
}