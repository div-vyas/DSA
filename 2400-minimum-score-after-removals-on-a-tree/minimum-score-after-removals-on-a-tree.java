class Solution {
    List<Integer>[] adj;
    int[] nums;
    int[] subtreeXOR;
    int[] tin;
    int[] tout;
    int timer;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        subtreeXOR = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        dfs(0, -1); // Root the tree at node 0, -1 as parent of root

        int totalXOR = subtreeXOR[0]; // XOR sum of the entire tree

        int minScore = Integer.MAX_VALUE;

        // Iterate through all distinct pairs of nodes (potential roots of cut subtrees)
        // Since cutting an edge (u,v) where v is child of u, means v's subtree is a component.
        // We consider all nodes from 1 to n-1 as potential 'child' nodes that form a cut subtree.
        for (int i = 1; i < n; i++) { // First cut 'child' node
            for (int j = i + 1; j < n; j++) { // Second cut 'child' node (distinct from i)
                int xor1, xor2, xor3;

                // Case: i is ancestor of j
                if (isAncestor(i, j)) {
                    xor1 = subtreeXOR[j];             // Innermost subtree
                    xor2 = subtreeXOR[i] ^ subtreeXOR[j]; // Part of i's subtree excluding j's subtree
                    xor3 = totalXOR ^ subtreeXOR[i];  // Rest of the tree
                } 
                // Case: j is ancestor of i
                else if (isAncestor(j, i)) {
                    xor1 = subtreeXOR[i];             // Innermost subtree
                    xor2 = subtreeXOR[j] ^ subtreeXOR[i]; // Part of j's subtree excluding i's subtree
                    xor3 = totalXOR ^ subtreeXOR[j];  // Rest of the tree
                } 
                // Case: i and j are not ancestor-descendant
                else {
                    xor1 = subtreeXOR[i];
                    xor2 = subtreeXOR[j];
                    xor3 = totalXOR ^ subtreeXOR[i] ^ subtreeXOR[j];
                }

                int currentMax = Math.max(xor1, Math.max(xor2, xor3));
                int currentMin = Math.min(xor1, Math.min(xor2, xor3));
                minScore = Math.min(minScore, currentMax - currentMin);
            }
        }

        return minScore;
    }

    private void dfs(int u, int p) {
        tin[u] = timer++;
        subtreeXOR[u] = nums[u];

        for (int v : adj[u]) {
            if (v == p) continue;
            dfs(v, u);
            subtreeXOR[u] ^= subtreeXOR[v];
        }
        tout[u] = timer++;
    }

    private boolean isAncestor(int u, int v) {
        // u is an ancestor of v if v is within the DFS entry/exit time range of u
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }
}