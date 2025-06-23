class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;

        // Try increasing palindrome lengths
        for (int len = 1; count < n; len++) {
            // Generate odd and even length palindromes
            for (long pal : generatePalindromes(len)) {
                if (isKPalindrome(pal, k)) {
                    sum += pal;
                    count++;
                    if (count == n) return sum;
                }
            }
        }

        return sum;
    }

    // Generate palindromes of given length
    private List<Long> generatePalindromes(int len) {
        List<Long> result = new ArrayList<>();

        // Half the length
        int halfLen = (len + 1) / 2;
        long start = (long)Math.pow(10, halfLen - 1);
        long end = (long)Math.pow(10, halfLen);

        for (long i = start; i < end; i++) {
            String firstHalf = Long.toString(i);
            String secondHalf = new StringBuilder(
                firstHalf.substring(0, len % 2 == 0 ? firstHalf.length() : firstHalf.length() - 1)
            ).reverse().toString();
            String full = firstHalf + secondHalf;
            result.add(Long.parseLong(full));
        }

        return result;
    }

    // Convert number to base-k and check if it's a palindrome
    private boolean isKPalindrome(long num, int base) {
        String baseK = toBaseK(num, base);
        return isPalindrome(baseK);
    }

    // Convert number to base-k string
    private String toBaseK(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % base);
            num /= base;
        }
        return sb.reverse().toString();
    }

    // Check if string is a palindrome
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
