package com.architecture.model.service.mapservice;

public class StartMap extends MapService{
    @Override
    public void initMap() {
        super.initMap();
        this.width = 30;
        this.height = 10;
        this.map = new char[this.height][this.width];
        for(int i = 0; i < this.height; ++i) {
            for(int j = 0; j < this.width; ++j) {
                this.map[i][j] = ' ';
            }
        }
        this.placeWalls();
        int exitX = 5;
        int exitY = this.width - 3;
        this.map[exitX][exitY] = 'E';
        this.map[this.height - 3][2] = 'P';
        this.placeRandomElement('S', 1);
        this.placeRandomElement('T', 1);
        this.placeRandomElement('M', 3);
        this.placeRandomElement('B', 1);
        this.placeRandomElement('N', 2);
        System.out.println("地图初始化完成！");
        System.out.println("s"+"代表商店");
        System.out.println("T"+"代表宝箱");
        System.out.println("M"+"代表怪兽");
        System.out.println("E"+"代表出口");
        System.out.println("P"+"代表玩家");
        System.out.println("N"+"代表事件");
        System.out.println("B"+"代表领主");
        System.out.println("请开始你的冒险之旅！");
    }
}
