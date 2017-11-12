package cells.bloodcells;

public class WhiteBloodCell extends BloodCell {

    private int size;

    public WhiteBloodCell(String id, int health, int positionX, int positionY, int size) {
        super(id, health, positionX, positionY);
        this.size = size;
    }

    @Override
    public int calculateEnergy() {
        return (super.getHealth() + this.size) * 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------Cell ").append(super.getId()).append(String.format(" [%d,%d]", super.getPositionX(), super.getPositionY())).append("\r\n");
        sb.append("--------Health: ").append(super.getHealth()).append(" | ");
        sb.append("Size: ").append(this.size).append(" | Energy: ").append(this.calculateEnergy()).append("\r\n");
        return sb.toString();
    }
}
