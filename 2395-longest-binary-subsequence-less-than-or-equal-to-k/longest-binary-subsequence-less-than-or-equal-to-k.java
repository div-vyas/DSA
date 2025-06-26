class Solution {
    public int longestSubsequence(String s, int k) {
        int count = 0;
        int len = s.length();
        int zeroCount = 0;

        // Count all zeros — they are safe to add
        for (char c : s.toCharArray()) {
            if (c == '0') zeroCount++;
        }

        // Traverse from right to left, try to add 1s
        long value = 0;
        int pow = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (pow < 32) {  // Avoid overflow
                    long temp = value + (1L << pow);
                    if (temp <= k) {
                        value = temp;
                        count++;
                    }
                }
                pow++;
            } else {
                count++;
                pow++;
            }
        }

        return count;
    }
}
