package com.architecture.model.entity;
/*
 * 怪物类
 * */
public class Monster {
    public int id;               // 怪物种类编号
    public String name;          // 怪物名称
    public int hp;               // 怪物生命值
    public int attack;           // 怪物攻击力
    public int rewardGold;       // 击杀掉落金币
    public int exp;                // 击杀怪物掉落的经验值
    public int x, y;             // 在地图上的二维坐标
    public boolean isAlive;      // 存活状态：true为存活，false为已击杀
    public int vertigo;



    public Monster() {
    }

    public Monster(int id, String name, int hp, int attack, int rewardGold, int exp, int x, int y, boolean isAlive,int vertigo) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.rewardGold = rewardGold;
        this.exp = exp;
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
        this.vertigo=vertigo;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * 设置
     * @param hp
     */
    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    /**
     * 获取
     * @return attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 设置
     * @param attack
     */
    public void setAttack(int attack) {
        if(attack<0){this.attack=0;}
        else{
            this.attack = attack;
        }
    }

    /**
     * 获取
     * @return rewardGold
     */
    public int getRewardGold() {
        return rewardGold;
    }

    /**
     * 设置
     * @param rewardGold
     */
    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
    }

    /**
     * 获取
     * @return exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * 设置
     * @param exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 获取
     * @return isAlive
     */
    public boolean isIsAlive() {
        return isAlive;
    }

    /**
     * 设置
     * @param isAlive
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getVertigo() {
        return vertigo;
    }

    public void setVertigo(int vertigo) {
        if(vertigo<0){this.vertigo=0;}
        else
        {this.vertigo = vertigo;}
    }
    public String toString() {
        return "Monster{id = " + id + ", name = " + name + ", hp = " + hp + ", attack = " + attack + ", rewardGold = " + rewardGold + ", exp = " + exp + ", x = " + x + ", y = " + y + ", isAlive = " + isAlive + "}";
    }
}
