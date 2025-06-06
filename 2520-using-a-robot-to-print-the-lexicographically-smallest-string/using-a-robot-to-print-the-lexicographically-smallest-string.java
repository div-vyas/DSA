class Solution {
        public String robotWithString(String s) {
        int[] count = new int[26];  // Frequency count of character
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int minCharIndex = 0;

        for (char ch : s.toCharArray()) {
            stack.push(ch);
            count[ch - 'a']--;

            // Update minCharIndex to reflect the smallest character still in s
            while (minCharIndex < 26 && count[minCharIndex] == 0) {
                minCharIndex++;
            }

            // Pop from t (stack) to result while top is <= smallest remaining in s
            while (!stack.isEmpty() && (stack.peek() - 'a') <= minCharIndex) {
                result.append(stack.pop());
            }
        }

        // Add remaining characters in the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    
    }
}