class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> resultSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                int start = Math.max(0, i - k);
                int end = Math.min(nums.length - 1, i + k);
                for (int j = start; j <= end; j++) {
                    resultSet.add(j);
                }
            }
        }

        List<Integer> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
}
