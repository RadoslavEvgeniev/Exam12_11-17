package organisms;

import clusters.Cluster;

import java.util.LinkedHashMap;
import java.util.Map;

public class Organism {

    private String name;
    private Map<String, Cluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedHashMap<>();
    }

    public Map<String, Cluster> getClusters() {
        return this.clusters;
    }

    public void addCluster(String id, Cluster cluster) {
        this.clusters.put(id, cluster);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organism - ").append(this.name).append("\r\n");
        sb.append("--Clusters: ").append(this.clusters.size()).append("\r\n");
        int cellsSize = 0;
        for (Cluster cluster : clusters.values()) {
            cellsSize += cluster.getCells().size();
        }
        sb.append("--Cells: ").append(cellsSize).append("\r\n");
        for (Cluster cluster : clusters.values()) {
            sb.append(cluster.toString());
        }
        return sb.toString();
    }
}
