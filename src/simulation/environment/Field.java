package simulation.environment;

public class Field {

    private Tile[][] tiles;
    private int fieldSize;

    public int getFieldSize() {
        return fieldSize;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

}
