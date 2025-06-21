import java.util.*;

class Solution {
    public int minimumDeletions(String word, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // Step 1: Count frequencies of each character
        for (char c : word.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> frequencies = new ArrayList<>(freqMap.values());
        int maxFreq = Collections.max(frequencies);
        int minDeletions = Integer.MAX_VALUE;

        // Step 2: Try all possible target frequencies from 1 to max frequency
        for (int target = 1; target <= maxFreq; target++) {
            int deletions = 0;

            for (int freq : frequencies) {
                if (freq < target) {
                    deletions += freq; // delete all characters with freq < target
                } else if (freq > target + k) {
                    deletions += freq - (target + k); // delete excess characters
                }
            }

            minDeletions = Math.min(minDeletions, deletions);
        }

        return minDeletions;
    }
}
