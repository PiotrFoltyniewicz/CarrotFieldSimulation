package simulation.environment;

public class Tile {

    private boolean hasRabbit;
    private boolean hasCarrot;
    private boolean isDestroyed;

    public boolean getHasRabbit() {
        return hasRabbit;
    }

    public boolean getHasCarrot() {
        return hasCarrot;
    }

    public boolean getIsDestroyed() {
        return isDestroyed;
    }

    public void setHasRabbit(boolean hasRabbit) {
        this.hasRabbit = hasRabbit;
    }

    public void setHasCarrot(boolean hasCarrot) {
        this.hasCarrot = hasCarrot;
    }

    public void setIsDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

}
