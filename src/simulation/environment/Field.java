package simulation.environment;

public class Field {

    private Tile[][] tiles;
    private int fieldSize;

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        tiles = new Tile[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

}
