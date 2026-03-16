package com.architecture.model.service;

import com.architecture.App;

import java.util.Random;
import java.util.Scanner;

public class LibraryService {

    final String RED = "\033[31m";
    final String PURPLE = "\033[35m";
    final String GREEN = "\033[32m";
    final String BLUE = "\033[34m";
    final String YELLOW = "\033[33m";
    final String RESET = "\033[0m";
    final String BROWN = "\u001B[38;5;130m";

    /*功能：随机事件库，生成随机数字，然后抽取随机事件输出，并对玩家产生对应的影响
    返回值：无
    参数：无
     */
    public void randomCase(){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        int index = r.nextInt(12);

        if (index == 0) {
            slowPrint("突然，你眼前一黑...");
            slowPrint("...噗通...");
            slowPrint("你慌乱起身，环顾四周，只见周围只是黑压压一片...");
            slowPrint("......");
            slowPrint("就在你不知如何是好时，一只大手摸到了你");
            slowPrint(RED + "是谁？！\033[0m");
            slowPrint("你惊慌失措，打开手电筒，哦，原来是" + PURPLE + "云海学长\033[0m。");
            slowPrint("真的是虚惊一场");
            slowPrint("云海学长把你抬到了洞口。自己却留在哪个漆黑的洞穴中...");
            slowPrint("不过不用担心，这点小事怎么能难得住他呢");
            slowPrint(YELLOW + "事件影响：体力值 -5\033[0m");
            App.player.stamina -= 5;
            sc.nextLine();

        } else if (index == 1) {
            slowPrint("你来到了一个神秘的树洞，眼前是一个小木门");
            slowPrint("...吱呀...木门竟自己打开了");
            slowPrint("原来是一个废弃的精灵屋");
            slowPrint("除了一些硬币，这里再无有用的东西了");
            slowPrint(BLUE + "事件影响：金币 +20\033[0m");
            App.player.gold += 20;
            sc.nextLine();

        } else if (index == 2) {
            slowPrint("嗯，这是什么？");
            slowPrint("一株深绿色的小草，普通而又有些与众不同");
            slowPrint("也许叫它草丛更适合些...");
            slowPrint("突然，草竟然动了起来...");
            slowPrint("你生怕草中有魔物，应激的攻击了...");
            slowPrint("草被攻击后抽搐了起来。");
            slowPrint("当你发现它是曼德拉草的时候，它已经奄奄一息了");
            slowPrint("死的曼德拉草并不能卖出一个好价格钱");
            slowPrint("所以你把它吃了。。。");
            slowPrint(BLUE + "事件影响：最大生命值和最大体力值提高20点\033[0m");
            App.player.maxHp += 20;
            App.player.hp += 20;
            sc.nextLine();

        } else if (index == 3) {
            slowPrint("你来到了一个河畔");
            slowPrint("看着夕阳的余晖，荡漾的流水，碧绿的青草");
            slowPrint("这让你感到十分惬意，勾起了你美好的回忆");
            slowPrint("你坐在河边休息了");
            slowPrint(BLUE + "事件影响：体力值回满了\033[0m");
            App.player.stamina = App.player.maxStamina;
            sc.nextLine();

        } else if (index == 4) {
            slowPrint("一股烤肉的香味袭来");
            slowPrint("你顺着香味来到一棵大树下");
            slowPrint("滋...滋...");
            slowPrint("(⊙o⊙)哇，是" + PURPLE + "予白学长\033[0m在做烤肉");
            slowPrint("“小学弟，要不要尝尝学长烤的肉？”");
            slowPrint("身经百战的你早已饥肠辘辘，");
            slowPrint("直接忘记“尝”的含义，狼吞虎咽起来");
            slowPrint(BLUE + "事件影响：体力值 +20, 血量 +20\033[0m");
            App.player.stamina += 20;
            App.player.hp += 20;
            if(App.player.hp > App.player.maxHp) App.player.hp = App.player.maxHp;
            sc.nextLine();

        } else if (index == 5) {
            slowPrint("这四周没有村庄怎么会有一口井？");
            slowPrint("你本想喝一口水，但是不小心掉进了井里");
            slowPrint("咕噜...咕噜...");
            slowPrint("就在你以为你的旅程就要结束时，你突然从水里漂了起来");
            slowPrint("井似乎把你带到了另一个世界");
            slowPrint("这里太过寂静神秘以至于让你感到害怕");
            slowPrint("你想方设法才从井中逃出来");
            slowPrint(RED + "事件影响：体力值 -10\033[0m");
            App.player.stamina -= 10;
            sc.nextLine();

        } else if (index == 6) {
            slowPrint("莎莎...莎莎...");
            slowPrint("你总感觉这片区域有东西在跟踪你");
            slowPrint("尽管你年轻气盛，但在这种前不着村后不着店的地方，还是让你感到恐惧");
            slowPrint("你拼命的逃离了这个鬼地方");
            slowPrint(RED + "事件影响：体力值 -5\033[0m");
            App.player.stamina -= 5;
            sc.nextLine();

        } else if (index == 7) {
            slowPrint("你来到了一片沙滩");
            slowPrint("走着走着，踩到了一个鼓包");
            slowPrint("你以为是宝藏，挖开一看，只是一个藏在沙里睡觉的大乌龟");
            slowPrint("正当你泄气时，乌龟爬开了");
            slowPrint("令人惊喜的是，下面还真有个宝藏箱，但是箱子上写着" + RED + "东东哥\033[0m三个字");
            slowPrint("想想东东哥那么有钱，一定不差这一点");
            slowPrint("于是你搜刮了东东哥的财宝，一分不差！");
            slowPrint(BLUE + "事件影响：金币 +300\033[0m");
            App.player.gold += 300;
            sc.nextLine();

        } else if (index == 8) {
            slowPrint("不远处有一个类似神庙的建筑");
            slowPrint("你好奇的走了进去，因为你知道，这里面可能有宝藏");
            slowPrint("你躲过了重重机关陷阱，终于取得了宝藏");
            slowPrint("但也同时受了点伤");
            slowPrint(YELLOW + "事件影响：HP -5, 金币 +200\033[0m");
            App.player.hp -= 5;
            App.player.gold += 200;
            sc.nextLine();

        } else if (index == 9) {
            slowPrint("这是什么东西？");
            slowPrint("一个巨型的蘑菇映入眼帘");
            slowPrint("周围的大地也被菌丝包裹，走过去如同踩在柔软的床垫上。");
            slowPrint("蘑菇散发出一种特殊的香味，这让你感到疲惫不堪");
            slowPrint("你就这样毫无防备的睡着了");
            slowPrint("....");
            slowPrint("当你醒来时，你感觉特别舒服");
            slowPrint("但你身旁的那个巨型蘑菇却不见了...");
            slowPrint("也许是某种生物，也许是中了幻术，你想着...感觉脊背发凉");
            slowPrint(BLUE + "事件影响：HP 回满, 体力 回满\033[0m");
            App.player.hp = App.player.maxHp;
            App.player.stamina = App.player.maxStamina;
            sc.nextLine();

        } else if (index == 10) {
            slowPrint(RED + "恭喜您中大奖了!!!\033[0m");
            slowPrint("请留下您的银行卡密码，我们这边把您中的" + RED +"1亿美元\033[0m打给您");
            slowPrint("有了它，您还在这打什么怪啊!累死累活的");
            slowPrint("直接飞往成都......");
            slowPrint("喂...喂...快醒醒，你是打怪打蒙了吧，还想去成都");
            slowPrint("去了那儿你也只能算一个"+ PURPLE +"萝莉\033[0m");
            slowPrint(BLUE + "快振作起来吧!\033[0m");
            slowPrint(YELLOW + "事件影响：金币增加100\033[0m");
            App.player.gold += 100;
            sc.nextLine();

        } else if (index == 11) {
            slowPrint(YELLOW + "正当你绝望之时，意志会带你杀出重围\033[0m");
            slowPrint("嘿嘿...骗你的，" + "最后能救你的只有你"+ RED + "东东哥\033[0m");
            slowPrint(BLUE + "恭喜你获得东东哥的救赎---孩子们不要怕，那个男人来了\033[0m");
            slowPrint(PURPLE + "事件影响：血量给你加满\033[0m");
            App.player.hp = App.player.maxHp;
            sc.nextLine();
        }

    }

    /*
        功能：辅助打印，实现类似打印机的效果
    */
    private void slowPrint(String text) {
        System.out.println(text);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
        }
    }

    /*
        功能：剧情库，根据currentState和mapState和getTile来输出剧情
        参数：mapState
        返回值：无
     */
    public void getGamePlot(int mapState) {
        System.out.println("\n==================================================");
        switch (mapState) {
            case 1:
                System.out.println(YELLOW + "【序章：初入大陆】" + RESET);
                System.out.println(BLUE + "你从昏迷中醒来，发现自己身处一个阴暗森林..."+ RESET);
                System.out.println(BLUE + "四周传来哥布林的低吼，握紧手中的武器，寻找出口吧！"+ RESET);
                break;
            case 2:
                System.out.println(YELLOW + "【第一章：无尽走廊】"+ RESET);
                System.out.println(PURPLE + "穿过铁门，你来到了一个错综复杂的迷宫。"+ RESET);
                System.out.println(PURPLE + "这里的墙壁似乎在不断变换，神秘的商人正在角落里注视着你。"+ RESET);
                break;
            case 3:
                System.out.println(YELLOW + "【第二章：宝藏秘境】"+ RESET);
                System.out.println(YELLOW + "你闻到了金币的香气。这是一个充满宝藏的房间..."+ RESET);
                System.out.println(YELLOW + "但要小心，贪婪往往伴随着致命的陷阱。"+ RESET);
                break;
            case 4:
                System.out.println(YELLOW + "【第三章：魔王前哨】"+ RESET);
                System.out.println(BROWN + "空气变得无比压抑，地面上残留着战斗的痕迹。"+ RESET);
                System.out.println(BROWN + "前方的巨大石门后，似乎隐藏着控制这一切的幕后黑手。"+ RESET);
                break;
            case 5:
                System.out.println(YELLOW + "【终章：深渊对决】"+ RESET);
                System.out.println(RED + "你终于踏入了最终魔王的领地！邪恶气息让怪物变得异常强大。"+ RESET);
                System.out.println(RED + "这不是演习，拼上性命去战斗吧！"+ RESET);
                break;
            case 6:
                System.out.println(YELLOW + "【尾声：破晓】"+ RESET);
                System.out.println(GREEN + "伴随着最后一声嘶吼，魔王倒下了。"+ RESET);
                System.out.println(GREEN +"阳光刺破了地牢的穹顶，你终于迎来了自由..."+ RESET);
                break;
            default:
                System.out.println("【未知的领域...】");
        }
        System.out.println("==================================================\n");
    }

}
