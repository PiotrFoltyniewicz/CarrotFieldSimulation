package simulation.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.actions.*;
import simulation.environment.Tile;

public class Dog extends DynamicEntity {

    private Tile targetTile;

    public Dog(int posX, int posY, int sightRange) {
        super(posX, posY, sightRange);
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private boolean chooseKillRabbit() {
        return getCurrentTile().getHasRabbit();
    }

    private void spotNearestRabbit() {
        if (targetTile != null && targetTile.getHasRabbit()) {
            return;
        }
        double distance = Double.MAX_VALUE;
        for (Tile tile : surroundingTiles) {
            if (tile.getHasRabbit()) {
                double currDist = calculateDistance(tile.getPosition(), position);

                if (targetTile == null) {
                    targetTile = tile;
                    distance = currDist;

                } else if (currDist < distance) {
                    targetTile = tile;
                    distance = currDist;
                }
            }
        }
    }

    private void randomMove() {
        List<Tile> possibleTiles = new ArrayList<>();
        for (Tile tile : surroundingTiles) {
            if (tile != getCurrentTile() && calculateDistance(tile.getPosition(), position) <= 1.1) {
                possibleTiles.add(tile);
            }
        }
        Random random = new Random();
        position = possibleTiles.get(random.nextInt(possibleTiles.size())).getPosition();
    }

    private void targetMove() {
        List<Tile> possibleTiles = new ArrayList<>();
        for (Tile tile : surroundingTiles) {
            if (tile != getCurrentTile() && calculateDistance(tile.getPosition(), position) <= 1.1) {
                possibleTiles.add(tile);
            }
        }
        Point bestPosition = possibleTiles.get(0).getPosition();
        double bestDist = calculateDistance(bestPosition, targetTile.getPosition());
        for (Tile tile : possibleTiles) {
            double currDist = calculateDistance(tile.getPosition(), targetTile.getPosition());
            if (currDist < bestDist) {
                bestPosition = tile.getPosition();
                bestDist = currDist;
            }
        }
        position = bestPosition;
    }

    @Override
    public EntityAction getAction() {
        if (chooseKillRabbit()) {
            return new KillRabbitAction(position.x, position.y);
        }
        spotNearestRabbit();
        if (targetTile != null) {
            targetMove();
        } else {
            randomMove();
        }
        return new NoneAction();
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }

}
