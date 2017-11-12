package cells.microbes;

public class Fungi extends Microbe {

    public Fungi(String id, int health, int positionX, int positionY, int virulence) {
        super(id, health, positionX, positionY, virulence);
    }

    @Override
    public int calculateEnergy() {
        return (super.getHealth() + super.getVirulence()) / 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------Cell ").append(super.getId()).append(String.format(" [%d,%d]", super.getPositionX(), super.getPositionY())).append("\r\n");
        sb.append("--------Health: ").append(super.getHealth()).append(" | ").append("Virulence: ").append(super.getVirulence()).append(" | Energy: ");
        sb.append(this.calculateEnergy()).append("\r\n");
        return sb.toString();
    }
}
