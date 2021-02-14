import java.util.ArrayList;
import java.util.Arrays;

public class DijikstraMain {

    public static void main(String[] args) {

        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        int selectedNode = 0;

        int[] nodeDistances = dijikstra(graph, selectedNode);

        printSolution(nodeDistances);
    }

    private static int[] dijikstra(int[][] graph, int selectedNode) {

        int verticeCount = graph[0].length;
        int nodeDistances[] = new int[verticeCount];
        Arrays.fill(nodeDistances, Integer.MAX_VALUE);

        // starter node is 0
        nodeDistances[selectedNode] = 0;
        ArrayList<Integer> progressedNodes = new ArrayList<Integer>();
        int nodeDistanceTotal = 0;

        for (int k = 0; k < verticeCount; k++) {

            selectedNode = getMinimumDistanceNode(nodeDistances, progressedNodes);
            progressedNodes.add(selectedNode);

            for (int i = 0; i < graph[selectedNode].length; i++) {
                if (graph[selectedNode][i] != 0 && !progressedNodes.contains(i)) {
                    nodeDistanceTotal = nodeDistances[selectedNode] + graph[selectedNode][i];
                    if (nodeDistanceTotal < nodeDistances[i]) {
                        nodeDistances[i] = nodeDistances[selectedNode] + graph[selectedNode][i];
                    }
                }
            }

        }
        return nodeDistances;
    }

    private static int getMinimumDistanceNode(int nodeDistances[], ArrayList<Integer> progressedNodes) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < nodeDistances.length; i++) {
            if (nodeDistances[i] != Integer.MAX_VALUE && !progressedNodes.contains(i) && nodeDistances[i] < min) {
                min = nodeDistances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void printSolution(int nodeDistances[]) {
        for (int i = 0; i < nodeDistances.length; i++)
            System.out.println(i + " \t\t " + nodeDistances[i]);
    }


}
