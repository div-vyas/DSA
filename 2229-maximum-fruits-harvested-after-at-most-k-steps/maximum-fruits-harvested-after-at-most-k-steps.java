class Solution {
    /**
     * Finds the maximum total number of fruits that can be harvested by moving at most k steps.
     *
     * This problem is solved using a sliding window approach. The window represents a contiguous
     * range of fruits we aim to harvest. We seek the window that yields the maximum fruit
     * count while being reachable within 'k' steps.
     *
     * 1.  **Prefix Sums**: We pre-calculate prefix sums of fruit amounts. This allows us to
     * find the total fruits in any window [left, right] in O(1) time.
     *
     * 2.  **Sliding Window**: We use two pointers, 'left' and 'right'. We iterate 'right'
     * to expand the window. If the steps required to cover the window [left, right]
     * exceed 'k', we shrink the window by incrementing 'left'. This works because the
     * number of steps is monotonic with respect to the window's boundaries.
     *
     * 3.  **Step Calculation**: The minimum steps to visit all fruits in a position range [l_pos, r_pos]
     * from startPos is calculated based on whether the range is to one side of startPos or spans it.
     * If it spans startPos, we must make a round trip to the closer endpoint and a one-way trip to the farther one.
     *
     * The overall time complexity is O(N) as each pointer traverses the array once.
     * The space complexity is O(N) for the prefix sum array.
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;

        // Prefix sums for O(1) window sum calculation.
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + fruits[i][1];
        }

        int maxFruits = 0;
        int left = 0;
        // Iterate through all possible right endpoints of the sliding window.
        for (int right = 0; right < n; right++) {
            int r_pos = fruits[right][0];
            
            // Shrink the window from the left until the step constraint is met.
            while (left <= right) {
                int l_pos = fruits[left][0];
                long steps = calculateSteps(l_pos, r_pos, startPos);
                if (steps <= k) {
                    // This window [left, right] is valid. It's the largest valid
                    // window ending at 'right', so we can stop shrinking for now.
                    break;
                }
                // Window is invalid (takes too many steps), so shrink from the left.
                left++;
            }
            
            // If the window is valid, calculate its fruit sum and update the maximum.
            if (left <= right) {
                long currentFruits = prefixSum[right + 1] - prefixSum[left];
                maxFruits = Math.max(maxFruits, (int)currentFruits);
            }
        }
        
        return maxFruits;
    }

    /**
     * Calculates the minimum steps to visit all fruit positions from l_pos to r_pos,
     * starting at startPos.
     */
    private long calculateSteps(int l_pos, int r_pos, int startPos) {
        // Case 1: The entire trip is to the right of (or at) startPos.
        if (l_pos >= startPos) {
            return r_pos - startPos;
        }
        
        // Case 2: The entire trip is to the left of (or at) startPos.
        if (r_pos <= startPos) {
            return startPos - l_pos;
        }

        // Case 3: The trip spans across startPos.
        long dist_l = (long)startPos - l_pos;
        long dist_r = (long)r_pos - startPos;
        
        // Two possible paths:
        // a) Go left first, then right: 2 * dist_l + dist_r
        // b) Go right first, then left: dist_l + 2 * dist_r
        return Math.min(2 * dist_l + dist_r, dist_l + 2 * dist_r);
    }
}