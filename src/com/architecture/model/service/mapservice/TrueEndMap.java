package com.architecture.model.service.mapservice;

public class TrueEndMap extends MapService {

    @Override
    public void initMap() {
        super.initMap();
        this.width = 15;
        this.height = 10;
        this.map = new char[this.height][this.width];

        // 填充满空地
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.map[i][j] = ' ';
            }
        }

        this.placeWalls();
        this.placeRandomWalls(1);

        int exitX = 2;
        int exitY = this.width - 3;
        this.map[exitX][exitY] = 'E';
        this.map[this.height - 3][2] = 'P';
        this.placeRandomElement('B', 1);

        this.PathToExit(exitX, exitY);

        this.placeRandomElement('M', 1);
        this.placeRandomElement('N', 1);


        System.out.println("警告：你已踏入最终魔王的领地！邪恶气息让怪物变得异常强大！");
    }
}