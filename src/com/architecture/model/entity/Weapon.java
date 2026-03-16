package com.architecture.model.entity;
/*
* 武器类
* */
public class Weapon {
    public String name;      // 武器名称
    public int addAttack;    // 增加的攻击力
    public int price;        // 价格

    public Weapon() {
    }

    public Weapon(String name, int addAttack, int price) {
        this.name = name;
        this.addAttack = addAttack;
        this.price = price;
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
     * @return addAttack
     */
    public int getAddAttack() {
        return addAttack;
    }

    /**
     * 设置
     * @param addAttack
     */
    public void setAddAttack(int addAttack) {
        this.addAttack = addAttack;
    }

    /**
     * 获取
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "Weapon{name = " + name + ", addAttack = " + addAttack + ", price = " + price + "}";
    }
}
