import java.util.*;

public class Solution {
    public int maxDifference(String s) {
        // Step 1: Count frequencies of characters
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Separate odd and even frequencies
        List<Integer> oddFreqs = new ArrayList<>();
        List<Integer> evenFreqs = new ArrayList<>();

        for (int count : freqMap.values()) {
            if (count % 2 == 1) {
                oddFreqs.add(count); // Odd frequency
            } else {
                evenFreqs.add(count); // Even frequency
            }
        }

        // Step 3: Find the maximum difference between odd and even frequencies
        if (oddFreqs.isEmpty() || evenFreqs.isEmpty()) {
            return 0; // Just a safeguard if we don't find both odd and even frequencies
        }

        // Get the maximum odd frequency and the minimum even frequency
        int maxOdd = Collections.max(oddFreqs);
        int minEven = Collections.min(evenFreqs);

        return maxOdd - minEven;
    }
}
