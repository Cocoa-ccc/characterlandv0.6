package com.architecture.model.service.mapservice;
public class RewardMap extends MapService{

    @Override
    public void initMap() {
        this.width = 20;
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
        this.placeRandomElement('S', 3);
        this.placeRandomElement('T', 3);
        this.placeRandomElement('N', 3);
    }
}



