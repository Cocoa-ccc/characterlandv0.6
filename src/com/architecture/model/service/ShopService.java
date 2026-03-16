package com.architecture.model.service;

import com.architecture.App;
import com.architecture.model.entity.GameState;
import com.architecture.model.entity.Player;
import com.architecture.model.entity.Potion;
import com.architecture.model.entity.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    商店界面
    独立存放两类商品
*/
public class ShopService {
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    static final String RESET = "\033[0m";
    private static final String BOLD_RED = "\033[1;31m";
    // 建两个list独立存储武器和药水
    static List<Weapon> weapons = new ArrayList<>();
    static List<Potion> potions = new ArrayList<>();

    // 商店类：管理货架数据
    // 改为静态代码块，类加载时自动初始化，确保数据存在
    static {
        init();
    }

    // 初始化商品数据（药水新增体力恢复属性）
    public static void init() {

        // 清空已有数据（防止重复添加）
        weapons.clear();
        potions.clear();

        // 假设你已经定义了之前的颜色常量
        weapons.add(new Weapon(RESET  + " 木 剑" , 10, 100));     // 基础
        weapons.add(new Weapon(CYAN   + " 铁 剑" , 25, 300));     // 普通
        weapons.add(new Weapon(YELLOW + " 大砍刀", 30, 350));     // 强化
        weapons.add(new Weapon(YELLOW + " 双板斧", 35, 400));     // 强化
        weapons.add(new Weapon(PURPLE + " 斩马刀", 60, 500));     // 史诗
        weapons.add(new Weapon(RED    + "方天画戟", 100, 1000));   // 传说

        // 药水初始化：新增体力恢复参数（第四个参数）
        // 药水初始化：强化视觉反馈
        potions.add(new Potion(GREEN  + "   小 血 瓶  " , 50, 50, 0));
        potions.add(new Potion(GREEN  + "   大 血 瓶  " , 150, 120, 0));
        potions.add(new Potion(CYAN   + " 小型回复药剂 " , 200, 150, 10));
        potions.add(new Potion(CYAN   + " 中型回复药剂 " , 250, 200, 25));
        potions.add(new Potion(PURPLE + " 大型回复药剂 " , 300, 300, 50));
        potions.add(new Potion(BOLD_RED + "  生生不灭丹  " , 500, 550, 100)); // 这种丹药一看就很贵
    }

    /*
        功能：打印商店商品列表（新增体力恢复）
        作用：让玩家知道编号对应什么商品
    */
    public void showShopItems() {
        System.out.println(YELLOW + "\n====商店商品列表====" + RESET);

        System.out.println("【武器】");
        if (weapons.isEmpty()) {
            System.out.println("暂无可售武器");
        } else {
            for (int i = 0; i < weapons.size(); i++) {
                Weapon w = weapons.get(i);
                System.out.printf("%d. %-4s | 攻击力:%-3d | 价格:%d金币\n", // 格式
                        i, w.getName(), w.getAddAttack(), w.getPrice());
            }
        }

        // 打印药水（新增体力恢复列）
        System.out.println("\n【药水】");
        if (potions.isEmpty()) {
            System.out.println("暂无可售药水");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                // 新增：体力恢复展示
                System.out.printf("%d. %-8s | 回血:%-3d | 回体力:%-3d | 价格:%d金币\n",
                        i, p.getName(), p.getHealAmount(), p.getStaminaRecover(), p.getPrice());
            }
        }
        System.out.println("====================");
    }

    /*
        功能：
            购买武器逻辑
            判断金币是否足够，判断武器栏是否为空，提示替换，扣除金币
    */
    public boolean buyWeapon(Player player, int weaponIndex) {
        if (weapons.isEmpty()) {
            System.out.println("商店目前没有出售武器！");
            return false;
        }
        if (weaponIndex < 0 || weaponIndex >= weapons.size()) {
            System.out.println("输入错误，无效的武器编号！购买失败！");
            return false;
        }

        Weapon targetWeapon = weapons.get(weaponIndex);
        if (player.getGold() < targetWeapon.getPrice()) {
            System.out.println("金币不足！购买失败！");
            return false;
        }

        if (player.getEquippedWeapon() != null) {
            System.out.println("武器栏已经有武器，已自动替换为 " + targetWeapon.getName());
            player.setAttack(player.attack + targetWeapon.getAddAttack());
        } else {
            System.out.println("成功装备武器 " + targetWeapon.getName());
            player.setAttack(player.attack + targetWeapon.getAddAttack());
        }

        player.setEquippedWeapon(targetWeapon);
        player.setGold(player.getGold() - targetWeapon.getPrice());
        System.out.println("购买成功！花费了 " + targetWeapon.getPrice() + " 金币");
        return true;
    }

    /*
        功能：
            购买药水逻辑（新增体力恢复）
    */
    public boolean buyPotion(Player player, int potionIndex) {
        if (potions.isEmpty()) {
            System.out.println("商店目前没有出售药水！");
            return false;
        }
        if (potionIndex < 0 || potionIndex >= potions.size()) {
            System.out.println("输入错误，无效的药水编号！购买失败！");
            return false;
        }

        Potion targetPotion = potions.get(potionIndex);
        if (player.getGold() < targetPotion.getPrice()) {
            System.out.println("金币不足！购买失败！");
            return false;
        }

        //提示药水的恢复属性
        String tip = String.format("（回血%d + 回体力%d）",
                targetPotion.getHealAmount(), targetPotion.getStaminaRecover());
        if (player.getEquippedPotion() != null) {
            System.out.println("药水栏已有药水，已替换为 " + targetPotion.getName() + tip);
        } else {
            System.out.println("成功装备药水 " + targetPotion.getName() + tip);
        }

        player.setEquippedPotion(targetPotion);
        player.setGold(player.getGold() - targetPotion.getPrice());
        System.out.println("购买成功！花费了 " + targetPotion.getPrice() + " 金币");
        return true;
    }

    /*
        功能：商店主逻辑
    */
    public int shopFunction(Player player, Scanner sc) {

        ShopService.init();

        // 先显示商品列表，让玩家知道编号
        showShopItems();

        App.view.shopMenu(); // 显示 1.武器 2.药水 3.退出

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("请输入要购买的武器编号：");
                int weaponIndex = sc.nextInt();
                buyWeapon(player, weaponIndex);
                sc.nextLine();
                break;
            case 2:
                System.out.print("请输入要购买的药水编号：");
                int potionIndex = sc.nextInt();
                buyPotion(player, potionIndex);
                break;
            case 3:
                System.out.println("离开商店，继续冒险！");
                App.currentState = GameState.MAP;
                if (App.sc.hasNextLine()) {
                    App.sc.nextLine(); // 读掉残留的 \n
                }
                sc.nextLine();
                return 3;
            default:
                System.out.println("输入错误，请重新选择！");
        }
        return 0;
    }

    // 构造函数
    public ShopService() {}
    public ShopService(List<Weapon> weapons, List<Potion> potions) {
        this.weapons = weapons;
        this.potions = potions;
    }

    // Getter/Setter
    public static List<Weapon> getWeapons() { return weapons; }
    public static void setWeapons(List<Weapon> weapons) { ShopService.weapons = weapons; }
    public static List<Potion> getPotions() { return potions; }
    public static void setPotions(List<Potion> potions) { ShopService.potions = potions; }

    public String toString() {
        return "ShopService{weapons = " + weapons + ", potions = " + potions + "}";
    }
}