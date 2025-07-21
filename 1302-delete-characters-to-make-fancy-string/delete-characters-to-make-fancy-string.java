class Solution {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        char lastChar = '\0';
        int consecutiveCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == lastChar) {
                consecutiveCount++;
            } else {
                lastChar = currentChar;
                consecutiveCount = 1;
            }

            if (consecutiveCount < 3) {
                sb.append(currentChar);
            }
        }

        return sb.toString();
    }
}