package clusters;

import cells.Cell;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Cluster {

    private String id;
    private int rows;
    private int cols;
    private List<Cell> cells;

    public Cluster(){}

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new LinkedList<>();
    }

    public String getId() {
        return this.id;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----Cluster ").append(this.id).append("\r\n");
        Comparator<Cell> byRow = Comparator.comparing(Cell::getPositionX);
        Comparator<Cell> byCol = Comparator.comparing(Cell::getPositionY);
        List<Cell> sorted = this.cells.stream().sorted(byRow.thenComparing(byCol)).collect(Collectors.toList());
        for (Cell cell : sorted) {
            sb.append(cell.toString());
        }
        return sb.toString();
    }
}
