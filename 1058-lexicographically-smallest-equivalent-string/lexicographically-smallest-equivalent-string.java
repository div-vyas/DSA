class Solution {
    int[] parent = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Step 1: Initialize parent array
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Step 2: Union the pairs from s1 and s2
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // Step 3: Build result using smallest parent for each character
        StringBuilder sb = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            sb.append((char) (find(ch - 'a') + 'a'));
        }

        return sb.toString();
    }

    // Find with path compression
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union with lexicographical order preference
    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;

        // Always attach the larger to the smaller to keep smallest at top
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }
}
