package com.architecture.model.service;

import com.architecture.App;
import com.architecture.model.entity.Monster;
import com.architecture.model.entity.Player;
import com.architecture.model.service.mapservice.MapService;

import java.util.Random;
import java.util.Scanner;

import static com.architecture.App.*;

/*
    逻辑类：处理移动、战斗
*/
public class GameService {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\u001B[31m";


    // 新增：解密服务实例
    private PuzzleService puzzleService = new PuzzleService();
    // 新增：随机数生成器（用于奖励随机）
    private Random random = new Random();

    /*
        移动逻辑
        功能：移动到指定位置
            调用getTile(x,y)获得要移动到的地块的信息,如果map[x][y]是空地则移动到指定位置，如果不为空地，则人物不动
        参数：
            玩家
            x:表示要移动到的x坐标
            y:表示要移动到的y坐标
        返回值：true:移动成功 false:移动失败
    */
    public boolean move(Player player, int nextX, int nextY) {

        if (App.mapService.getTile(nextX, nextY) == '#' || App.mapService.getTile(nextX, nextY) == '*') {
            return false;
        } else if (App.mapService.getTile(nextX, nextY) == ' ') {
            return true;
        } else if (App.mapService.getTile(nextX, nextY) == 'M' || App.mapService.getTile(nextX, nextY) == 'B' || App.mapService.getTile(nextX, nextY) == 'N') {
            return false;
        } else if (App.mapService.getTile(nextX, nextY) == 'S') {
            return false;
        }
        //这里若为E，调用changeMapState
        else if (App.mapService.getTile(nextX, nextY) == 'E') {
            System.out.println("逃脱成功");
            return false;
        }
        // 新增：检测是否移动到宝箱位置
        else if (App.mapService.getTile(nextX, nextY) == MapService.TREASURE_CHAR) {
            // 触发宝箱解密交互
            interactWithTreasure(player, nextX, nextY);
            return false; // 交互后不移动
        }

        return true;


    }

    /*
        战斗逻辑
        功能：判断战斗是否胜利
        参数：
            玩家
            怪物
        返回值 0：继续战斗 1：战斗胜利 2：玩家死亡 3：逃脱战斗
    */
    //加一个逃脱战斗
    public int battle(Player player, Monster monster) {

        /*App.view.showPlayerStatusBarView(App.player);
        App.view.showMonsterStatusBarView(App.currentEnemy);
        App.view.battleMenu();*/

        view.showBattleScreen(player, currentEnemy);
        //玩家回合
        Scanner sc = new Scanner(System.in);
        String choose;
        //接收玩家的菜单输入
        //k用于记录skill()的返回值
        int k=0;

        do{
            choose = sc.nextLine();
            switch (choose) {
                case "1":
                    //k用于记录skill()的返回值
                    k=skill();
                    //战士使用技能一，就是正常攻击，不做处理
                    //战士技能二，不涉及攻击，不做处理
                    //战士技能三，给怪兽到的攻击加10
                    //战士技能四，不涉及攻击，不做处理
                    if(App.player.getCharacter().equals("战士")&&k==3)
                    {
                        int r=twiceHit();
                        if(r==2){System.out.println(App.player.getName() +"使出暴击，击中怪物要害");}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        App.currentEnemy.setHp(App.currentEnemy.getHp() - (App.player.getAttack()+10)*r);
                        System.out.println(App.player.getName() +"攻击了怪物，造成了" + (App.player.getAttack()+10)*r + "的伤害！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    /*刺客一技能，不做处理
                    刺客二技能，不做处理
                    技能三四做
                     */
                    else if(App.player.getCharacter().equals("刺客")&&k==3)
                    {
                        int r=twiceHit();
                        if(r==2){System.out.println(App.player.getName() +"使出暴击，击中怪物要害");}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        App.currentEnemy.setHp(App.currentEnemy.getHp() - (App.player.getAttack()+20)*r);
                        System.out.println(App.player.getName() +"攻击了怪物，造成了" + (App.player.getAttack()+20)*r + "的伤害！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if(App.player.getCharacter().equals("刺客")&&k==4)
                    {
                        int r=twiceHit();
                        if(r==2){System.out.println(App.player.getName() +"使出暴击，击中怪物要害");}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        App.currentEnemy.setHp(App.currentEnemy.getHp() - App.player.getAttack()*r*3);
                        System.out.println(App.player.getName() +"攻击了怪物，造成了" + App.player.getAttack()*r*3 + "的伤害！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    /*游侠技能一不做处理
                    技能二在怪物攻击时做处理
                    技能三不处理
                    技能四处理
                     */
                    else if(App.player.getCharacter().equals("游侠")&&k==4)
                    {
                        int r=twiceHit();
                        if(r==2){System.out.println(App.player.getName() +"使出暴击，击中怪物要害");}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        App.currentEnemy.setHp(App.currentEnemy.getHp() - 100*r);
                        System.out.println(App.player.getName() +"攻击了怪物，造成了" + 100*r + "的伤害！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if(k==5){view.showBattleScreen(player, currentEnemy);continue;}
                    else {
                        int r = twiceHit();
                        if (r == 2) {
                            System.out.println(App.player.getName() + "使出暴击，击中怪物要害");
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        App.currentEnemy.setHp(App.currentEnemy.getHp() - App.player.getAttack() * r);
                        System.out.println(App.player.getName() + "攻击了怪物，造成了" + App.player.getAttack() * r + "的伤害！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "2":
                    if(App.player.getEquippedPotion()==null){System.out.println("没有药水了，请重新选择");
                        view.showBattleScreen(player, currentEnemy);continue;}
                    else{
                        App.player.setHp(App.player.getHp() + App.player.getEquippedPotion().getHealAmount());
                        App.player.setHp(App.player.getStamina() + App.player.getEquippedPotion().getStaminaRecover());
                        System.out.println(App.player.getName() +"喝下血瓶，恢复"+ App.player.getEquippedPotion().getHealAmount()+"点生命值！");
                        //喝完药水，药水没了
                        App.player.equippedPotion=null;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "3":
                    return 3;
                default:
                    System.out.println("无效选择，请重新选择");
                    sc.nextLine();
                    view.showBattleScreen(player, currentEnemy);continue;
            }

            //玩家回合后判断一下，怪物是否死
            if (App.currentEnemy.getHp() == 0) {
                System.out.println("恭喜" + App.player.getName() + "获得胜利");
                App.player.setExp(App.player.getExp()+App.currentEnemy.getExp());
                App.player.setGold(App.player.getGold()+App.currentEnemy.getRewardGold());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("exp增加了"+App.currentEnemy.getExp());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("金币增加了"+App.currentEnemy.getRewardGold());
                System.out.println("按回车回到地图");
                sc.nextLine();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                levelUp();
                App.player.setStamina(App.player.getStamina() + 10);
                if (App.player.getStamina() > App.player.getMaxStamina()) {
                    App.player.setStamina(App.player.getMaxStamina());
                }
                App.currentEnemy.setIsAlive(false);
                return 1;
            }

            //怪物回合,先看看怪物是否是眩晕状态，若是，则直接不执行怪物攻击，进入下一个循环
            if(App.currentEnemy.getVertigo()>0)
            {App.currentEnemy.setVertigo(App.currentEnemy.getVertigo()-1);
                view.showBattleScreen(player, currentEnemy);continue;
            }

            else{

                int r=avoid();

                if(r==0){System.out.println(App.player.getName() +"帅气的躲过了攻击");}
                else {
                    if (App.player.getCharacter().equals("游侠") && k == 2)
                    {

                        App.player.setHp(App.player.getHp() - App.currentEnemy.getAttack()+20);
                        if(App.currentEnemy.getAttack() - App.player.getDefense()-20<0)
                        {
                            System.out.println("怪物攻击了" + App.player.getName() + "，但造成了" + 0 + "伤害！");
                            System.out.println("按回车继续...");
                            sc.nextLine();
                        }
                        else
                        {
                            System.out.println("怪物攻击了" + App.player.getName() + "，造成了" + (App.currentEnemy.getAttack() - App.player.getDefense() - 20) + "的伤害！");
                            System.out.println("按回车继续...");
                            sc.nextLine();
                        }
                    }
                    else {
                        if (App.currentEnemy.getAttack() - App.player.getDefense() <= 0) {
                            System.out.println("怪物攻击了玩家，但是0伤害");
                            System.out.println("按回车继续...");
                            sc.nextLine();
                        } else {
                            App.player.setHp(App.player.getHp() - App.currentEnemy.getAttack() + App.player.getDefense());
                            System.out.println("怪物攻击了" + App.player.getName() + "，造成了" + (App.currentEnemy.getAttack() - App.player.getDefense()) + "的伤害！");
                            System.out.println("按回车继续...");
                            sc.nextLine();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //看看玩家是否活
                if(App.player.getHp()==0){System.out.println(App.player.getName() +"被怪物击败了");return 2;}
                return 0;}
        }while(choose.equals("1"));
        return 0;

    }
    /*功能：玩家提升等级，数值提升
     返回值：无
     参数：无
     */
    public void levelUp() {
        if (player.getExp() >= player.getExpToNextLevel()) {
            //只要exp够，玩家等级一直加
            int cnt = 0;
            while (player.getExp() >= player.getExpToNextLevel()) {
                player.setExp(player.getExp() - player.getExpToNextLevel());
                player.setLevel(player.getLevel() + 1);
                cnt++;
                player.setMaxHp(player.getMaxHp() + 20);
                player.setHp(player.getMaxHp());
                player.setMaxStamina(player.getMaxStamina() + 10);
                player.setStamina(player.getStamina() + 50);
                player.setAttack(player.getAttack() + 5);
            }
            System.out.println(player.getName() + "的等级提升了" + cnt + "级");
        }
    }

    /*暴击函数
    参数：无
    返回值：整数
     */
    public int twiceHit(){
        Random r=new Random();
        if(player.getCharacter().equals("游侠")){
            player.setCriticalHitRate("50%");
            int s[]={0,1};
            int num=r.nextInt(2);
            if(s[num]==0){return 2;}
        }
        else if(player.getCharacter().equals("刺客")& player.getHp()<=50){
            player.setCriticalHitRate("80%");
            int s[]={0,0,0,0,1};
            int num=r.nextInt(5);
            if(s[num]==0){return 2;}
        }
        else{int s[]={0,1,1,1,1};
            int num=r.nextInt(5);
            if(s[num]==0){return 2;}}

        return 1;
    }

    /*闪避函数
    返回值：整数
    参数：无
     */
    public int avoid(){
        Random r=new Random();
        if(player.getCharacter().equals("游侠")){
            player.setAvoidanceRate("50%");
            int s[]={0,1};
            int num=r.nextInt(2);
            if(s[num]==0){return 0;}
        }
        else if(player.getCharacter().equals("刺客")& player.getHp()<=50){
            player.setAvoidanceRate("50%");
            int s[]={0,1};
            int num=r.nextInt(2);
            if(s[num]==0){return 0;}
        }
        return 1;
    }

    /*使用技能函数
    返回值：int
    参数：无
     */
    public int skill(){
        Scanner sc=new Scanner(System.in);
        String choose;
        //r用于检查是否需要重新选择技能
        int r;
        do{/*三个角色的一技能：普攻*/
            r=0;
            if(player.getCharacter().equals("战士")){App.view.fighterSkills();}
            else if(player.getCharacter().equals("刺客")){App.view.raiderSkills();}
            else if(player.getCharacter().equals("游侠")){App.view.errantSkills();}
            System.out.print("请输入要选择的攻击方式:");
            choose = sc.nextLine();
            switch(choose){
                case "1":if(player.getCharacter().equals("战士")){System.out.println("玩家使用了斩击");return 1;}
                else if(player.getCharacter().equals("刺客")){System.out.println("玩家使用了暗影突袭");return 1;}
                else if(player.getCharacter().equals("游侠")){System.out.println("玩家使用了追命箭");return 1;}
                    break;

                case "2":
                    /*战士的二技能*/
                    if(player.getCharacter().equals("战士"))
                    {if(player.getStamina()>=10)
                    {System.out.println("玩家大吼一声，把怪物吓得愣在原地(*Φ皿Φ*)");
                        //用完技能，扣除相应体力值
                        player.setStamina(player.getStamina()-10);
                        //怪物眩晕值+1
                        App.currentEnemy.setVertigo(App.currentEnemy.getVertigo()+1);
                        return 2;}
                    else{System.out.println("体力值不够！");r=1;}
                    }
                    /*刺客二技能*/
                    else if(player.getCharacter().equals("刺客"))
                    {
                        if(player.getStamina()>=20)
                        {System.out.println("玩家挥出吸收拳，造成伤害的同时hp也增加了！");
                            player.setStamina(player.getStamina()-20);
                            player.setHp(player.getHp()+ player.getAttack());
                            return 2;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    /*游侠二技能*/
                    else if(player.getCharacter().equals("游侠"))
                    {
                        if(player.getStamina()>=10)
                        {
                            System.out.println("怪物被冰霜箭冰冻了，攻击力下降了20！");
                            player.setStamina(player.getStamina()-10);

                            return 2;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    break;

                case "3":/*战士的三技能*/
                    if(player.getCharacter().equals("战士"))
                    {if(player.getStamina()>=20) {
                        System.out.println("玩家召唤出了\"我的刀盾\"小宠物，攻击力和防御值都增加了！");
                        //用完技能，扣除相应体力值
                        player.setStamina(player.getStamina() - 20);
                        App.currentEnemy.setAttack(App.currentEnemy.getAttack()-10);
                        return 3;
                    }
                    else{System.out.println("体力值不够！");r=1;}
                    }
                    /*刺客三技能*/
                    else if(player.getCharacter().equals("刺客"))
                    {
                        if(player.getStamina()>=20)
                        {System.out.println("玩家在剑上淬毒，怪物收到的伤害增加了！");
                            player.setStamina(player.getStamina()-20);
                            return 3;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    /*游侠三技能*/
                    else if(player.getCharacter().equals("游侠"))
                    {
                        if(player.getStamina()>=10)
                        {
                            System.out.println("玩家后撤闪避，未伤及分毫，甚至还恢复了血量！");
                            player.setStamina(player.getStamina()-10);
                            player.setHp(player.getHp()+10);
                            App.currentEnemy.setVertigo(App.currentEnemy.getVertigo()+1);
                            return 3;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    break;
                case "4":/*战士的四技能*/
                    if(player.getCharacter().equals("战士"))
                    {if(player.getStamina()>=30) {
                        System.out.println("残血逆生，筋骨重铸！");
                        //用完技能，扣除相应体力值
                        player.setStamina(player.getStamina() - 30);
                        player.setHp(player.getHp()+100);
                        App.currentEnemy.setAttack(App.currentEnemy.getAttack()-10);
                        return 4;
                    }
                    else{System.out.println("体力值不够！");r=1;}
                    }
                    /*刺客四技能*/
                    else if(player.getCharacter().equals("刺客"))
                    {
                        if(player.getStamina()>=50)
                        {System.out.println("身如鬼魅，两只致命残影协同玩家作战！");
                            player.setStamina(player.getStamina()-50);
                            return 4;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    /*游侠四技能*/
                    else if(player.getCharacter().equals("游侠"))
                    {
                        if(player.getStamina()>=30)
                        {
                            System.out.println("玩家一飞冲天，万箭齐发，对目标造成重创！");
                            player.setStamina(player.getStamina()-30);

                            return 4;
                        }
                        else
                        {
                            System.out.println("体力值不够！");r=1;
                        }
                    }
                    break;
                case "5":
                    return 5;
                default:System.out.println("无效输入，请重新选择");r=1;
            }

        }while(r==1);
        //下面这个是废物，没啥用但要写
        return 0;
    }


    /*功能：游戏难度设置
    返回值：double,难度系数
    参数：无
     */
    public static double selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        double difficultyValue = 1.0; // 默认返回值
        boolean validInput = false;

        while (!validInput) {
            System.out.println("====================");
            System.out.println("    请选择游戏难度    ");
            System.out.println("1. " + GREEN + "简单 怪物的血量和攻击力仅仅为标准状态下的0.5倍" + RESET);
            System.out.println("2. " + BLUE + "普通 标准难度" + RESET);
            System.out.println("3. " + YELLOW + "困难 怪物的血量和和攻击力为标准状态下的1.5倍" + RESET);
            System.out.println("4. " + RED + "噩梦 怪物的血量和攻击力为标准状态下的2.5倍" + RESET);
            System.out.println("====================");
            System.out.print("请输入你的选择: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n已选择: " + GREEN + "简单难度，你是在养生吗？" + RESET);
                        difficultyValue = 0.5;
                        validInput = true;
                        break;
                    case 2:
                        System.out.println("\n已选择: " + BLUE + "普通难度，一场原汁原味的冒险开始了！" + RESET);
                        difficultyValue = 1.0;
                        validInput = true;
                        break;
                    case 3:
                        System.out.println("\n已选择: " + YELLOW + "困难难度，这将是一片荒芜..." + RESET);
                        difficultyValue = 1.5;
                        validInput = true;
                        break;
                    case 4:
                        System.out.println("\n已选择: " + RED + "噩梦难度,你——将——会——永——世——不——得——超——生—！！！！！！" + RESET);
                        difficultyValue = 3.5;
                        validInput = true;
                        break;
                    default:
                        System.out.println("输入错误！\n");
                }
            } else {
                System.out.println("输入格式无效！请输入数字。\n");
                scanner.next();
            }
        }

        return difficultyValue;
    }

    /*
        新增：宝箱解密交互逻辑
        功能：触发解密、验证答案、发放奖励、移除宝箱
        参数：玩家对象、宝箱坐标
    */
    public void interactWithTreasure(Player player, int x, int y) {
        System.out.println("\n 你发现了一个宝箱...");

        boolean success = puzzleService.startPuzzle();
        if (success) {
            // 发金币
            giveTreasureReward(player);

            // 宝箱从地图上移除
            App.mapService.removeTreasureChest(x, y);
        } else {
            System.out.println(" 答案错误，宝箱打不开！");
        }
    }

    /*
        新增：宝箱解密交互逻辑
        功能：触发解密、验证答案、发放奖励、移除宝箱
        参数：玩家对象、宝箱坐标
    */
    public void interactWithEvent(Player player, int x, int y) {
        boolean success = puzzleService.startPuzzle();
        if (success) {
            // 发金币
            giveTreasureReward(player);

            // 宝箱从地图上移除
            App.mapService.removeTreasureChest(x, y);
        } else {
            System.out.println(" 答案错误，宝箱打不开！");
        }
    }

    /*
        发放宝箱奖励（奖励：金币）
    */
    private void giveTreasureReward(Player player) {
        // 金币奖励：100 ~ 400 随机
        int gold = 100 + random.nextInt(301);
        player.setGold(player.getGold() + gold);
        System.out.println(" 获得金币：" + gold);
    }




}
