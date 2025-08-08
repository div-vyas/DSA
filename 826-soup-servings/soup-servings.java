class Solution {
    // memo[i][j] will store the result for solve(i, j)
    private Double[][] memo;

    public double soupServings(int n) {
        // For large n, the probability that soup A finishes first approaches 1.0.
        // A cutoff of n=4800 is empirically found to be sufficient to pass
        // the required precision of 10^-5.
        if (n >= 4800) {
            return 1.0;
        }
        
        // Scale the problem down by a factor of 25 to reduce the state space.
        // N represents the number of 25mL units.
        // (n + 24) / 25 is a way to calculate ceil(n / 25.0) using integer arithmetic.
        int N = (n + 24) / 25;
        memo = new Double[N + 1][N + 1];
        
        return solve(N, N);
    }

    /**
     * Recursive function with memoization to calculate the desired probability.
     * @param a Units of soup A remaining.
     * @param b Units of soup B remaining.
     * @return P(A empty first) + 0.5 * P(A and B empty together)
     */
    private double solve(int a, int b) {
        // Base case: Both soups run out at the same time.
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        // Base case: Soup A runs out first.
        if (a <= 0) {
            return 1.0;
        }
        // Base case: Soup B runs out first.
        if (b <= 0) {
            return 0.0;
        }

        // Return the memoized result if this state has already been computed.
        if (memo[a][b] != null) {
            return memo[a][b];
        }

        // Recursive step: The probability is the average of the probabilities of the four possible outcomes.
        double probability = 0.25 * (solve(a - 4, b) +      // Operation 1: 100mL A, 0mL B
                                      solve(a - 3, b - 1) +  // Operation 2: 75mL A, 25mL B
                                      solve(a - 2, b - 2) +  // Operation 3: 50mL A, 50mL B
                                      solve(a - 1, b - 3)); // Operation 4: 25mL A, 75mL B
        
        // Store the result in the memoization table before returning.
        memo[a][b] = probability;
        return probability;
    }
}