class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array to make groupings easier
        int n = nums.length;
        
        // Check if input size is valid (though problem guarantees it)
        if (n % 3 != 0) return new int[0][0];
        
        int[][] result = new int[n / 3][3];
        int groupIndex = 0;

        for (int i = 0; i < n; i += 3) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];

            // Check if the group is valid
            if (c - a > k) return new int[0][0]; // Invalid group

            // Add group to result
            result[groupIndex][0] = a;
            result[groupIndex][1] = b;
            result[groupIndex][2] = c;
            groupIndex++;
        }

        return result;
    }
}
