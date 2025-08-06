class Solution {
    // Segment tree array. Stores the maximum capacity in a given range of baskets.
    private int[] tree;
    // The number of baskets.
    private int n;

    /**
     * Builds the segment tree from the baskets array recursively.
     * @param baskets The array of basket capacities.
     * @param node The index of the current node in the 'tree' array.
     * @param start The starting index of the segment represented by this node.
     * @param end The ending index of the segment represented by this node.
     */
    private void build(int[] baskets, int node, int start, int end) {
        if (start == end) {
            // Leaf node: store the capacity of the single basket.
            tree[node] = baskets[start];
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;
            // Recursively build the left and right children.
            build(baskets, leftChild, start, mid);
            build(baskets, rightChild, mid + 1, end);
            // The value of the internal node is the maximum of its children.
            tree[node] = Math.max(tree[leftChild], tree[rightChild]);
        }
    }

    /**
     * Updates the capacity of a used basket to 0 to mark it as unavailable.
     * @param node The index of the current node in the 'tree' array.
     * @param start The starting index of the segment.
     * @param end The ending index of the segment.
     * @param idx The index of the basket to update.
     */
    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            // We've reached the leaf node corresponding to the basket.
            // Set its capacity to 0 to signify it's used.
            tree[node] = 0;
            return;
        }
        
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            // If the index is in the left child's range, update there.
            update(leftChild, start, mid, idx);
        } else {
            // Otherwise, update in the right child's range.
            update(rightChild, mid + 1, end, idx);
        }
        
        // After the child is updated, recalculate the max for the current node.
        tree[node] = Math.max(tree[leftChild], tree[rightChild]);
    }

    /**
     * Queries the segment tree to find the leftmost basket with capacity >= q.
     * @param node The index of the current node in the 'tree' array.
     * @param start The starting index of the segment.
     * @param end The ending index of the segment.
     * @param q The required minimum capacity (the fruit quantity).
     * @return The index of the found basket, or -1 if no suitable basket is found.
     */
    private int findLeftmostBasket(int node, int start, int end, int q) {
        // If the max capacity in this entire range is less than required,
        // no suitable basket exists in this subtree.
        if (tree[node] < q) {
            return -1;
        }
        
        // If we are at a leaf node, we have found a valid basket.
        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // We must check the left child first to find the "leftmost" basket.
        if (tree[leftChild] >= q) {
            // A suitable basket exists in the left subtree. Search there.
            return findLeftmostBasket(leftChild, start, mid, q);
        } else {
            // No basket in the left subtree is large enough. Search the right subtree.
            return findLeftmostBasket(rightChild, mid + 1, end, q);
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        this.n = baskets.length;
        // A segment tree typically requires up to 4n space.
        this.tree = new int[4 * n];
        
        // Build the segment tree using the initial basket capacities.
        // Node 1 is the root, covering the full range [0, n-1].
        build(baskets, 1, 0, n - 1);

        int unplacedCount = 0;
        for (int fruitQuantity : fruits) {
            // Find the index of the leftmost available basket that can hold the fruit.
            int basketIndex = findLeftmostBasket(1, 0, n - 1, fruitQuantity);
            
            if (basketIndex != -1) {
                // If a basket is found, "remove" it by updating its capacity to 0.
                update(1, 0, n - 1, basketIndex);
            } else {
                // If no suitable basket is found, the fruit is unplaced.
                unplacedCount++;
            }
        }
        
        return unplacedCount;
    }
}