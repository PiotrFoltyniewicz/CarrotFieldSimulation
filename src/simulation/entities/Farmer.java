package simulation.entities;

import java.awt.Point;
import java.util.*;
import simulation.actions.*;
import simulation.environment.*;

public class Farmer extends DynamicEntity {

    private Dog ownedDog;

    public Farmer(int posX, int posY, int sightRange) {
        super(posX, posY, sightRange);
        ownedDog = new Dog(posX, posY, sightRange);
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private void move() {
        List<Tile> possibleTiles = new ArrayList<>();
        for (Tile tile : surroundingTiles) {
            if (tile != getCurrentTile() && calculateDistance(tile.getPosition(), position) <= 1.1) {
                possibleTiles.add(tile);
            }
        }
        Random random = new Random();
        position = possibleTiles.get(random.nextInt(possibleTiles.size())).getPosition();
    }

    private boolean chooseRepairTile() {

        return getCurrentTile().getIsDestroyed();
    }

    private void spotNearestRabbit() {
        Tile closestRabbitToDog = null;
        double distanceToDog = Double.MAX_VALUE;
        for (Tile tile : surroundingTiles) {
            if (tile.getHasRabbit()) {
                double currDist = calculateDistance(tile.getPosition(), ownedDog.getPosition());

                if (closestRabbitToDog == null) {
                    closestRabbitToDog = tile;
                    distanceToDog = currDist;

                } else if (currDist < distanceToDog) {
                    closestRabbitToDog = tile;
                    distanceToDog = currDist;
                }
            }
        }
        if (closestRabbitToDog != null) {
            ownedDog.setTargetTile(closestRabbitToDog);
        }
    }

    private boolean choosePlantCarrot() {
        return !getCurrentTile().getHasCarrot();
    }

    @Override
    public EntityAction getAction() {

        spotNearestRabbit();
        if (chooseRepairTile()) {
            return new RepairTileAction(position.x, position.y);
        }
        if (choosePlantCarrot()) {
            return new PlantCarrotAction(position.x, position.y);
        }
        move();
        return new NoneAction();

    }

    public Dog getDog() {
        return ownedDog;
    }
}
