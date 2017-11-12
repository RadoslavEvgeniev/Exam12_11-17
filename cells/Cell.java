package cells;

public abstract class Cell {

    private String id;
    private int health;
    private int positionX;
    private int positionY;

    public Cell(String id, int health, int positionX, int positionY) {
        this.id = id;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected String getId() {
        return this.id;
    }

    public int getHealth() {
        return this.health;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public abstract int calculateEnergy();

    @Override
    public String toString() {
        return super.toString();
    }
}
