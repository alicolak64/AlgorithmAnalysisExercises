import java.util.Random;
import java.util.Scanner;

public class CompleteBinaryTreeMinPath {

    /************************* COMPLETE BINARY TREE *************************/
    private static class CompleteBinaryTree {

        Node root;

        int size;

        int[] nodeCosts;

        private static class Node {
            int key;
            int id;

            int cost;
            Node left;
            Node right;

            public Node(int id, int key) {
                this.id = id;
                this.key = key;
            }

        }

        public CompleteBinaryTree(int[] keys, int[][] costs) {

            this.size = keys.length;
            this.nodeCosts = new int[size];

            this.root = fillCompleteBinaryTree(null, keys, 0);

            this.root.cost = this.root.key;
            addPathCosts(root, costs);

            fillNodeCosts(root);

        }

        private Node fillCompleteBinaryTree(Node root, int[] keys, int index) {

            if (index < keys.length) {

                root = new Node(index, keys[index]);

                root.left = fillCompleteBinaryTree(root.left, keys, 2 * index + 1);
                root.right = fillCompleteBinaryTree(root.right, keys, 2 * index + 2);

            }

            return root;
        }

        private void addPathCosts(Node node, int[][] costs) {

            if (node == null)
                return;

            if (node.left != null) {
                node.left.cost += node.left.key;
                node.left.cost += costs[node.id][node.left.id];
            }

            if (node.right != null) {
                node.right.cost += node.right.key;
                node.right.cost += costs[node.id][node.right.id];
            }

            addPathCosts(node.left, costs);
            addPathCosts(node.right, costs);

        }

        public void fillNodeCosts(Node root) {
            if (root == null)
                return;
            fillNodeCosts(root.left);
            nodeCosts[root.id] = root.cost;
            fillNodeCosts(root.right);
        }

        public void printTree() {
            printTree(this.root, 0);
        }

        private void printTree(Node node, int level) {

            if (node == null)
                return;

            printTree(node.right, level + 1);

            if (level != 0) {

                for (int i = 0; i < level - 1; i++)
                    System.out.print("|\t");

                System.out.println("|-------" + node.key);

            } else
                System.out.println(node.key);

            printTree(node.left, level + 1);

        }


    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size value : ");
        final int LENGTH = scanner.nextInt();

        final int[] NODE_WEIGHTS = new int[LENGTH];
        final int[][] EDGE_WEIGHTS = new int[LENGTH][LENGTH];

        fillNodeWeights(NODE_WEIGHTS);
        fillEdgeWeights(EDGE_WEIGHTS);

        /*
        System.out.println("\n----- Node Weights Array -----\n");
        printArray(NODE_WEIGHTS);

        System.out.println("\n----- Edge Weights Array -----\n");
        printArray(EDGE_WEIGHTS);

         */

        final CompleteBinaryTree COMPLETE_BINARY_TREE = new CompleteBinaryTree(NODE_WEIGHTS, EDGE_WEIGHTS);

        System.out.println("\n----- Greedy Solution -----\n");

        greedySolutionWithPath(COMPLETE_BINARY_TREE);
        int greedySolution = greedySolution(COMPLETE_BINARY_TREE);

        System.out.println("\n----- Recursive Solution -----\n");

        recursiveSolutionWithPath(COMPLETE_BINARY_TREE);
        int recursiveSolution = recursiveSolution(COMPLETE_BINARY_TREE);

        System.out.println("\n----- Dynamic Programming Solution -----\n");

        dynamicProgrammingSolutionWithPath(COMPLETE_BINARY_TREE);
        int dynamicProgrammingSolution = dynamicProgrammingSolution(COMPLETE_BINARY_TREE);

        System.out.println("\n-------------------------------\n");

        System.out.println("Greedy Solution : " + greedySolution);
        System.out.println("Recursive Solution : " + recursiveSolution);
        System.out.println("Dynamic Programming Solution : " + dynamicProgrammingSolution);


        if (greedySolution == recursiveSolution)
            System.out.println("\nGreedy algorithms have followed a successful path with the given input values.");
        else
            System.out.println("\nGreedy algorithms have followed a failed path with the given input values.");

        System.out.println("\n-------------------------------\n");

        System.out.println("\n-----  Three Different Input Sizes And Compute The Actual Running Times -----\n");

        double[][] times = new double[3][3];

        long startTimeGreedy, endTimeGreedy;
        long startTimeRecursive, endTimeRecursive;
        long startTimeDynamicProgramming, endTimeDynamicProgramming;

        /*********************** -----  Input Size : 100 ----- *************************/

        final int[] NODE_WEIGHTS_100 = new int[100];
        final int[][] EDGE_WEIGHTS_100 = new int[100][100];

        fillNodeWeights(NODE_WEIGHTS_100);
        fillEdgeWeights(EDGE_WEIGHTS_100);

        final CompleteBinaryTree COMPLETE_BINARY_TREE_100 = new CompleteBinaryTree(NODE_WEIGHTS_100, EDGE_WEIGHTS_100);


        startTimeGreedy = getTime();
        greedySolution(COMPLETE_BINARY_TREE_100);
        endTimeGreedy = getTime();

        startTimeRecursive = getTime();
        recursiveSolution(COMPLETE_BINARY_TREE_100);
        endTimeRecursive = getTime();

        startTimeDynamicProgramming = getTime();
        dynamicProgrammingSolution(COMPLETE_BINARY_TREE_100);
        endTimeDynamicProgramming = getTime();


        times[0][0] = getTimeDifference(startTimeGreedy, endTimeGreedy);
        times[1][0] = getTimeDifference(startTimeRecursive, endTimeRecursive);
        times[2][0] = getTimeDifference(startTimeDynamicProgramming, endTimeDynamicProgramming);


        /************************* -----  Input Size : 1000 ----- *************************/

        final int[] NODE_WEIGHTS_1000 = new int[1000];
        final int[][] EDGE_WEIGHTS_1000 = new int[1000][1000];

        fillNodeWeights(NODE_WEIGHTS_1000);
        fillEdgeWeights(EDGE_WEIGHTS_1000);

        final CompleteBinaryTree COMPLETE_BINARY_TREE_1000 = new CompleteBinaryTree(NODE_WEIGHTS_1000, EDGE_WEIGHTS_1000);

        startTimeGreedy = getTime();
        greedySolution(COMPLETE_BINARY_TREE_1000);
        endTimeGreedy = getTime();

        startTimeRecursive = getTime();
        recursiveSolution(COMPLETE_BINARY_TREE_1000);
        endTimeRecursive = getTime();

        startTimeDynamicProgramming = getTime();
        dynamicProgrammingSolution(COMPLETE_BINARY_TREE_1000);
        endTimeDynamicProgramming = getTime();

        times[0][1] = getTimeDifference(startTimeGreedy, endTimeGreedy);
        times[1][1] = getTimeDifference(startTimeRecursive, endTimeRecursive);
        times[2][1] = getTimeDifference(startTimeDynamicProgramming, endTimeDynamicProgramming);


        /************************* -----  Input Size : 10000 ----- *************************/

        final int[] NODE_WEIGHTS_10000 = new int[10000];
        final int[][] EDGE_WEIGHTS_10000 = new int[10000][10000];

        fillNodeWeights(NODE_WEIGHTS_10000);
        fillEdgeWeights(EDGE_WEIGHTS_10000);

        final CompleteBinaryTree COMPLETE_BINARY_TREE_10000 = new CompleteBinaryTree(NODE_WEIGHTS_10000, EDGE_WEIGHTS_10000);

        startTimeGreedy = getTime();
        greedySolution(COMPLETE_BINARY_TREE_10000);
        endTimeGreedy = getTime();

        startTimeRecursive = getTime();
        recursiveSolution(COMPLETE_BINARY_TREE_10000);
        endTimeRecursive = getTime();

        startTimeDynamicProgramming = getTime();
        dynamicProgrammingSolution(COMPLETE_BINARY_TREE_10000);
        endTimeDynamicProgramming = getTime();

        times[0][2] = getTimeDifference(startTimeGreedy, endTimeGreedy);
        times[1][2] = getTimeDifference(startTimeRecursive, endTimeRecursive);
        times[2][2] = getTimeDifference(startTimeDynamicProgramming, endTimeDynamicProgramming);

        System.out.println("\n----- Actual Running Times -----\n");

        System.out.println("Input Size" + "    " + "Greedy" + "       " + "Recursive" + "      " + "Dynamic Programming");

        for (int i = 0; i < 3; i++)
            System.out.println("    " + (int) (Math.pow(10, i)) + "00" + "       " + times[0][i] + "         " + times[1][i] + "          " + times[2][i]);


    }


    /************************* FILL ARRAYS *************************/

    public static void fillNodeWeights(int[] nodeWeights) {

        Random random = new Random();

        for (int i = 0; i < nodeWeights.length; i++)
            nodeWeights[i] = random.nextInt(20) + 1;

    }

    public static void fillEdgeWeights(int[][] edgeWeights) {

        Random random = new Random();

        int i = 0;

        while (2 * i + 1 <= edgeWeights.length - 1) {
            edgeWeights[i][2 * i + 1] = random.nextInt(20) + 1;
            i++;
        }

        i = 0;

        while (2 * i + 2 <= edgeWeights.length - 1) {
            edgeWeights[i][2 * i + 2] = random.nextInt(20) + 1;
            i++;
        }

    }


    /************************* GREEDY SOLUTION *************************/


    public static int greedySolution(CompleteBinaryTree completeBinaryTree) {

        int minCost = completeBinaryTree.root.key;

        CompleteBinaryTree.Node currentNode = completeBinaryTree.root;

        while (currentNode.left != null && currentNode.right != null) {

            int leftCost = currentNode.left.cost;
            int rightCost = currentNode.right.cost;

            if (leftCost < rightCost) {
                minCost += leftCost;
                currentNode = currentNode.left;
            } else {
                minCost += rightCost;
                currentNode = currentNode.right;
            }

        }

        if (currentNode.left != null)
            minCost += currentNode.left.cost;

        return minCost;

    }

    public static void greedySolutionWithPath(CompleteBinaryTree completeBinaryTree) {

        int minCost = completeBinaryTree.root.key;

        int index = 0;

        CompleteBinaryTree.Node currentNode = completeBinaryTree.root;

        StringBuilder path = new StringBuilder("0");

        while (currentNode.left != null && currentNode.right != null) {

            int leftCost = currentNode.left.cost;
            int rightCost = currentNode.right.cost;

            if (leftCost < rightCost) {
                minCost += leftCost;
                currentNode = currentNode.left;
                index = 2 * index + 1;
            } else {
                minCost += rightCost;
                currentNode = currentNode.right;
                index = 2 * index + 2;
            }

            path.append(" -> ").append(index);

        }

        if (currentNode.left != null) {
            minCost += currentNode.left.cost;
            path.append(" -> ").append(2 * index + 1);
        }

        System.out.println("Greedy Solution : Min total weight path includes nodes " + path
                + "  with total weight " + minCost + ".");

    }

    /************************* RECURSIVE SOLUTION *************************/

    public static int recursiveSolution(CompleteBinaryTree completeBinaryTree) {

        return recursiveSolution(completeBinaryTree.root);

    }

    private static int recursiveSolution(CompleteBinaryTree.Node node) {

        if (node == null)
            return 0;
        else if (node.left != null && node.right == null)
            return node.cost + node.left.cost;

        int leftCost = recursiveSolution(node.left);
        int rightCost = recursiveSolution(node.right);

        return node.cost + Math.min(leftCost, rightCost);

    }


    public static void recursiveSolutionWithPath(CompleteBinaryTree completeBinaryTree) {

        int minCost = recursiveSolution(completeBinaryTree.root);

        String path = recursiveSolutionPath(completeBinaryTree.root);

        System.out.println("Recursive Solution : Min total weight path includes nodes " + path
                + "  with total weight " + minCost + ".");

    }


    private static String recursiveSolutionPath(CompleteBinaryTree.Node node) {

        if (node == null)
            return "";
        else if (node.left == null && node.right == null)
            return node.id + "";

        int leftCost = recursiveSolution(node.left);
        int rightCost = recursiveSolution(node.right);

        if (leftCost < rightCost || node.left != null && node.right == null)
            return node.id + " -> " + recursiveSolutionPath(node.left);
        else
            return node.id + " -> " + recursiveSolutionPath(node.right);

    }

    /************************* DYNAMIC SOLUTION *************************/


    public static int dynamicProgrammingSolution(CompleteBinaryTree completeBinaryTree) {

        int[] costs = new int[completeBinaryTree.size];

        costs[0] = completeBinaryTree.root.cost;

        for (int i = 0; i < completeBinaryTree.size; i++) {

            if (2 * i + 1 < completeBinaryTree.size)
                costs[2 * i + 1] = costs[i] + completeBinaryTree.nodeCosts[2 * i + 1];

            if (2 * i + 2 < completeBinaryTree.size)
                costs[2 * i + 2] = costs[i] + completeBinaryTree.nodeCosts[2 * i + 2];
        }

        int minCost = Integer.MAX_VALUE;

        for (int i = completeBinaryTree.size / 2; i < completeBinaryTree.size; i++)
            minCost = Math.min(minCost, costs[i]);

        return minCost;

    }

    public static void dynamicProgrammingSolutionWithPath(CompleteBinaryTree completeBinaryTree) {

        int[] costs = new int[completeBinaryTree.size];

        costs[0] = completeBinaryTree.root.cost;

        for (int i = 0; i < completeBinaryTree.size; i++) {
            if (2 * i + 1 < completeBinaryTree.size)
                costs[2 * i + 1] = costs[i] + completeBinaryTree.nodeCosts[2 * i + 1];

            if (2 * i + 2 < completeBinaryTree.size)
                costs[2 * i + 2] = costs[i] + completeBinaryTree.nodeCosts[2 * i + 2];
        }

        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = completeBinaryTree.size / 2; i < completeBinaryTree.size; i++)
            if (costs[i] < minCost) {
                minCost = costs[i];
                minIndex = i;
            }

        String path = dynamicProgrammingSolutionPath(minIndex);

        System.out.println("Dynamic Programming Solution : Min total weight path includes nodes " + path
                + "  with total weight " + minCost + ".");

    }

    private static String dynamicProgrammingSolutionPath(int minIndex) {

        StringBuilder path = new StringBuilder(minIndex + "");

        while (minIndex != 0) {
            minIndex = (minIndex - 1) / 2;
            path.insert(0, minIndex + " -> ");
        }

        return path.toString();

    }


    /************************* PRINT ARRAY *************************/


    private static void printArray(int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }

    private static void printArray(int[][] array) {

        for (int[] row : array) {

            for (int element : row)
                System.out.print(element + " ");

            System.out.println();

        }

    }

    /************************* CALCULATE TIME *************************/

    // return the time in nanoseconds
    public static long getTime() {
        return System.nanoTime();
    }

    // It takes the difference of the nanosecond type time values and converts the result to ms.
    public static double getTimeDifference(long startTime, long finishTime) {
        return (finishTime - startTime) / 1000000.0;
    }

}
