class Solution {
    public int findLucky(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        
        // Count frequency of each number
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        int maxLucky = -1;
        
        // Find the largest lucky number
        for (int num : freq.keySet()) {
            if (num == freq.get(num)) {
                maxLucky = Math.max(maxLucky, num);
            }
        }
        
        return maxLucky;
    }
}
