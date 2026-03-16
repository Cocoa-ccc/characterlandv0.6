package com.architecture.model.service.mapservice;

public class RandomMap extends MapService{
    public void initMap() {
        super.initMap();
        this.width = 30;
        this.height = 20;
        this.map = new char[this.height][this.width];

        for(int i = 0; i < this.height; ++i) {
            for(int j = 0; j < this.width; ++j) {
                this.map[i][j] = ' ';
            }
        }

        this.placeWalls();
        this.placeRandomWalls(20);
        int exitX = 3;
        int exitY = this.width - 3;
        this.map[exitX][exitY] = 'E';
        this.map[this.height - 3][2] = 'P';
        this.PathToExit(exitX, exitY);
        this.placeRandomElement('M', 8);
        this.placeRandomElement('S', 1);
        this.placeRandomElement('B', 1);
        this.placeRandomElement('N', 2);
        System.out.println("地图初始化完成！");
    }
}
