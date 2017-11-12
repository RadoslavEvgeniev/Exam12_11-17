package cells.microbes;

public class Virus extends Microbe{

    public Virus(String id, int health, int positionX, int positionY, int virulence) {
        super(id, health, positionX, positionY, virulence);
    }

    @Override
    public int calculateEnergy() {
        return super.getHealth() + super.getVirulence();
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
