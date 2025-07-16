class Solution {
    public int maximumLength(int[] nums) {
        int evenCount = 0, oddCount = 0;

        // Count number of even and odd numbers
        for (int num : nums) {
            if (num % 2 == 0) evenCount++;
            else oddCount++;
        }

        // Strategy 1: same parity max
        int sameParityMax = Math.max(evenCount, oddCount);

        // Strategy 2: alternating parity
        int maxAlt = 1;
        int lastParity = nums[0] % 2;
        int altLen = 1;

        for (int i = 1; i < nums.length; i++) {
            int currentParity = nums[i] % 2;
            if (currentParity != lastParity) {
                altLen++;
                lastParity = currentParity;
            }
        }
        maxAlt = altLen;

        return Math.max(sameParityMax, maxAlt);
    }
}
