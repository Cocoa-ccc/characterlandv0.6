package com.architecture.model.entity;
/*
 * 药水类
 * */
public class Potion {
    public String name;     // 药水名称
    public int healAmount;  // 回复的血量
    public int price;       // 药水价格
    public int staminaRecover; // 新增：回复的体力值

    // 空参构造
    public Potion() {
    }

    // 构造方法
    public Potion(String name, int healAmount, int price) {
        this.name = name;
        this.healAmount = healAmount;
        this.price = price;
        this.staminaRecover = 0; // 旧药水默认体力恢复为0
    }

    // 带体力恢复的构造方法（用于配置有体力效果的药水）
    public Potion(String name, int healAmount, int price, int staminaRecover) {
        this.name = name;
        this.healAmount = healAmount;
        this.price = price;
        this.staminaRecover = staminaRecover;
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
     * @return healAmount
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * 设置
     * @param healAmount
     */
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
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

    /**
     * 获取体力恢复值
     * @return staminaRecover
     */
    public int getStaminaRecover() {
        return staminaRecover;
    }

    /**
     * 设置体力恢复值
     * @param staminaRecover
     */
    public void setStaminaRecover(int staminaRecover) {
        this.staminaRecover = staminaRecover;
    }

    // 重写toString（新增体力恢复字段，便于调试）
    public String toString() {
        return "Potion{name = " + name + ", healAmount = " + healAmount + ", staminaRecover = " + staminaRecover + ", price = " + price + "}";
    }
}
