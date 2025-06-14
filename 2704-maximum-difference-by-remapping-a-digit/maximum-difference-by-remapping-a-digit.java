class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int maxVal = num;
        int minVal = num;

        // Try replacing each digit with '9' for max
        for (char d = '0'; d <= '9'; d++) {
            if (s.indexOf(d) == -1 || d == '9') continue;
            String maxCandidateStr = s.replace(d, '9');
            int maxCandidate = Integer.parseInt(maxCandidateStr);
            maxVal = Math.max(maxVal, maxCandidate);
        }

        // Try replacing each digit with '0' for min
        for (char d = '0'; d <= '9'; d++) {
            if (s.indexOf(d) == -1 || d == '0') continue;
            String minCandidateStr = s.replace(d, '0');
            int minCandidate = Integer.parseInt(minCandidateStr);
            minVal = Math.min(minVal, minCandidate);
        }

        return maxVal - minVal;

    }
}