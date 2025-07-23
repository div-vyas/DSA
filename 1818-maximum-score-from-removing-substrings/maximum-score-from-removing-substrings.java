import java.util.Stack; // Using Stack for clarity as it's a common pattern

class Solution {
    public int maximumGain(String s, int x, int y) {
        int totalScore = 0;
        String s1, s2; // s1 for the higher-priority pair, s2 for the lower
        int points1, points2;

        if (x >= y) {
            // Prioritize "ab"
            s1 = "ab";
            points1 = x;
            s2 = "ba";
            points2 = y;
        } else {
            // Prioritize "ba"
            s1 = "ba";
            points1 = y;
            s2 = "ab";
            points2 = x;
        }

        // First pass: remove higher-priority pair
        StringBuilder sb1 = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == s1.charAt(1) && sb1.length() > 0 && sb1.charAt(sb1.length() - 1) == s1.charAt(0)) {
                sb1.setLength(sb1.length() - 1); // Pop the first char
                totalScore += points1;
            } else {
                sb1.append(c);
            }
        }

        // Second pass: remove lower-priority pair from the remaining string
        StringBuilder sb2 = new StringBuilder();
        String remainingString = sb1.toString();
        for (char c : remainingString.toCharArray()) {
            if (c == s2.charAt(1) && sb2.length() > 0 && sb2.charAt(sb2.length() - 1) == s2.charAt(0)) {
                sb2.setLength(sb2.length() - 1); // Pop the first char
                totalScore += points2;
            } else {
                sb2.append(c);
            }
        }

        return totalScore;
    }
}