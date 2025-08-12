import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Extract the exponents of the powers of 2 that form n.
        // This corresponds to the indices of the set bits in n's binary representation.
        List<Integer> exponents = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 1) == 1) {
                exponents.add(i);
            }
        }

        // Step 2: Create a prefix sum array for the exponents for efficient range sum queries.
        int m = exponents.size();
        long[] prefixSumOfExponents = new long[m + 1];
        for (int i = 0; i < m; i++) {
            prefixSumOfExponents[i + 1] = prefixSumOfExponents[i] + exponents.get(i);
        }

        // Step 3: Process each query.
        int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            // The product of powers of 2 (2^e1 * 2^e2 * ...) is 2^(e1 + e2 + ...).
            // We find the sum of exponents in the range [left, right] using our prefix sum array.
            long totalExponent = prefixSumOfExponents[right + 1] - prefixSumOfExponents[left];
            
            // The answer is 2^totalExponent mod MOD.
            // We calculate this using an efficient modular exponentiation algorithm.
            answers[i] = (int) power(2, totalExponent);
        }

        return answers;
    }

    /**
     * Calculates (base^exp) % MOD using the binary exponentiation (exponentiation by squaring) method.
     * This is an efficient way to compute large powers under a modulus.
     */
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}