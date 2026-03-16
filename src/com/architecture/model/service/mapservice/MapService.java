package com.architecture.model.service.mapservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    地图类：负责地图初始化和数据
*/
public class MapService {
    /*
        定义地图常量，方便以后修改字符
        墙：#
        空地：‘ ’
        玩家：P
        怪物：M
        出口：E
    */
    public static final char WALL = '#';
    public static final char EMPTY = ' ';
    public static final char PLAYER = 'P';
    public static final char MONSTER = 'M';
    public static final char EXIT = 'E';
    public static final char SHOP = 'S';
    public static final char TREASURE_CHAR = 'T';// 宝箱符号
    public static final char BOSS = 'B';// 宝箱符号
    public static final char EVENT = 'N';// 随机事件
    public char[][] map;       // 二维数组表示地图
    public int width;          // 地图的宽
    public int height;         // 地图的高
    public Random random = new Random();   // 随机数对象
    private List<int[]> treasureChests = new ArrayList<>(); // 存储未开启的宝箱坐标 [x,y]
    private List<int[]> enventChests = new ArrayList<>(); // 存储未开启的事件坐标 [x,y]

    /*
        初始化地图：铺设墙壁、空地、怪物、出口
    */
    public void initMap() {
        /*width = 20;
        height = 20;
        map = new char[height][width];
        */


        // 此处修改地图参数
        width = 30;
        height = 20;
        map = new char[height][width];

        placeWalls();
        // 放置怪物
        placeRandomElement(MONSTER, 3);
        // 放置boss
        placeRandomElement(BOSS, 1);
        // 放置出口
        placeRandomElement(EXIT, 1);
        // 放置玩家
        placeRandomElement(PLAYER, 1);
        // 放置商店
        placeRandomElement(SHOP, 1);
        // 放置随机事件
        placeRandomElement(EVENT, 2);
        //全赋值为空地
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = EMPTY;
            }
        }
        //铺设边缘墙
        placeWalls();
        // 创建随机墙体 可设置数量
        placeRandomWalls(10);
        // 创建出口和玩家
        int exitX = 3;
        int exitY = width - 3;
        map[exitX][exitY] = EXIT;
        map[height - 3][2] = PLAYER;
        //创建路径通向出口
        PathToExit(exitX, exitY);
        // 放置怪物和商店 可设置数量
        placeRandomElement(MONSTER, 8);
        placeRandomElement(SHOP, 1);
        // 放置宝箱（数量2个，可自定义）
        placeRandomElement(TREASURE_CHAR, 2);
        // 记录宝箱初始坐标（用于后续判断是否开启）
        treasureChests = findElements(TREASURE_CHAR);
    }

    //创建路径通向出口
    public void PathToExit(int targetX, int targetY) {
        // 从左侧中间开始
        int startX = height - 3;
        int startY = 2;
        //x y拐点
        int turnX = random.nextInt(height - 4) + 2;
        int turnY = random.nextInt(width - 4) + 2;

        // 清除路径上的所有障碍，创建 L 型路径
        // 第一段：水平向右
        for (int j = startY + 1; j <= turnY; j++) {
            if (map[startX][j] != EXIT) {
                map[startX][j] = EMPTY;
            }
        }

        // 第二段：垂直向上/向下到转弯点
        if (turnX >= startX) {
            for (int i = startX + 1; i <= turnX; i++) {
                if (map[i][turnY] != EXIT) {
                    map[i][turnY] = EMPTY;
                }
            }
        } else {
            for (int i = turnX; i <= startX; i++) {
                if (map[i][turnY] != EXIT) {
                    map[i][turnY] = EMPTY;
                }
            }
        }

        // 第三段：水平到出口
        if (targetY >= turnY) {
            for (int j = turnY; j <= targetY; j++) {
                if (map[targetX][j] != EXIT) {
                    map[targetX][j] = EMPTY;
                }
            }
        } else {
            for (int j = targetY; j <= turnY; j++) {
                if (map[targetX][j] != EXIT) {
                    map[targetX][j] = EMPTY;
                }
            }
        }
    }

    //创建随机城墙增加难度
    public void placeRandomWalls(int count) {
        int placed = 0;

        while(placed < count) {
            int rx = this.random.nextInt(this.height);
            int ry = this.random.nextInt(this.width);
            if (rx >= 1 && rx <= this.height - 2 && ry >= 1 && ry <= this.width - 2 && this.map[rx][ry] == ' ') {
                int direction = this.random.nextInt(2);
                boolean canPlace = true;
                if (direction == 0) {
                    if (ry + 4 >= this.width - 1) {
                        canPlace = false;
                    } else {
                        for(int i = 0; i < 5; ++i) {
                            if (this.map[rx][ry + i] != ' ') {
                                canPlace = false;
                                break;
                            }
                        }
                    }
                } else if (rx + 4 >= this.height - 1) {
                    canPlace = false;
                } else {
                    for(int i = 0; i < 5; ++i) {
                        if (this.map[rx + i][ry] != ' ') {
                            canPlace = false;
                            break;
                        }
                    }
                }

                if (canPlace) {
                    if (direction == 0) {
                        for(int i = 0; i < 5; ++i) {
                            this.map[rx][ry + i] = '*';
                        }
                    } else {
                        for(int i = 0; i < 5; ++i) {
                            this.map[rx + i][ry] = '*';
                        }
                    }

                    ++placed;
                }
            }
        }

    }

    // 铺设墙壁
    public void placeWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    map[i][j] = WALL;
                } else {
                    map[i][j] = EMPTY;
                }
            }
        }
    }

    /*
        功能：
            在地图空地上随机放置指定元素
        参数：
            element：要放置的元素，例如怪物
            count：要放置的数量
        返回值：
            无
    */
    // 如果地图没有足够的空地，会无限循环
    public void placeRandomElement(char element, int count) {
        int placed = 0;
        while (placed < count) {
            int rx = random.nextInt(height);
            int ry = random.nextInt(width);
            if (map[rx][ry] == EMPTY) {
                map[rx][ry] = element;
                placed++;
            }
        }
    }

    /*
        功能：获取指定坐标的内容
        参数：
            x,y代表指定位置的坐标
        返回值：
            返回指定位置的内容
    */
    public char getTile(int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width) {
            return WALL;
        }
        if (map[x][y] == WALL) {
            return WALL;
        }
        if (map[x][y] == PLAYER) {
            return PLAYER;
        }
        if (map[x][y] == MONSTER) {
            return MONSTER;
        }
        if (map[x][y] == EXIT) {
            return EXIT;
        }
        if (map[x][y] == SHOP) {
            return SHOP;
        }
        if (map[x][y] == BOSS) {
            return BOSS;
        }

        return map[x][y];
    }

    /*
        功能：更新地图上的实体位置
        参数：
            oldX,oldY:原位置坐标
            newX,newY:新位置坐标
            element:要生成的东西
        返回值：无
    */
    public void updatePosition(int oldX, int oldY, int newX, int newY, char element) {
        // 判断是否可以移动
        if (map[newX][newY] == WALL) {
            System.out.println("无法移动到墙壁上！");
            return;
        }

        map[oldX][oldY] = EMPTY;
        map[newX][newY] = element;

    }

    /*
        功能：获取地图数据
        参数：无
        返回值：
            map[][]
    */
    public char[][] getMapData() {
        return map;
    }

    /*
        功能：打印地图
        参数：无
        返回值：无
    */
    public void displayMap() {
        map = getMapData();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        功能：查找地图中指定元素的所有坐标
        参数：
            targetElement：要查找的字符,如MapService.MONSTER, MapService.PLAYER
        返回值：
            返回一个 List，里面包含了所有找到的坐标数组 [x, y]
            如果没找到，则返回空列表
    */
    public List<int[]> findElements(char targetElement) {
        List<int[]> positions = new ArrayList<>();

        // 遍历整个地图二维数组
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // 如果当前格子的字符匹配目标字符
                if (map[i][j] == targetElement) {
                    // 将坐标打包成数组存入 List
                    positions.add(new int[]{i, j});
                }
            }
        }

        return positions;
    }

    // 移除已开启的宝箱（从地图和列表中删除）
    public void removeTreasureChest(int x, int y) {
        treasureChests.removeIf(chest -> chest[0] == x && chest[1] == y);
        if (x >= 0 && x < height && y >=0 && y < width) {
            map[x][y] = EMPTY;
        }
    }

    // 移除已经触发过的事件
    public void removeEventChest(int x, int y) {
        enventChests.removeIf(chest -> chest[0] == x && chest[1] == y);
        if (x >= 0 && x < height && y >=0 && y < width) {
            map[x][y] = EMPTY;
        }
    }

    // 获取所有未开启的宝箱坐标
    public List<int[]> getTreasureChests() {
        return new ArrayList<>(treasureChests); // 返回副本，避免外部修改
    }

    // 获取所有未开启的宝箱坐标
    public List<int[]> getEventChests() {
        return new ArrayList<>(enventChests); // 返回副本，避免外部修改
    }

    /*
   功能：地图库，可以自己决定地图的个数，地图的形状，地图里的怪物分布，出口个数等等,被getMapData调用
   返回值：map[][]
   参数：地图序号
   */
    public char[][] mapLibrary(int mapIndex){
        return map;}

    public List<int[]> getEnventChests() {
        return enventChests;
    }
}

