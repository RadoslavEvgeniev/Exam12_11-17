import cells.Cell;
import cells.bloodcells.BloodCell;
import cells.bloodcells.RedBloodCell;
import cells.bloodcells.WhiteBloodCell;
import cells.microbes.Bacteria;
import cells.microbes.Fungi;
import cells.microbes.Virus;
import clusters.Cluster;
import organisms.Organism;

import java.util.*;
import java.util.stream.Collectors;

public class HealthManager {

    private Map<String, Organism> organisms;

    public HealthManager() {
        this.organisms = new LinkedHashMap<>();
    }

    public String createOrganism(String name) {
        Organism organism = new Organism(name);
        if (!this.organisms.containsKey(name)) {
            this.organisms.put(name, organism);
            return String.format("Created organism %s", name);
        } else {
            return String.format("Organism %s already exists", name);
        }
    }

    public String addCluster(String organismName, String id, int rows, int cols) {
        Cluster cluster = new Cluster(id, rows, cols);
        if (this.organisms.containsKey(organismName) && !this.organisms.get(organismName).getClusters().containsKey(id)) {
            this.organisms.get(organismName).addCluster(id, cluster);
            return String.format("Organism %s: Created cluster %s", organismName, id);
        }
        return "";
    }

    public String addCell(String organismName, String clusterId, String cellType, String cellId, int health, int positionX, int positionY, int additionalProperty) {
        if (!this.organisms.containsKey(organismName) || !this.organisms.get(organismName).getClusters().containsKey(clusterId)) {
            return "";
        } else {
            int clusterRow = this.organisms.get(organismName).getClusters().get(clusterId).getRows();
            int clusterCol = this.organisms.get(organismName).getClusters().get(clusterId).getCols();
            if ((positionX > clusterRow - 1 || positionX < 0) || ( positionY > clusterCol - 1 || positionY < 0)) {
                return "";
            }
        }
        switch (cellType) {
            case "WhiteBloodCell":
                WhiteBloodCell bloodCell = new WhiteBloodCell(cellId, health, positionX, positionY, additionalProperty);
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(bloodCell);
                break;
            case "RedBloodCell":
                RedBloodCell redBloodCell = new RedBloodCell(cellId, health, positionX, positionY, additionalProperty);
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(redBloodCell);
                break;
            case "Bacteria":
                Bacteria bacteria = new Bacteria(cellId, health, positionX, positionY, additionalProperty);
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(bacteria);
                break;
            case "Virus":
                Virus virus = new Virus(cellId, health, positionX, positionY, additionalProperty);
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(virus);
                break;
            case "Fungi":
                Fungi fungi = new Fungi(cellId, health, positionX, positionY, additionalProperty);
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(fungi);
                break;
        }
        return String.format("Organism %s: Created cell %s in cluster %s", organismName, cellId, clusterId);
    }

    public String activateCluster(String organismName) {
        if (!this.organisms.containsKey(organismName) || this.organisms.get(organismName).getClusters().size() < 1) {
            return "";
        }
        String clusterName = "";
        try {
            for (Cluster cluster : this.organisms.get(organismName).getClusters().values()) {
                clusterName = cluster.getId();
                Comparator<Cell> byRow = Comparator.comparing(Cell::getPositionX);
                Comparator<Cell> byCol = Comparator.comparing(Cell::getPositionY);
                List<Cell> sorted = cluster.getCells().stream().sorted(byRow.thenComparing(byCol)).collect(Collectors.toList());
                for (int i = 1; i < sorted.size(); i++) {
                    if (sorted.get(0) instanceof BloodCell) {
                        sorted.get(0).setHealth(sorted.get(0).getHealth() + sorted.get(i).getHealth());
                        sorted.get(0).setPositionX(sorted.get(i).getPositionX());
                        sorted.get(0).setPositionY(sorted.get(i).getPositionY());
                    } else {
                        while (true) {
                            sorted.get(i).setHealth(sorted.get(i).getHealth() - sorted.get(0).calculateEnergy());
                            if (sorted.get(i).getHealth() <= 0) {
                                sorted.get(0).setPositionX(sorted.get(i).getPositionX());
                                sorted.get(0).setPositionY(sorted.get(i).getPositionY());
                                break;
                            }
                            sorted.get(0).setHealth(sorted.get(0).getHealth() - sorted.get(i).calculateEnergy());
                            if (sorted.get(0).getHealth() <= 0) {
                                sorted.set(0, sorted.get(i));
                                break;
                            }
                        }
                    }
                }
                cluster.getCells().clear();
                cluster.addCell(sorted.get(0));
                break;
            }
        } catch (Exception e){}
        Cluster cluster = this.organisms.get(organismName).getClusters().get(clusterName);
        this.organisms.get(organismName).getClusters().remove(clusterName);
        this.organisms.get(organismName).getClusters().putIfAbsent(clusterName, cluster);
        int size = cluster.getCells().size();
        return String.format("Organism %s: Activated cluster %s. Cells left: %d", organismName, cluster.getId(), size);
    }

    public String checkCondition(String organismName) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).toString();
        } else {
            return "";
        }
    }
}
