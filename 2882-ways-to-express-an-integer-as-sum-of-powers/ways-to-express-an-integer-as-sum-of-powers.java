class Solution {
    /**
     * Calculates the number of ways to express an integer 'n' as a sum of unique x-th powers.
     */
    public int numberOfWays(int n, int x) {
        int MOD = 1_000_000_007;

        // dp[i] will store the number of ways to represent the integer i.
        int[] dp = new int[n + 1];

        // Base case: There is one way to represent 0 (by choosing no powers).
        dp[0] = 1;

        // Iterate through all possible base numbers 'i'.
        // We only need to consider 'i' such that i^x is not greater than n.
        for (int i = 1; ; i++) {
            // Calculate i^x.
            int power = (int) Math.pow(i, x);
            
            // If the current power exceeds n, any subsequent powers will also exceed n,
            // so we can stop the process.
            if (power > n) {
                break;
            }
            
            // To ensure each power (from a unique base i) is used at most once,
            // we iterate backwards from n. This is a standard 0/1 knapsack DP technique.
            for (int j = n; j >= power; j--) {
                dp[j] = (dp[j] + dp[j - power]) % MOD;
            }
        }
        
        return dp[n];
    }
}