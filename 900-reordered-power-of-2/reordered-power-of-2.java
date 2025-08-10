import java.util.Arrays;

class Solution {
    /**
     * Checks if the digits of n can be reordered to form a power of two.
     */
    public boolean reorderedPowerOf2(int n) {
        // Create a frequency map (signature) for the digits of n.
        int[] nDigitCounts = countDigits(n);

        // Iterate through powers of 2. We only need to check up to 2^29,
        // since 2^30 is greater than the constraint 10^9.
        for (int i = 0; i < 30; i++) {
            // Calculate the current power of two.
            int powerOfTwo = 1 << i;
            
            // If the digit signature of n matches the signature of the current power of two,
            // then n can be reordered into a power of two.
            if (Arrays.equals(nDigitCounts, countDigits(powerOfTwo))) {
                return true;
            }
        }
        
        // If we iterate through all relevant powers of two and find no match.
        return false;
    }

    /**
     * Helper function to compute the digit frequency signature of a number.
     * @param num The number to analyze.
     * @return An array of size 10 where the value at index d is the count of digit d.
     */
    private int[] countDigits(int num) {
        int[] counts = new int[10];
        while (num > 0) {
            counts[num % 10]++;
            num /= 10;
        }
        return counts;
    }
}