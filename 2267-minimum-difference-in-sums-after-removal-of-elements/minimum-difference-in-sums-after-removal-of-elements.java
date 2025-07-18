class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        long[] prefix = new long[nums.length];
        long[] suffix = new long[nums.length];

        long sum = 0;
        // Max-heap for prefix
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 2 * n; i++) {
            sum += nums[i];
            maxHeap.offer(nums[i]);
            if (maxHeap.size() > n) {
                sum -= maxHeap.poll();
            }
            if (maxHeap.size() == n) {
                prefix[i] = sum;
            }
        }

        sum = 0;
        // Min-heap for suffix
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = nums.length - 1; i >= n; i--) {
            sum += nums[i];
            minHeap.offer(nums[i]);
            if (minHeap.size() > n) {
                sum -= minHeap.poll();
            }
            if (minHeap.size() == n) {
                suffix[i] = sum;
            }
        }

        long result = Long.MAX_VALUE;
        // Evaluate possible partitions
        for (int i = n; i <= 2 * n; i++) {
            result = Math.min(result, prefix[i - 1] - suffix[i]);
        }

        return result;
    }
}
