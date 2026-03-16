package com.architecture.model.service.mapservice;

public class EndMap extends MapService {
    private boolean phase2Activated = false;
    private int phase2ExitX;
    private int phase2ExitY;

    @Override// 重写父类方法 初始化地图
    public void initMap() {
        super.initMap();
        this.width = 30;
        this.height = 18;
        this.map = new char[this.height][this.width];

        for(int i = 0; i < this.height; ++i) {
            for(int j = 0; j < this.width; ++j) {
                this.map[i][j] = ' ';
            }
        }

        this.placeWalls();
        this.placeRandomWalls(15);
        int exitX = 5;
        int exitY = this.width - 3;
        this.map[exitX][exitY] = 'E';
        this.map[this.height - 3][2] = 'P';
        this.pathToExit(exitX, exitY);
        this.placeRandomElement('M', 5);
        this.placeRandomElement('S', 1);
        this.placeRandomElement('B', 1);
        this.phase2ExitX = exitX;
        this.phase2ExitY = exitY;
        System.out.println("最终");
    }
// 创建第二阶段 更难
    public void activatePhase2() {
        this.phase2Activated = true;
        this.width = 60;
        this.height = 22;
        char[][] newMap = new char[this.height][this.width];

        for(int i = 0; i < Math.min(this.height, this.map.length); ++i) {
            for(int j = 0; j < Math.min(this.width, this.map[0].length); ++j) {
                if (this.map[i][j] == 'P') {
                    newMap[i][j] = 'P';
                } else if (this.map[i][j] == 'E') {
                    newMap[i][j] = ' ';
                } else {
                    newMap[i][j] = this.map[i][j];
                }
            }
        }

        for(int i = 0; i < this.height; ++i) {
            for(int j = 0; j < this.width; ++j) {
                if (newMap[i][j] == 0) {
                    newMap[i][j] = ' ';
                }
            }
        }

        this.map = newMap;
        this.placeWalls();
        this.placeRandomWalls(40);
        int newExitX = this.height / 2;
        int newExitY = this.width - 3;
        this.map[newExitX][newExitY] = 'E';
        this.createComplexPath(newExitX, newExitY);
        this.placeRandomElement('M', 15);
        this.placeRandomElement('S', 2);
        this.placeRandomElement('B', 1);
        this.placeRandomElement('N', 1);
        System.out.println("你真的出去了吗");
        System.out.println("\n=== 最终关卡 - 第二阶段（更难）===");
    }

    public boolean checkExit(int playerX, int playerY) {
        if (this.map[playerX][playerY] == 'E') {
            if (!this.phase2Activated) {
                this.activatePhase2();
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private void pathToExit(int targetX, int targetY) {
        int startX = this.height - 3;
        int startY = 2;
        int turnX = this.random.nextInt(this.height - 4) + 2;
        int turnY = this.random.nextInt(this.width - 4) + 2;

        for(int j = startY + 1; j <= turnY; ++j) {
            if (this.map[startX][j] != 'E') {
                this.map[startX][j] = ' ';
            }
        }

        if (turnX >= startX) {
            for(int i = startX + 1; i <= turnX; ++i) {
                if (this.map[i][turnY] != 'E') {
                    this.map[i][turnY] = ' ';
                }
            }
        } else {
            for(int i = turnX; i <= startX; ++i) {
                if (this.map[i][turnY] != ' ') {
                    this.map[i][turnY] = ' ';
                }
            }
        }

        if (targetY >= turnY) {
            for(int j = turnY; j <= targetY; ++j) {
                if (this.map[targetX][j] != 'E') {
                    this.map[targetX][j] = ' ';
                }
            }
        } else {
            for(int j = targetY; j <= turnY; ++j) {
                if (this.map[targetX][j] != 'E') {
                    this.map[targetX][j] = ' ';
                }
            }
        }

    }

    private void createComplexPath(int targetX, int targetY) {
        int startX = this.height - 3;
        int startY = 2;
        int[] turnPointsX = new int[4];
        int[] turnPointsY = new int[4];

        for(int i = 0; i < 4; ++i) {
            turnPointsX[i] = this.random.nextInt(this.height - 6) + 3;
            turnPointsY[i] = this.random.nextInt(this.width - 10) + i * (this.width / 5);
        }

        for(int j = startY + 1; j <= turnPointsY[0]; ++j) {
            this.map[startX][j] = ' ';
        }

        int currentX = startX;
        int currentY = turnPointsY[0];

        for(int i = 0; i < 4; ++i) {
            if (turnPointsX[i] >= currentX) {
                for(int x = currentX + 1; x <= turnPointsX[i]; ++x) {
                    this.map[x][currentY] = ' ';
                }
            } else {
                for(int x = turnPointsX[i]; x <= currentX; ++x) {
                    this.map[x][currentY] = ' ';
                }
            }

            currentX = turnPointsX[i];
            int nextY = i < 3 ? turnPointsY[i + 1] : targetY;
            if (nextY >= currentY) {
                for(int y = currentY + 1; y <= nextY; ++y) {
                    this.map[currentX][y] = ' ';
                }
            } else {
                for(int y = nextY; y <= currentY; ++y) {
                    this.map[currentX][y] = ' ';
                }
            }

            currentY = nextY;
        }

        if (targetX != currentX) {
            if (targetX >= currentX) {
                for(int x = currentX; x <= targetX; ++x) {
                    this.map[x][targetY] = ' ';
                }
            } else {
                for(int x = targetX; x <= currentX; ++x) {
                    this.map[x][targetY] = ' ';
                }
            }
        }

    }

    public void  placeRandomWalls(int count) {
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
}