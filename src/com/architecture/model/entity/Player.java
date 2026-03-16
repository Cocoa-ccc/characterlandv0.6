package com.architecture.model.entity;

import java.io.Serializable;

/*
 * 玩家类
 * */
public class Player implements Serializable {
    public String name;          // 玩家名称
    public String character;
    public int level;                // 玩家等级
    public int exp;                // 当前经验
    public int expToNextLevel;                // 升级所需经验值
    public Weapon equippedWeapon = null;                // 当前武器，null表示无武器
    public Potion equippedPotion = null;                // 当前药水，null表示无药水
    public int hp;               // 当前生命值
    public int maxHp;            // 最大生命值
    public int stamina;// 体力值
    public int maxStamina;
    public int attack;           // 攻击力
    public String criticalHitRate;
    public String avoidanceRate;
    public int defense;          // 防御力
    public int gold;             // 持有金币
    public int x, y;             // 在地图上的二维坐标


    public Player() {
    }

    public Player(String name, String character, int level, int exp, int expToNextLevel, Weapon equippedWeapon, Potion equippedPotion, int hp, int maxHp, int stamina,int maxStamina, int attack, String criticalHitRate, String avoidanceRate, int defense, int gold, int x, int y) {
        this.name = name;
        this.character = character;
        this.level = level;
        this.exp = exp;
        this.expToNextLevel = expToNextLevel;
        this.equippedWeapon = equippedWeapon;
        this.equippedPotion = equippedPotion;
        this.hp = hp;
        this.maxHp = maxHp;
        this.stamina = stamina;
        this.maxStamina=maxStamina;
        this.attack = attack;
        this.criticalHitRate = criticalHitRate;
        this.avoidanceRate = avoidanceRate;
        this.defense = defense;
        this.gold = gold;
        this.x = x;
        this.y = y;
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
     * @return character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 设置
     * @param character
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * 获取
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
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
     * @return expToNextLevel
     */
    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    /**
     * 设置
     * @param expToNextLevel
     */
    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    /**
     * 获取
     * @return equippedWeapon
     */
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * 设置
     * @param equippedWeapon
     */
    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * 获取
     * @return equippedPotion
     */
    public Potion getEquippedPotion() {
        return equippedPotion;
    }

    /**
     * 设置
     * @param equippedPotion
     */
    public void setEquippedPotion(Potion equippedPotion) {
        this.equippedPotion = equippedPotion;
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
    public void setHp(int hp)
    {if(hp<0){this.hp=0;}
    else if(hp>getMaxHp()){this.hp=getMaxHp();}
    else{this.hp=hp;}
    }

    /**
     * 获取
     * @return maxHp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 设置
     * @param maxHp
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * 获取
     * @return stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * 设置
     * @param stamina
     */
    public void setStamina(int stamina) {
        if(stamina<0){this.stamina=0;}
        else if(stamina>getMaxStamina()){this.stamina=getMaxStamina();}
        else{this.stamina=stamina;}
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
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
        this.attack = attack;
    }

    /**
     * 获取
     * @return criticalHitRate
     */
    public String getCriticalHitRate() {
        return criticalHitRate;
    }

    /**
     * 设置
     * @param criticalHitRate
     */
    public void setCriticalHitRate(String criticalHitRate) {
        this.criticalHitRate = criticalHitRate;
    }

    /**
     * 获取
     * @return avoidanceRate
     */
    public String getAvoidanceRate() {
        return avoidanceRate;
    }

    /**
     * 设置
     * @param avoidanceRate
     */
    public void setAvoidanceRate(String avoidanceRate) {
        this.avoidanceRate = avoidanceRate;
    }

    /**
     * 获取
     * @return defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * 设置
     * @param defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * 获取
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * 设置
     * @param gold
     */
    public void setGold(int gold) {
        this.gold = gold;
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

    public String toString() {
        return "Player{name = " + name + ", character = " + character + ", level = " + level + ", exp = " + exp + ", expToNextLevel = " + expToNextLevel + ", equippedWeapon = " + equippedWeapon + ", equippedPotion = " + equippedPotion + ", hp = " + hp + ", maxHp = " + maxHp + ", stamina = " + stamina + ", attack = " + attack + ", criticalHitRate = " + criticalHitRate + ", avoidanceRate = " + avoidanceRate + ", defense = " + defense + ", gold = " + gold + ", x = " + x + ", y = " + y + "}";
    }
}
