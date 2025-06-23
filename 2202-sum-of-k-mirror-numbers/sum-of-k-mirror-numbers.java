class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int length = 1;

        while (count < n) {
            // Generate palindromic numbers of a given length
            for (long pal : generatePalindromes(length)) {
                if (isKPalindrome(pal, k)) {
                    sum += pal;
                    count++;
                    if (count == n) return sum;
                }
            }
            length++;
        }
        return sum;
    }

    // Generate palindromic numbers in base 10
    private List<Long> generatePalindromes(int length) {
        List<Long> res = new ArrayList<>();

        // Half-length of palindrome
        int half = (length + 1) / 2;
        long start = (long)Math.pow(10, half - 1);
        long end = (long)Math.pow(10, half);

        for (long i = start; i < end; i++) {
            String firstHalf = Long.toString(i);
            String full = firstHalf + new StringBuilder(firstHalf.substring(0, length % 2 == 0 ? firstHalf.length() : firstHalf.length() - 1)).reverse().toString();
            res.add(Long.parseLong(full));
        }
        return res;
    }

    // Check if number is palindrome in base k
    private boolean isKPalindrome(long num, int k) {
        String baseK = toBaseK(num, k);
        return isPalindrome(baseK);
    }

    // Convert number to base k string
    private String toBaseK(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % base);
            num /= base;
        }
        return sb.reverse().toString();
    }

    // Check if a string is a palindrome
    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
