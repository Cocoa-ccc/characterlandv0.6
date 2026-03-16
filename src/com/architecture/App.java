package com.architecture;

import com.architecture.model.entity.GameState;
import com.architecture.model.entity.Monster;
import com.architecture.model.entity.Player;
import com.architecture.model.entity.Weapon;
import com.architecture.model.service.*;
import com.architecture.model.service.mapservice.*;
import com.architecture.model.view.GameView;


import java.util.*;

public class App {

    public static Player player = new Player("格里菲斯","战士", 1, 0, 100, null, null, 100, 100, 100, 100, 20,"0%","0%", 0, 100, 0, 0);
    public static Weapon weapon = new Weapon();
    public static GameService service = new GameService();
    public static GameView view = new GameView();
    public static DataService dataService = new DataService();
    public static MapService mapService = new MapService();
    public static StartMap startMap = new StartMap();
    public static RandomMap randomMap = new RandomMap();
    public static RewardMap rewardMap = new RewardMap();
    public static EndMap endMap = new EndMap();
    public static TrueEndMap trueEndMap = new TrueEndMap();
    public static ShopService shopService = new ShopService();
    public static LibraryService libraryService = new  LibraryService();
    public static AudioService audio = new AudioService();
    public static int mapState = 0;
    public static Scanner sc = new Scanner(System.in);
    public static double model;

    // 用于存储当前地图上所有怪物的集合
    public static List<Monster> monsters = new ArrayList<>();
    // 用于表示目前正在与哪只怪物战斗
    public static Monster currentEnemy = null;
    // 当前游戏所处的状态
    public static GameState currentState = GameState.MENU;



    final String CYAN = "\033[36m";
    final String GREEN = "\033[32m";
    static final String RED = "\033[31m";
    final String YELLOW = "\033[33m";
    static final String RESET = "\033[0m";

    /*
        功能：游戏主循环
    */
    public static void gameLoop() {
        view.showTitle();

        // 玩家存活且未退出游戏
        while (player.hp > 0) {
            // 利用状态机模式管理不同的界面切换
            switch (currentState) {
                case MENU:
                    renderMenuState();
                    break;

                case MAP:
                    renderMapState();
                    break;

                case BATTLE:
                    renderBattleState();
                    break;

                case SHOP:
                    renderShopState();
                    break;

                case INGAMESET:
                    pause();
                    break;
            }
        }

        // 进入 Game Over 结算流程
        System.out.println("游戏结束！");
        System.exit(0);
    }

    private static void renderMenuState() {
        view.showMenuView();
        audio.musicOn(mapState, currentState);
        System.out.println("请输入选项：");
        String input = sc.nextLine();
        switch (input) {
            case "1":// 1. 选择难度，获取难度乘区系数
                model = GameService.selectDifficulty();

                pickCharacters();

                mapState = 1; // 确保每次新游戏都从第一关开始
                loadNextLevel(); // 加载第一关地图、玩家位置和怪物
                currentState = GameState.MAP;
                break;
            case "2":// 继续游戏
                currentState = GameState.MAP;
                break;
            case "3":// 游戏设置
                pause();
                System.out.println("按回车返回主菜单...");
                input = sc.nextLine();
                break;
            case "4":
                view.showTeamIntroductionView();
                System.out.println("按回车返回主菜单...");
                input = sc.nextLine();
                break;
            case "5":
                view.showGameIntroductionView();
                System.out.println("按回车返回主菜单...");
                input = sc.nextLine();
                break;
            case "6":
                System.exit(0);
            default:
                System.out.println("无效指令！");
                return;
        }
    }

    /**
     * 渲染地图探索状态，处理玩家移动和地图事件触发（如遇怪、捡宝箱、进商店等）
     */
    private static void renderMapState() {
        audio.musicOn(mapState, currentState);
        view.clearScreen();
        view.showGameScreen(mapService.getMapData(), player);

        System.out.print("\n请输入移动指令 (W:上, S:下, A:左, D:右, P:暂停, Q:退出): ");
        String input = sc.nextLine().trim().toUpperCase();

        int nextX = player.x;
        int nextY = player.y;

        switch (input) {
            case "W":
                nextX--;
                audio.soundEffectOn("move");
                break;
            case "S":
                nextX++;
                audio.soundEffectOn("move");
                break;
            case "A":
                nextY--;
                audio.soundEffectOn("move");
                break;
            case "D":
                nextY++;
                audio.soundEffectOn("move");
                break;
            case "Q":
                currentState = GameState.MENU;
                return;
            case "P":
                currentState = GameState.INGAMESET;
                return;
            default:
                System.out.println("无效指令！");
                sc.nextLine();
                return;
        }

        char nextUnitForExit = mapService.getTile(nextX, nextY);
        if (nextUnitForExit == MapService.EXIT) {
            boolean isBossAlive = false;
            for (Monster m : monsters) {
                // 如果怪物名字包含 [Boss] 且存活，说明还没打败 Boss
                if (m.getName().contains("[Boss]") && m.isAlive) {
                    isBossAlive = true;
                    break;
                }
            }

            // 强制玩家必须击败 Boss 才能过关
            if (isBossAlive) {
                System.out.println(RED +"\n【警告】出口被强大的邪恶力量封印！你需要先击败地图上的首领才能进入下一关！" + RESET);
                System.out.println("按回车键继续...");
                sc.nextLine();
                return; // 阻止进入下一关，停留在当前地图
            }


            System.out.println("\n你找到了出口！");

            mapState++; // 下一张地图
            loadNextLevel(); // 加载下一张地图（内部会显示剧情并等待回车）
            return; // 结束当前回合的地图渲染
        }

        // 调用 GameService 处理移动逻辑
        if (service.move(player, nextX, nextY)) {
            // 如果移动成功，则把玩家的坐标，修正为新坐标
            player.setX(nextX);
            player.setY(nextY);

            // 在地图中查找原来的玩家坐标
            int pX = mapService.findElements(mapService.PLAYER).get(0)[0];
            int pY = mapService.findElements(mapService.PLAYER).get(0)[1];

            // 在地图中更新玩家位置
            mapService.updatePosition(pX,pY,player.x,player.y,mapService.PLAYER);

        } else {
            // 下一个要遇到的单位
            char nextUnit = mapService.getTile(nextX,nextY);
            if (nextUnit == 'M' || nextUnit == 'B') {
                // 找到玩家要和哪个怪物对战
                currentEnemy = getMonsterAt(nextX,nextY);
                currentState = GameState.BATTLE;
            } else if (nextUnit == 'S') {
                currentState = GameState.SHOP;
            } else if (nextUnit == 'N') {
                libraryService.randomCase();
                mapService.removeEventChest(nextX, nextY);
            }

        }

    }

    /**
     * 渲染战斗状态并处理回合战斗逻辑
     */
    private static void renderBattleState() {
        view.clearScreen();

        audio.musicOn(mapState, currentState);

        audio.soundEffectOn("attack");
        int battleResult = service.battle(player,currentEnemy);

        if (battleResult == 1) {
            // 获取地图的二维数组
            char[][] map = mapService.getMapData();

            // 将怪物所在的坐标替换为空地
            map[currentEnemy.getX()][currentEnemy.getY()] = MapService.EMPTY;

            // 切换回地图状态
            currentState = GameState.MAP;
            currentEnemy = null;
            return;
        } else if (battleResult == 2) {
            System.out.println("你失败了...");
        } else if (battleResult == 3) {
            currentState = GameState.MAP;
            System.out.println("你成功摆脱了与" + currentEnemy.getName() + "的战斗...");
            sc.nextLine();
            return;
        }

    }

    /**
     * 渲染商店状态
     */
    private static void renderShopState() {
        view.clearScreen();
        audio.musicOn(mapState, currentState);
        shopService.shopFunction(player,sc);
    }

    /*
        功能：根据地图上生成的 'M' 和 'B' 字符，实例化具体的怪物对象，并结合难度系数调整属性
        参数：无
        返回值：无
    */
    public static void initMonsters() {
        Random rand = new java.util.Random();
        // 获取地图上所有M的坐标
        List<int[]> monsterPositions = mapService.findElements(MapService.MONSTER);


        // 遍历这些坐标，生成怪物实体
        int idCounter = 1; // 用于生成怪物ID
        for (int[] pos : monsterPositions) {
            int mx = pos[0];
            int my = pos[1];

            Monster newMonster = null;

            // 实例化怪物
            int monsterType = rand.nextInt(3);

            // 根据随机数，实例化不同的怪物，但赋予它们相同的 mx, my 坐标
            switch (monsterType) {
                case 0:
                    newMonster = new Monster(idCounter, "哥布林", (int) (10 + model * 50), (int) (5 + model * 10), 20, 15, mx, my, true, 0);
                    break;
                case 1:
                    newMonster = new Monster(idCounter, "巨魔", (int) (30 + model * 50), (int) (10 + model * 10), 40, 30, mx, my, true, 0);
                    break;
                case 2:
                    newMonster = new Monster(idCounter, "火龙", (int) (70 + model * 50), (int) (15 + model * 10), 80, 50, mx, my, true, 0);
                    break;
            }
            monsters.add(newMonster);

            idCounter++;
        }

        List<int[]> bossPositions = mapService.findElements(MapService.BOSS);
        for (int[] pos : bossPositions) {
            int bx = pos[0];
            int by = pos[1];
            // 实例化 Boss
            switch (mapState) {
                case 1:
                    Monster boss0 = new Monster(idCounter, "哥布林酋长 [Boss]", (int) (50 + model * 80), (int) (5 + model * 15), 200, 200, bx, by, true, 0);
                    monsters.add(boss0);
                    break;
                case 2:
                    Monster boss1 = new Monster(idCounter, "黑暗守卫 [Boss]", (int) (150 + model * 80), (int) (15 + model * 15), 300, 300, bx, by, true, 0);
                    monsters.add(boss1);
                    break;
                case 4:
                    Monster boss3 = new Monster(idCounter, "怒翼巨龙 [Boss]", (int) (300 + model * 80), (int) (20 + model * 15), 500, 500, bx, by, true, 0);
                    monsters.add(boss3);
                    break;
                case 5:
                    Monster boss4 = new Monster(idCounter, "深渊魔王 [Boss]", (int) (500 + model * 100), (int) (25 + model * 15), 1000, 1200, bx, by, true, 0);
                    monsters.add(boss4);
                    break;
            }


            idCounter++;
        }
    }

    /*
        功能：根据坐标查找对应的存活怪物
        参数：x, y, 玩家试图进入的坐标
        返回值：匹配的 Monster 对象，如果没有找到则返回 null
    */
    public static Monster getMonsterAt(int x, int y) {
        for (Monster m : monsters) {
            // 如果坐标匹配，并且怪物是存活状态
            if (m.x == x && m.y == y && m.isAlive) {
                return m;
            }
        }
        return null; // 没找到
    }

    /*功能：游戏内设置
    返回值：无
    参数：无
    */
    /*1.继续游戏
    2.音乐开，3.音乐关
    4.音效开，5.音效关
    6.保存并返回主菜单
     */
    public static void pause(){
        view.inGameMenu();
        int choose;
        do{
            System.out.print("请输入选择：");
            choose=sc.nextInt();
            switch(choose){
                case 1:
                    currentState=GameState.MAP;
                    audio.musicOn(mapState, currentState);
                    break;
                case 2:
                    audio.setMusicEnabled(true);
                    audio.musicOn(mapState, currentState);
                    System.out.println("音乐已开启");
                    break;
                case 3:
                    audio.setMusicEnabled(false);
                    System.out.println("音乐已关闭");
                    break;
                case 4:
                    audio.setSoundEffectEnabled(true);
                    System.out.println("音效已开启");
                    break;
                case 5:
                    audio.setSoundEffectEnabled(false);
                    System.out.println("音效已关闭");
                    break;
                case 6:
                    sc.nextLine();
                    dataService.saveGame(player);
                    System.out.println("游戏进度已保存");
                    currentState=GameState.MENU;
            }
        }while(choose!=1&&choose!=6);
    }


    /**
     * 创建角色流程
     * 处理玩家输入的名字和职业选择，并根据职业初始化对应的初始属性流派
     */
    public static int pickCharacters(){
        System.out.print("勇士啊，请输入你的名字：  ");
        String name = sc.nextLine();
        view.pickCharacter();
        System.out.println("请输入要选择的职业");
        String flag =  sc.nextLine();
        switch (flag) {
            case "1":
                player.setName(name);
                player.setExp(0);
                player.setLevel(1);
                player.setGold(0);
                player.setMaxHp(150);
                player.setHp(150);
                player.setCharacter("战士");
                player.setStamina(50);
                player.setMaxStamina(50);
                player.setEquippedPotion(null);
                player.setEquippedWeapon(null);
                player.setAttack(20);
                player.setDefense(10);
                break;
            case "2":
                player.setName(name);
                player.setExp(0);
                player.setLevel(1);
                player.setGold(0);
                player.setMaxHp(80);
                player.setHp(80);
                player.setCharacter("刺客");
                player.setStamina(50);
                player.setMaxStamina(50);
                player.setEquippedPotion(null);
                player.setEquippedWeapon(null);
                player.setAttack(30);
                player.setDefense(5);
                player.setCriticalHitRate("20%");
                player.setAvoidanceRate("0%");
                break;
            case "3":
                player.setName(name);
                player.setExp(0);
                player.setLevel(1);
                player.setGold(0);
                player.setMaxHp(60);
                player.setHp(60);
                player.setCharacter("游侠");
                player.setStamina(100);
                player.setMaxStamina(100);
                player.setEquippedPotion(null);
                player.setEquippedWeapon(null);
                player.setAttack(40);
                player.setDefense(0);
                player.setCriticalHitRate("50%");
                player.setAvoidanceRate("50%");
                break;
            default:
                System.out.println("输入非法！");
                break;
        }
        return 1;
    }

    /*
        功能：关卡加载引擎
        描述：根据当前 mapState（层数）切换对应的 MapService 子类，播放剧情，并生成该层对应的地图与怪物数据
    */
    public static void loadNextLevel() {
        LibraryService lib = new LibraryService();
        lib.getGamePlot(mapState);

        if (mapState <= 5) {
            System.out.print("【按回车键进入地图...】");
            sc.nextLine();
        }
        // 根据关卡号切换对应的地图服务实例
        switch (mapState) {
            case 1:
                mapService = startMap;
                break;
            case 2:
                mapService = randomMap;
                break;
            case 3:
                mapService = rewardMap;
                break;
            case 4:
                mapService = endMap;
                break;
            case 5:
                mapService = trueEndMap;
                model += 2.0;
                break;
            default:
                System.out.println("恭喜你通关了所有关卡，游戏胜利！");
                System.exit(0);
        }

        // 初始化新地图
        mapService.initMap();

        // 获取新地图中玩家的初始生成位置并设置给 player
        List<int[]> playerPos = mapService.findElements(MapService.PLAYER);
        if (!playerPos.isEmpty()) {
            player.setX(playerPos.get(0)[0]);
            player.setY(playerPos.get(0)[1]);
        }

        // 清空上一关的怪物，重新生成新地图的怪物
        monsters.clear();
        initMonsters();
    }

    public static void main(String[] args) {
        gameLoop();

    }


}
