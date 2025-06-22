class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int groupCount = (n + k - 1) / k; // Total number of groups needed
        String[] result = new String[groupCount];

        for (int i = 0; i < groupCount; i++) {
            int start = i * k;
            int end = Math.min(start + k, n);
            String group = s.substring(start, end);
            
            // If the group is shorter than k, append the fill character
            if (group.length() < k) {
                StringBuilder sb = new StringBuilder(group);
                while (sb.length() < k) {
                    sb.append(fill);
                }
                group = sb.toString();
            }

            result[i] = group;
        }

        return result;
    }
}
