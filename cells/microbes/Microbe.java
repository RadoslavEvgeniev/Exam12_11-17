package cells.microbes;

import cells.Cell;

public abstract class Microbe extends Cell {

    private int virulence;

    public Microbe(String id, int health, int positionX, int positionY, int virulence) {
        super(id, health, positionX, positionY);
        this.virulence = virulence;
    }

    protected int getVirulence() {
        return this.virulence;
    }

}
