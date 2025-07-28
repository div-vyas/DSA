class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for (int num : nums) {
            maxOr |= num;
        }

        int n = nums.length;
        int count = 0;

        // Iterate through all non-empty subsets using bitmask
        for (int i = 1; i < (1 << n); i++) {
            int currentOr = 0;
            for (int j = 0; j < n; j++) {
                // Check if the j-th element is in the subset
                if (((i >> j) & 1) == 1) {
                    currentOr |= nums[j];
                }
            }
            
            if (currentOr == maxOr) {
                count++;
            }
        }
        
        return count;
    }
}