class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        Queue<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";

        // Step 1: Count characters that can appear (frequency >= k)
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        List<Character> valid = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            if (freq[i] >= k) valid.add((char) ('a' + i));

        // Step 2: BFS over candidate subsequences
        while (!q.isEmpty()) {
            String curr = q.poll();
            if (curr.length() * k > n) break;  // no longer candidates possible
            for (char c : valid) {
                String next = curr + c;
                if (isKRepeatedSubseq(next, s, k)) {
                    q.offer(next);
                    ans = next;
                }
            }
        }
        return ans;
    }

    // Step 3: Check if t*k is a subsequence of s
    private boolean isKRepeatedSubseq(String t, String s, int k) {
        int i = 0, tkLen = t.length(), times = 0;
        for (char c : s.toCharArray()) {
            if (c == t.charAt(i)) {
                i++;
                if (i == tkLen) {
                    i = 0;
                    times++;
                    if (times == k) return true;
                }
            }
        }
        return false;
    }
}
