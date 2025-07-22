import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int currentSum = 0;
        int maxScore = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // While the current element is already in our set,
            // remove elements from the left side of the window
            // until the current element is unique.
            while (seen.contains(nums[right])) {
                currentSum -= nums[left];
                seen.remove(nums[left]);
                left++;
            }

            // Add the current element to the window
            seen.add(nums[right]);
            currentSum += nums[right];

            // Update the maximum score found so far
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }
}