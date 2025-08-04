import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds the maximum number of fruits that can be picked with two baskets.
     * This is equivalent to finding the length of the longest subarray with at most two distinct elements.
     *
     * @param fruits An array representing the types of fruit on the trees.
     * @return The maximum number of fruits that can be picked.
     */
    public int totalFruit(int[] fruits) {
        // Handle edge case of no fruit trees.
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        int n = fruits.length;
        int maxPicked = 0;
        int left = 0;
        
        // 'basket' will store the counts of fruit types in the current window [left, right].
        Map<Integer, Integer> basket = new HashMap<>();

        // Use 'right' pointer to expand the window.
        for (int right = 0; right < n; right++) {
            int currentFruit = fruits[right];
            basket.put(currentFruit, basket.getOrDefault(currentFruit, 0) + 1);

            // If the basket has more than 2 types of fruit, shrink the window from the left.
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                
                // If a fruit count drops to 0, remove it from the basket.
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                left++;
            }

            // The window [left, right] is now valid.
            // Update the maximum number of fruits picked so far.
            maxPicked = Math.max(maxPicked, right - left + 1);
        }

        return maxPicked;
    }
}