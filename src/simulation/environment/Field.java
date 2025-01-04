package simulation.environment;

import java.awt.Point;
import java.util.*;

public class Field {

    private Tile[][] tiles;
    private int fieldSize;

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        tiles = new Tile[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                tiles[i][j] = new Tile(j, i);
            }
        }
    }

    public List<Tile> getSurroundingTiles(Point currPos, int range) {
        List<Tile> surrTiles = new ArrayList<>();
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Point tilePos = new Point(j, i);
                if (calculateDistance(tilePos, currPos) < range) {
                    surrTiles.add(tiles[i][j]);
                }
            }
        }
        return surrTiles;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
