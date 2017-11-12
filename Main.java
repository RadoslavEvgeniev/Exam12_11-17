import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HealthManager manager = new HealthManager();
        while (true) {
            String inputLine = scanner.nextLine();
            if (inputLine.equals("BEER IS COMING")) {
                break;
            }
            String[] tokens = inputLine.split(" ");
            String command = tokens[0];
            String organism = tokens[1];
            String clusterId = "";
            int rows = 0;
            int cols = 0;
            String result = "";
            switch (command) {
                case "createOrganism":
                    System.out.println(manager.createOrganism(organism));
                    break;
                case "addCluster":
                    clusterId = tokens[2];
                    rows = Integer.parseInt(tokens[3]);
                    cols = Integer.parseInt(tokens[4]);
                    String addClusterResult = manager.addCluster(organism, clusterId, rows, cols);
                    if (!addClusterResult.equals("")) {
                        System.out.println(addClusterResult);
                    }
                    break;
                case "addCell":
                    clusterId = tokens[2];
                    String cellType = tokens[3];
                    String cellId = tokens[4];
                    int health = Integer.parseInt(tokens[5]);
                    int row = Integer.parseInt(tokens[6]);
                    int col = Integer.parseInt(tokens[7]);
                    int additionalProperty = Integer.parseInt(tokens[8]);
                    result = manager.addCell(organism, clusterId, cellType, cellId, health, row, col, additionalProperty);
                    if (!result.equals("")) {
                        System.out.println(result);
                    }
                    break;
                case "activateCluster":
                    result = manager.activateCluster(organism);
                    if (!result.equals("")) {
                        System.out.println(result);
                    }
                    break;
                case "checkCondition":
                    try {
                        if (!manager.checkCondition(organism).equals("")) {
                            System.out.printf("%s", manager.checkCondition(organism));
                        }
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    }
}
