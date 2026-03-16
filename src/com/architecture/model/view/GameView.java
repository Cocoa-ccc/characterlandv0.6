package com.architecture.model.view;

import com.architecture.model.entity.Monster;
import com.architecture.model.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*
    界面类：负责打印控制台内容
*/
public class GameView {
    final String CYAN = "\033[36m";
    final String GRAY = "\033[37m";
    final String GREEN = "\033[32m";
    final String RED = "\033[31m";
    final String YELLOW = "\033[33m";
    final String RESET = "\033[0m";
    String BROWN = "\u001B[38;5;130m";
    final String BRIGHT_MAGENTA = "\033[95m"; // 高亮品红
    final String BRIGHT_CYAN = "\033[96m";    // 高亮青
    String BOLD = "\u001B[1m";    // 加粗
    String PURPLE = "\u001B[35m"; // 紫色
    String WHITE = "\u001B[37m";  // 白色
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";



    /*
        功能：打印标题
        参数：无
        返回值：无
    */
    public void showTitle() {
        String[] titleLines = {
                "   ______ __                             __               __                    __ ",
                "  / ____// /_   ____ _ _____ ____ _ ____/ /_ ___   _____ / /   ____ _ ____   __/ /",
                " / /    / __ \\ / __ `// ___// __ `// ___// __// _ \\ / ___// /   / __ `// __ \\ / __ /",
                "/ /___ / / / // /_/ // /   / /_/ // /__ / /_ /  __// /   / /___/ /_/ // / / // /_/ / ",
                "\\____//_/ /_/ \\__,_//_/    _,_/ \\___/ _/ \\___//_/   /_____/\\__,_//_/ /_/ \\__,_/ "
        };

        // 定义用于每一行的颜色序列
        String[] colors = {ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN};

        // 循环打印每一行，并应用不同的颜色
        for (int i = 0; i < titleLines.length; i++) {
            System.out.println(colors[i % colors.length] + titleLines[i] + ANSI_RESET);
        }
    }

    /*
        功能：打印菜单
        参数：无
        返回值：无
    */
    public void showMenuView() {
        System.out.println(CYAN + "  ====字符大陆====");
        System.out.println(GREEN + "\t[1]  开始游戏");
        System.out.println(GREEN + "\t[2]  继续游戏");
        System.out.println(GREEN + "\t[3]  游戏设置");
        System.out.println(GREEN + "\t[4]  团队介绍");
        System.out.println(GREEN + "\t[5]  游戏介绍");
        System.out.println(GREEN + "\t[6]  退出游戏");
        System.out.println(CYAN + "==================");
        System.out.println();
        System.out.print("\033[0m");
    }

    /*
        功能：展示玩法介绍和背景剧情
        参数：无
        返回值：无
    */
    public void showGameIntroductionView() {
            // 1. 顶部
            System.out.println(CYAN + "╔═══════════════════════════════════════════════════════════════════════════════════" + RESET);

            // 2. 标题
            System.out.println(CYAN + "║" + RESET + "                        " + CYAN + BOLD + "《字符大陆》" + RESET + WHITE + "                           " + CYAN + "" + RESET);

            // 3. 分割线
            System.out.println(CYAN + "║" + RESET + "-----------------------------------------------------------------------------" + CYAN + "" + RESET);

            // 4. 背景故事 (使用灰色字体区分)
            System.out.println(CYAN + "║" + RESET + "            " + GRAY + BOLD + "[背景故事]" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + GRAY + BOLD + "在一个名为泰拉的星球上高度发达的科技最终导致了文明的毁灭。" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + GRAY + BOLD + "泰拉星崩碎后，残留的一块陆地脱离了原有的空间，在异次元中漂流，这块陆地就是字符大陆" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + GRAY + BOLD + "你作为'冒险家'，必须探索这片大陆，修复世界！" + RESET);

            // 5. 空行过渡
            System.out.println(CYAN + "║" + RESET + "                                                              " + CYAN + "" + RESET);

            // 6. 玩法介绍 (高亮关键按键)
            System.out.println(CYAN + "║" + RESET + "            " + WHITE + BOLD + "[核心玩法]" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + WHITE + BOLD + "探索：按 " + GREEN + BOLD + "WASD" + RESET + WHITE + BOLD + " 移动，遇敌自动战斗。" + "          " + CYAN + "" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + WHITE + BOLD + "战斗：输入 " + GREEN + BOLD + "数字序号" + RESET + WHITE + BOLD + " 释放技能，策略取胜。" + "      " + CYAN + "" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + WHITE + BOLD + "商店：购买武器药水强化自身，按 " + GREEN + BOLD + "Enter" + RESET + WHITE + BOLD + " 确认。" + "     " + CYAN + "" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + WHITE + BOLD + "存档：按 " + YELLOW + BOLD + "P键" + RESET + WHITE + BOLD + " 保存进度并返回主菜单。" + "    " + CYAN + "" + RESET);

            // 7. 底部装饰线
            System.out.println(CYAN + "║" + RESET + "-----------------------------------------------------------------------------" + CYAN + "" + RESET);
            System.out.println(CYAN + "║" + RESET + "            " + GREEN + BOLD + ">>> 现在开始你的冒险之旅吧 <<<" + RESET + WHITE + "          " + CYAN + "" + RESET);
            // 8. 底部
            System.out.println(CYAN + "╚═══════════════════════════════════════════════════════════════════════════════════" + RESET);
        }

    /*
        功能：展示团队介绍界面
        参数：无
        返回值：无
    */
    public void showTeamIntroductionView() {
        System.out.println(CYAN + "====团队介绍====");
        System.out.println(GREEN + "我们是Java不加班团队，");
        System.out.println(GREEN + "致力于开发一个有趣的RPG-字符大陆,");
        System.out.println(GREEN + "期待您来体验我们制作的游戏。");
        System.out.println(GREEN + "团队成员：");
        System.out.println(RED + "\t1. 川清浅 - 项目负责人");
        System.out.println(PURPLE + "\t2. 亚尔山卓 - 技术官");
        System.out.println(GREEN + "\t3. 洛郡 - 技术官");
        System.out.println(BROWN + "\t4. 福林 - 技术官");
        System.out.println(ANSI_BLUE + "\t5. THGRET - 信息官");
        System.out.println(BRIGHT_CYAN + "\t6. 下次 - 产品经理");
        System.out.println(BRIGHT_MAGENTA + "\t7. 许铮 - 产品经理" + RESET);
        System.out.println(CYAN + "==================");
        System.out.println();
        System.out.print("\033[0m");
    }

    /*
        功能：展示设置界面
        参数：无
        返回值：无
    */
    public void showSettingsView() {
        System.out.println(CYAN + "====游戏设置====");
        System.out.println(GREEN + "1.继续游戏");
        System.out.println(GREEN + "2.开启音乐");
        System.out.println(GREEN + "3.关闭音乐");
        System.out.println(GREEN + "4.开启音效");
        System.out.println(GREEN + "5.关闭音效");
        System.out.println(CYAN + "==================");
        System.out.println(YELLOW + "请输入你想要选择的设置：");
        System.out.println();
        System.out.print("\033[0m");
    }

    /*
        功能：打印玩家状态栏
        参数：无
        返回值：无
    */
    public void showPlayerStatusBarView(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(CYAN).append("====玩家状态栏====\n");
        sb.append(GREEN).append("玩家名称：").append(player.getName()).append("\n");
        sb.append(GREEN).append("玩家等级：").append(player.getLevel()).append("\n");
        sb.append(GREEN).append("当前经验：").append(player.getExp()).append("\n");
        sb.append(GREEN).append("升级所需经验值：").append(player.getExpToNextLevel()).append("\n");
        sb.append(GREEN).append("当前武器：").append(player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "无").append("\n");
        sb.append(GREEN).append("当前药水：").append(player.getEquippedPotion() != null ? player.getEquippedPotion().getName() : "无").append("\n");

        int hpPercentage = Math.max(0, Math.min(100, (player.getHp() * 100) / player.getMaxHp()));
        String hpColor = hpPercentage > 60 ? GREEN : hpPercentage > 30 ? YELLOW : RED;
        sb.append(hpColor).append("当前生命值：").append(player.getHp()).append("/").append(player.getMaxHp()).append("\n");
        sb.append(hpColor).append("生命值：[");
        for (int i = 0; i < 20; i++) {
            sb.append(i < (hpPercentage / 5) ? "█" : " ");
        }
        sb.append("] " + hpPercentage + "%\n");

        sb.append(GREEN).append("体力值：").append(player.getStamina()).append("\n");
        sb.append(GREEN).append("攻击力：").append(player.getAttack()).append("\n");
        sb.append(GREEN).append("防御力：").append(player.getDefense()).append("\n");
        sb.append(GREEN).append("持有金币：").append(player.getGold()).append("\n");
        sb.append(CYAN).append("==================\n\n");
        sb.append(RESET);
        System.out.print(sb.toString());
        System.out.print("\033[0m");
    }

    /*
        功能：打印怪物状态栏
        参数：无
        返回值：无
    */
    public void showMonsterStatusBarView(Monster monster) {
        System.out.println(CYAN + "====怪物状态栏====");
        System.out.println(GREEN + "怪物种类编号：" + monster.getId());
        System.out.println(GREEN + "怪物名称：" + monster.getName());
        System.out.println(GREEN + "怪物生命值：" + monster.getHp());
        System.out.println(GREEN + "怪物攻击力：" + monster.getAttack());
        System.out.println(GREEN + "击败掉落金币：" + monster.getExp());
        System.out.println(GREEN + "击败怪物掉落的经验值：" + monster.getExp());
        System.out.println(GREEN + "怪物存活状态（true为存活，false为死亡）：" + monster.isAlive);
        System.out.println(CYAN + "==================");
        System.out.println();
        System.out.print("\033[0m");
    }
    /*
    1.攻击
    2.使用小血瓶
    3.使用大血瓶
    4.逃跑
    */
    public void battleMenu(){
        System.out.println(CYAN + "====战斗页面====");
        System.out.println(GREEN + "1.战斗");
        System.out.println(GREEN + "2.使用血瓶");
        System.out.println(GREEN + "3.逃跑");
        System.out.println(CYAN + "==================");
        System.out.println(YELLOW + "请输入对应的操作：");
        System.out.println();
        System.out.print("\033[0m");
    }

    /**商店菜单
    */
    public void shopMenu(){
        System.out.println(CYAN + "====商店====");
        System.out.println(GREEN + "1.武器");
        System.out.println(GREEN + "2.药水");
        System.out.println(GREEN + "3.退出商店");
        System.out.println(CYAN + "==================");
        System.out.println(YELLOW + "请输入对应选择的序号，按回车确定……");
        System.out.println();
        System.out.print("\033[0m");
    }

    /*
        功能：打印下方文本框
        参数：String sentence
        返回值：无
    */
    public void showTextBox(String sentence) {
        System.out.println(CYAN + "====消息框====");
        System.out.println(sentence);
        System.out.println(CYAN + "=============");
        System.out.println(GREEN + "按回车键继续……");
        System.out.println(CYAN + "==================");
        System.out.println();
        System.out.print("\033[0m");
    }

    /*
    功能：打印游戏内的菜单
    参数：无
    返回值：无
     */
    public void inGameMenu(){
        System.out.println(CYAN + "====菜单====");
        System.out.println(GREEN + "1.继续游戏");
        System.out.println(GREEN + "2.开启音乐");
        System.out.println(GREEN + "3.关闭音乐");
        System.out.println(GREEN + "4.开启音效");
        System.out.println(GREEN + "5.关闭音效");
        System.out.println(YELLOW + "6.保存进度并返回标题");
        System.out.println(CYAN + "==================");
        System.out.println(YELLOW + "请输入你想要选择的操作：");
        System.out.println();
        System.out.println("\033[0m");
    }

    /*打印玩家选择菜单
    参数：无
    返回值：无
     */
    public void pickCharacter(){
        System.out.println(CYAN + "====角色选择菜单====");
        System.out.println(RED + "1. 战士  - 高血量高防御，擅长近战" + RESET);
        System.out.println(PURPLE + "2. 刺客   - 高暴击高闪避，擅长偷袭" + RESET);
        System.out.println(GREEN + "3. 游侠  - 高攻击高体力，极致的攻击" + RESET);
        System.out.println(CYAN + "==================");
        System.out.println(YELLOW + "请输入你想要选择的角色的编号：");
        System.out.println();
        System.out.print("\033[0m");
    }


    /*
        功能：同屏并排打印地图和状态栏
        参数：地图的二维数组，玩家实体
    */
    public void showGameScreen(char[][] map, Player player) {
        List<String> statusLines = new ArrayList<>();

        // 将带有颜色的状态栏信息逐行存入 List，并在每行末尾加上 RESET 防止颜色泄漏
        statusLines.add(CYAN + "====玩家状态栏====" + RESET);
        statusLines.add(GREEN + "玩家名称：" + player.getName() + RESET);
        statusLines.add(GREEN + "职业：" + player.getCharacter() + RESET);
        statusLines.add(GREEN + "玩家等级：" + player.getLevel() + RESET);
        statusLines.add(GREEN + "当前经验：" + player.getExp() + RESET);
        statusLines.add(GREEN + "升级所需经验值：" + player.getExpToNextLevel() + RESET);
        statusLines.add(GREEN + "当前武器：" + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "无") + RESET);
        statusLines.add(GREEN + "当前药水：" + (player.getEquippedPotion() != null ? player.getEquippedPotion().getName() : "无") + RESET);

        // 计算生命值百分比与颜色
        int hpPercentage = Math.max(0, Math.min(100, (player.getHp() * 100) / player.getMaxHp()));
        String hpColor = hpPercentage > 60 ? GREEN : hpPercentage > 30 ? YELLOW : RED;

        statusLines.add(hpColor + "当前生命值：" + player.getHp() + "/" + player.getMaxHp() + RESET);

        // 拼接动态图形血条
        StringBuilder hpBar = new StringBuilder();
        hpBar.append(hpColor).append("生命值：[");
        for (int i = 0; i < 20; i++) {
            hpBar.append(i < (hpPercentage / 5) ? "█" : " ");
        }
        hpBar.append("] ").append(hpPercentage).append("%").append(RESET);
        statusLines.add(hpBar.toString());

        statusLines.add(GREEN + "体力值：" + player.getStamina() + RESET);
        statusLines.add(GREEN + "攻击力：" + player.getAttack() + RESET);
        statusLines.add(GREEN + "防御力：" + player.getDefense() + RESET);
        statusLines.add(GREEN + "暴击率：" + player.getCriticalHitRate() + RESET);
        statusLines.add(GREEN + "闪避率：" + player.getAvoidanceRate() + RESET);
        statusLines.add(GREEN + "持有金币：" + player.getGold() + RESET);
        statusLines.add(CYAN + "==================" + RESET);


        // 2. 准备渲染拼接
        int mapHeight = map.length;
        int mapWidth = map[0].length;

        // 算出地图和状态栏哪个行数更多
        int maxLines = Math.max(mapHeight, statusLines.size());

        // 3. 逐行拼接打印
        for (int i = 0; i < maxLines; i++) {
            // -- 左侧：打印地图的一行 --
            if (i < mapHeight) {
                for (int j = 0; j < mapWidth; j++) {
                    char tile = map[i][j];
                    // 在这里对地图元素进行颜色判断和渲染
                    switch (tile) {
                        case 'P':
                            System.out.print(GREEN + tile + RESET + " ");
                            break;
                        case '#':
                            System.out.print(BROWN + '■' + RESET + " ");
                            break;
                        case '*':
                            System.out.print(BROWN + '◆' + RESET + " ");
                            break;
                        case 'S':
                            System.out.print(YELLOW + tile + RESET + " ");
                            break;
                        case 'T':
                            System.out.print(YELLOW + tile + RESET + " ");
                            break;
                        case 'M':
                            System.out.print(ANSI_BLUE + tile + RESET + " ");
                            break;
                        case 'E':
                            System.out.print(PURPLE + '▓' + RESET + " "); // 出口用紫色标识
                            break;
                        case 'B':
                            System.out.print(RED + tile + RESET + " "); // Boss用红色标识
                            break;
                        case 'N':
                            System.out.print(PURPLE + tile + RESET + " "); // 随机事件用紫色标识
                            break;
                        default:
                            System.out.print(tile + " "); // 空地正常输出
                            break;
                    }
                }
            } else {
                // 如果地图行数少于状态栏行数，用空格补齐左侧的空白
                for (int j = 0; j < mapWidth; j++) {
                    System.out.print("  ");
                }
            }

            // -- 中间：打印分隔用的空格 --
            System.out.print("        ");

            // -- 右侧：打印状态栏的一行 --
            if (i < statusLines.size()) {
                System.out.print(statusLines.get(i));
            }

            // 本行打印完毕，换行
            System.out.println();
        }
    }

    /*
        辅助工具：计算包含中文字符和ANSI颜色代码的字符串的视觉真实长度
    */
    private int getVisualLength(String str) {
        String cleanStr = str.replaceAll("\033\\[[;\\d]*m", "");
        int len = 0;
        for (int i = 0; i < cleanStr.length(); i++) {
            char c = cleanStr.charAt(i);
            // 宽字符,如中文,在控制台中通常算作 2 个英文字符的宽度
            if (c > 255) {
                len += 2;
            } else {
                len += 1;
            }
        }
        return len;
    }

    /*
        功能：同屏并排打印玩家与怪物的战斗状态，并在下方展示操作菜单
        参数：玩家实体，怪物实体
    */
    public void showBattleScreen(Player player, Monster monster) {
        List<String> playerLines = new ArrayList<>();
        List<String> monsterLines = new ArrayList<>();

        playerLines.add(CYAN + "====玩家状态栏====" + RESET);
        playerLines.add(GREEN + "玩家名称：" + player.getName() + RESET);
        playerLines.add(GREEN + "职业：" + player.getCharacter() + RESET);
        playerLines.add(GREEN + "玩家等级：" + player.getLevel() + RESET);
        playerLines.add(GREEN + "当前经验：" + player.getExp() + RESET);
        playerLines.add(GREEN + "升级所需经验值：" + player.getExpToNextLevel() + RESET);
        playerLines.add(GREEN + "当前武器：" + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "无") + RESET);
        playerLines.add(GREEN + "当前药水：" + (player.getEquippedPotion() != null ? player.getEquippedPotion().getName() : "无") + RESET);

        int hpPercentage = Math.max(0, Math.min(100, (player.getHp() * 100) / player.getMaxHp()));
        String hpColor = hpPercentage > 60 ? GREEN : hpPercentage > 30 ? YELLOW : RED;
        playerLines.add(hpColor + "当前生命值：" + player.getHp() + "/" + player.getMaxHp() + RESET);

        StringBuilder hpBar = new StringBuilder();
        hpBar.append(hpColor).append("生命值：[");
        for (int i = 0; i < 20; i++) {
            hpBar.append(i < (hpPercentage / 5) ? "█" : " ");
        }
        hpBar.append("] ").append(hpPercentage).append("%").append(RESET);
        playerLines.add(hpBar.toString());

        playerLines.add(GREEN + "体力值：" + player.getStamina() + RESET);
        playerLines.add(GREEN + "攻击力：" + player.getAttack() + RESET);
        playerLines.add(GREEN + "暴击率：" + player.getCriticalHitRate() + RESET);
        playerLines.add(GREEN + "闪避率：" + player.getAvoidanceRate() + RESET);
        playerLines.add(GREEN + "防御力：" + player.getDefense() + RESET);
        playerLines.add(GREEN + "持有金币：" + player.getGold() + RESET);
        playerLines.add(CYAN + "==================" + RESET);

        monsterLines.add(CYAN + "====怪物状态栏====" + RESET);
        monsterLines.add(GREEN + "怪物种类编号：" + monster.getId() + RESET);
        monsterLines.add(GREEN + "怪物名称：" + monster.getName() + RESET);
        monsterLines.add(RED + "怪物生命值：" + monster.getHp() + RESET); // 给怪物的血量标红提示
        monsterLines.add(GREEN + "怪物攻击力：" + monster.getAttack() + RESET);
        monsterLines.add(GREEN + "击败掉落金币：" + monster.getRewardGold() + RESET);
        monsterLines.add(GREEN + "击败掉落经验：" + monster.getExp() + RESET);
        monsterLines.add(GREEN + "怪物存活状态：" + monster.isAlive + RESET);
        monsterLines.add(CYAN + "==================" + RESET);

        int maxLines = Math.max(playerLines.size(), monsterLines.size());
        int leftColumnWidth = 45; // 左侧玩家状态栏的固定视觉宽度

        for (int i = 0; i < maxLines; i++) {
            String leftStr = i < playerLines.size() ? playerLines.get(i) : "";
            String rightStr = i < monsterLines.size() ? monsterLines.get(i) : "";

            // 打印左侧字符串
            System.out.print(leftStr);

            // 动态计算需要补齐的空格数量，确保右侧对齐
            int paddingSpaces = leftColumnWidth - getVisualLength(leftStr);
            for (int j = 0; j < Math.max(0, paddingSpaces); j++) {
                System.out.print(" ");
            }

            // 打印右侧字符串并换行
            System.out.println(rightStr);
        }

        // ================= 4. 打印下方战斗菜单 =================
        System.out.println();
        battleMenu();
    }

    /* 战士技能菜单*/
    public void fighterSkills() {
        System.out.println(BOLD + YELLOW + "\n=== 战士战斗菜单 ===" + RESET);
        System.out.println(WHITE + "1. 普通斩击" + RESET + " : 基础攻击 " + CYAN + "[体力 -0]" + RESET);
        System.out.println(YELLOW + "2. 护身战吼" + RESET + " : 吓晕敌人，本回合怪物不攻击 " + CYAN + "[体力 -10]" + RESET);
        System.out.println(YELLOW + "3. 我的刀盾" + RESET + " : 攻击力+10(1回合)，防御力+10(持续到战斗结束) " + CYAN + "[体力 -20]" + RESET);
        System.out.println(RED + "4. 浴血重生" + RESET + " : 血量回复100，并增加10点防御力!! " + CYAN + "[体力 -30]" + RESET);
        System.out.println(WHITE + "5. 返回" + RESET);
        System.out.println(YELLOW + "=========================" + RESET);
    }

    /* 刺客技能菜单*/
    public void raiderSkills() {
        System.out.println(BOLD + PURPLE + "\n=== 刺客战斗菜单 ===" + RESET);
        System.out.println(WHITE + "1. 暗影突袭" + RESET + " : 基础攻击 " + CYAN + "[体力 -0]" + RESET);
        System.out.println(RED + "2. 吸收拳  " + RESET + " : 本回合攻击并吸血，回复量等于未暴击伤害 " + CYAN + "[体力 -20]" + RESET);
        System.out.println(GREEN + "3. 淬毒穿刺" + RESET + " : 攻击力增加20 " + CYAN + "[体力 -20]" + RESET);
        System.out.println(PURPLE + "4. 影流之主" + RESET + " : 攻击力乘3，并进行1次强力攻击!! " + CYAN + "[体力 -50]" + RESET);
        System.out.println(WHITE + "5. 返回" + RESET);
        System.out.println(PURPLE + "=========================" + RESET);
    }

    /* 游侠技能菜单*/
    public void errantSkills() {
        System.out.println(BOLD + GREEN + "\n=== 游侠战斗菜单 ===" + RESET);
        System.out.println(WHITE + "1. 追命箭  " + RESET + " : 基础攻击 " + CYAN + "[体力 -0]" + RESET);
        System.out.println(CYAN + "2. 冰霜箭  " + RESET + " : 怪物攻击力减20(1回合) " + CYAN + "[体力 -10]" + RESET);
        System.out.println(GREEN + "3. 战术后撤" + RESET + " : 游侠躲避攻击，血量+10 " + CYAN + "[体力 -10]" + RESET);
        System.out.println(YELLOW + "4. 苍穹裁决" + RESET + " : 一飞冲天，万箭齐发，对怪物造成100点真实伤害!! " + CYAN + "[体力 -30]" + RESET);
        System.out.println(WHITE + "5. 返回" + RESET);
        System.out.println(GREEN + "=========================" + RESET);
    }

    /*
        底层渲染工具：
        功能：
            控制台画面刷新与防闪烁优化
            使用 ANSI 转义序列进行清屏重绘
    */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
}