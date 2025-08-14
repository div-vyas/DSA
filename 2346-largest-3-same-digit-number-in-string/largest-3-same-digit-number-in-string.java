class Solution {
    /**
     * Finds the largest "good integer" substring in a given string.
     * A "good integer" is a substring of length 3 with three identical digits.
     *
     * @param num The input string representing a large integer.
     * @return The largest good integer as a string, or an empty string if none exists.
     */
    public String largestGoodInteger(String num) {
        // We'll store the largest good integer found so far.
        // Start with a value smaller than any possible good integer like "000".
        String maxGoodInteger = "";

        // Iterate through the string with a window of size 3.
        // The loop must stop at the third-to-last character to avoid going out of bounds.
        for (int i = 0; i <= num.length() - 3; i++) {
            // Get the current 3-digit substring.
            String currentSubstring = num.substring(i, i + 3);

            // Check if all three characters in the substring are the same.
            if (currentSubstring.charAt(0) == currentSubstring.charAt(1) &&
                currentSubstring.charAt(1) == currentSubstring.charAt(2)) {

                // If this is the first good integer we've found, or if it's
                // larger than the max we've seen, update the max.
                // String comparison works for numbers of the same length.
                if (maxGoodInteger.isEmpty() || currentSubstring.compareTo(maxGoodInteger) > 0) {
                    maxGoodInteger = currentSubstring;
                }
            }
        }

        return maxGoodInteger;
    }
}