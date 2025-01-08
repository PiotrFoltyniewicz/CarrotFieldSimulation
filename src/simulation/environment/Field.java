package simulation.environment;

import java.awt.Point;
import java.util.*;

public class Field {

    private List<List<Tile>> tiles;
    private int fieldSize;

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        tiles = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < fieldSize; i++) {
            List<Tile> row = Collections.synchronizedList(new ArrayList<>());
            for (int j = 0; j < fieldSize; j++) {
                row.add(new Tile(j, i));
            }
            tiles.add(row);
        }
    }

    public List<Tile> getSurroundingTiles(Point currPos, int range) {
        List<Tile> surrTiles = new ArrayList<>();
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                Point tilePos = new Point(x, y);
                if (calculateDistance(tilePos, currPos) < range) {
                    surrTiles.add(tiles.get(x).get(y));
                }
            }
        }
        return surrTiles;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Tile getTile(int x, int y) {
        return tiles.get(x).get(y);
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
