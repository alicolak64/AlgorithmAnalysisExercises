We are given a COMPLETE BINARY TREE where nodes and edges have positive weights. Node weights are stored in a 1-dimensional array WN. Edge weights are stored in a 2-dimensional array WE where 0 denotes no edge. Starting at the root of the tree and moving to either one of the children from the current node, the goal is to find the minimum total weight (i.e. sum of node and edge weights path from the root to any one of the leaves.

![Example](/CompleteBinaryTreeMinPath/Example.png)

(a) Implement a function that generates complete binary tree (i.e. two arrays) with given size as input where the node and edge weights are set randomly between 1 and 20 inclusive.
(b) Implement the greedy strategy (i.e. write a function) of choosing the child with the smallest sum of edge and node weights each time.
(c) Implement a recursive algorithm (i.e. write a function) to find the minimum total weight. You must determine the input parameters. Also, give the time complexity of a recursive algorithm that implements this formulation? Show your work.
(d) Implement a dynamic programming algorithm to solve the problem. You must determine the input parameters. Also, give the time complexity of your dynamic programming solution? Show your work.
(e) In your main function:
• Show that the greedy algorithm does not solve this problem optimally.
• Run each of the recursive and dynamic functions with three different input sizes and compute the actual running times (in milliseconds or seconds of these three algorithms. You will need to calculate the time passed before and after making a call to each function. Provide a 2x3 table involving the actual running times.

![Question](/CompleteBinaryTreeMinPath/Question.png)

