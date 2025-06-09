class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--; // Because we start from 1

        while (k > 0) {
            long steps = countSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    // Count how many numbers are between curr and curr+1 in lex order
    private long countSteps(long curr, long n) {
        long steps = 0;
        long first = curr;
        long last = curr;

        while (first <= n) {
            steps += Math.min(n, last) - first + 1;
            first *= 10;
            last = last * 10 + 9;
        }

        return steps;
    }
}
