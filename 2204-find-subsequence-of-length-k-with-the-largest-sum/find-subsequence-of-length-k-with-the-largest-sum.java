class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;

        // Pair class to store value and original index
        class Pair {
            int value, index;
            Pair(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        // Create array of pairs
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        // Sort by value descending (largest values first)
        Arrays.sort(pairs, (a, b) -> b.value - a.value);

        // Take the top k elements
        List<Pair> topK = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topK.add(pairs[i]);
        }

        // Sort selected k elements by index to maintain original order
        Collections.sort(topK, (a, b) -> a.index - b.index);

        // Build result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topK.get(i).value;
        }

        return result;
    }
}
