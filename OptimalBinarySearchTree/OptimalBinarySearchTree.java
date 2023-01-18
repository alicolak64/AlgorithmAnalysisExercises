import java.util.Random;

public class OptimalBinarySearchTree {

    private record OptimalBST(int[][] costTable, int[][] rootTable, int minCost, BST bst) {

        public static void print(OptimalBST optimalBST) {

            System.out.println("*******    Minimum cost of optimal binary search tree  =  " + optimalBST.minCost + "    *******\n");

            System.out.println("\n*******    Cost Table is     *******\n");
            printArray(optimalBST.costTable);

            System.out.println("\n*******    Root Table is     *******\n");
            printArray(optimalBST.rootTable);

            System.out.println("\n*******    Optimal Binary Search Tree is     *******\n");
            optimalBST.bst.printTree();

            System.out.println("\n*******    Optimal Binary Search Tree Pre Order is     *******\n");
            optimalBST.bst.printPreOrder();

        }

    }

    private static class BST {

        Node root;

        private static class Node {
            int key;
            Node left;
            Node right;

            public Node(int key) {
                this.key = key;
            }

        }

        public BST() {
            this.root = null;
        }

        public void insert(int key) {
            this.root = insert(this.root, key);
        }

        private Node insert(Node node, int key) {

            if (node == null)
                return new Node(key);

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);

            return node;

        }

        public boolean search(int key) {

            return search(this.root, key);

        }

        private boolean search(Node node, int key) {

            if (node == null)
                return false;

            if (node.key == key)
                return true;

            if (key < node.key)
                return search(node.left, key);
            else
                return search(node.right, key);

        }

        public int searchWithCount(int key) {

            Node current = this.root;
            int count = 0;

            while (current != null) {
                count++;
                if (key == current.key)
                    return count;
                else if (key < current.key)
                    current = current.left;
                else
                    current = current.right;
            }

            return count;

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

        public void printInOrder() {
            printInOrder(this.root);
            System.out.println();
        }

        private void printInOrder(Node node) {

            if (node == null)
                return;

            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);

        }

        public void printPreOrder() {
            printPreOrder(this.root);
            System.out.println();
        }

        private void printPreOrder(Node node) {

            if (node == null)
                return;

            System.out.print(node.key + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);

        }

        public void printPostOrder() {
            printPostOrder(this.root);
            System.out.println();
        }

        private void printPostOrder(Node node) {

            if (node == null)
                return;

            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node.key + " ");

        }

    }

    public static class OptimalIntegerBinarySearchTreeTest {

        public static void test() {

            final Random random = new Random();

            final int N = 100;

            final int TEST_COUNT = 100;
            final int[] SEARCH_KEYS = new int[TEST_COUNT];
            final int[] SEARCH_FREQUENCIES = new int[TEST_COUNT];

            final int[] RANDOM_BST_KEYS1 = new int[N];
            final int[] RANDOM_BST_FREQUENCIES1 = new int[N];

            fillKeyArray(RANDOM_BST_KEYS1);
            fillFrequencyArray(RANDOM_BST_FREQUENCIES1);

            BST randomBST1 = createTree(RANDOM_BST_KEYS1);

            final int[] RANDOM_BST_KEYS2 = new int[N];
            final int[] RANDOM_BST_FREQUENCIES2 = new int[N];

            fillKeyArray(RANDOM_BST_KEYS2);
            fillFrequencyArray(RANDOM_BST_FREQUENCIES2);

            BST randomBST2 = createTree(RANDOM_BST_KEYS2);

            final int[] OPTIMAL_BST_KEYS = new int[N];
            final int[] OPTIMAL_BST_FREQUENCIES = new int[N];

            fillKeyArray(OPTIMAL_BST_KEYS);
            fillFrequencyArray(OPTIMAL_BST_FREQUENCIES);

            BST optimalBST = optimalSearchTree(OPTIMAL_BST_KEYS, OPTIMAL_BST_FREQUENCIES).bst;


            for (int i = 0; i < TEST_COUNT; i++) {
                int index = random.nextInt(N);
                SEARCH_KEYS[i] = OPTIMAL_BST_KEYS[index];
                SEARCH_FREQUENCIES[i] = OPTIMAL_BST_FREQUENCIES[index];
            }


            double randomBST1Time = calculateSearchTime(randomBST1, SEARCH_KEYS, SEARCH_FREQUENCIES);
            double randomBST2Time = calculateSearchTime(randomBST2, SEARCH_KEYS, SEARCH_FREQUENCIES);
            double optimalBSTTime = calculateSearchTime(optimalBST, SEARCH_KEYS, SEARCH_FREQUENCIES);


            System.out.println("      *****  Actual Search Time  *****   \n");

            System.out.println("Random BST 1 Time = " + randomBST1Time);
            System.out.println("Random BST 2 Time = " + randomBST2Time);
            System.out.println("Optimal BST Time = " + optimalBSTTime);

            System.out.println("\n      *****  Actual Path Count  *****   \n");

            int randomBST1Count = calculateSearchCount(randomBST1, SEARCH_KEYS, SEARCH_FREQUENCIES);
            int randomBST2Count = calculateSearchCount(randomBST2, SEARCH_KEYS, SEARCH_FREQUENCIES);
            int optimalBSTCount = calculateSearchCount(optimalBST, SEARCH_KEYS, SEARCH_FREQUENCIES);

            System.out.println("Random BST 1 Count = " + randomBST1Count);
            System.out.println("Random BST 2 Count = " + randomBST2Count);
            System.out.println("Optimal BST Count = " + optimalBSTCount);


        }

        public static BST createTree(int[] keys) {

            BST bst = new BST();

            for (int key : keys)
                bst.insert(key);

            return bst;

        }

        public static double calculateSearchTime(BST bst, int[] searchKeys, int[] searchFrequencies) {


            long startTime = getTime();

            for (int i = 0; i < searchKeys.length; i++)
                for (int j = 0; j < searchFrequencies[i]; j++)
                    bst.search(searchKeys[i]);

            long endTime = getTime();

            return getTimeDifference(startTime, endTime);

        }

        public static int calculateSearchCount(BST bst, int[] searchKeys, int[] searchFrequencies) {


            int count = 0;

            for (int i = 0; i < searchKeys.length; i++)
                for (int j = 0; j < searchFrequencies[i]; j++)
                    count += bst.searchWithCount(searchKeys[i]);

            return count;

        }

        // return the time in nanoseconds
        public static long getTime() {
            return System.nanoTime();
        }

        // It takes the difference of the nanosecond type time values and converts the result to ms.
        public static double getTimeDifference(long startTime, long finishTime) {
            return (finishTime - startTime) / 1000000.0;
        }

    }


    public static void main(String[] args) {


        final int N = 20;

        final int[] KEYS = new int[N];
        final int[] FREQUENCIES = new int[N];

        fillKeyArray(KEYS);
        fillFrequencyArray(FREQUENCIES);

        OptimalBST optimalBST = optimalSearchTree(KEYS, FREQUENCIES);

        OptimalBST.print(optimalBST);

        System.out.println("\n\n      **********    Test    **********\n\n");

        OptimalIntegerBinarySearchTreeTest.test();

    }

    private static void fillKeyArray(int[] keys) {

        Random random = new Random();

        for (int i = 0; i < keys.length; i++)
            keys[i] = i;

        for (int i = 0; i < keys.length; i++) {
            int randomIndexToSwap = random.nextInt(keys.length);
            swap(keys, randomIndexToSwap, i);
        }

    }


    private static void fillFrequencyArray(int[] frequencies) {

        Random random = new Random();

        for (int i = 0; i < frequencies.length; i++)
            frequencies[i] = random.nextInt(100) + 1;

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static OptimalBST optimalSearchTree(int[] keys, int[] frequencies) {

        int n = keys.length;

        int[][] costs = new int[n][n];
        int[][] roots = new int[n][n];

        for (int i = 0; i < n; i++) {
            costs[i][i] = frequencies[i];
            roots[i][i] = i;
        }

        for (int l = 2; l <= n; l++) {

            for (int i = 0; i <= n - l; i++) {

                int j = i + l - 1;

                costs[i][j] = Integer.MAX_VALUE;

                int sum = sum(frequencies, i, j);

                for (int r = i; r <= j; r++) {

                    int c = ((r > i) ? costs[i][r - 1] : 0) +
                            ((r < j) ? costs[r + 1][j] : 0) +
                            sum;

                    if (c < costs[i][j]) {
                        costs[i][j] = c;
                        roots[i][j] = r;
                    }

                }


            }

        }


        int minCost = costs[0][n - 1];

        BST bst = new BST();

        constructOptimalSearchTree(roots, 0, keys.length - 1, bst, keys);

        return new OptimalBST(costs, roots, minCost, bst);

    }


    // print optimal binary search tree method

    private static int sum(int[] frequencies, int i, int j) {

        int sum = 0;

        for (int k = i; k <= j; k++)
            sum += frequencies[k];

        return sum;

    }

    public static void constructOptimalSearchTree(int[][] rootTable, int i, int j, BST bst, int[] keys) {

        if (i > j)
            return;

        if (i == j)
            //System.out.println("d" + (i + 1) + " is the root");
            bst.insert(keys[i]);

        int r = rootTable[i][j];

        bst.insert(keys[r]);

        //System.out.println("k" + (r + 1) + " is the root");

        constructOptimalSearchTree(rootTable, i, r - 1, bst, keys);

        constructOptimalSearchTree(rootTable, r + 1, j, bst, keys);

    }

    private static void printArray(int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }

    private static void printArray(int[][] array) {

        for (int[] row : array) {

            for (int number : row)
                System.out.print(number + " ");

            System.out.println();

        }

    }

}