class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // sort by startDay

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // stores endDays
        int i = 0, n = events.length, res = 0;

        int day = 1;
        while (i < n || !minHeap.isEmpty()) {
            // Add events starting today
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]); // push endDay
                i++;
            }

            // Remove events that ended before today
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Attend an event today
            if (!minHeap.isEmpty()) {
                minHeap.poll(); // attend one with earliest endDay
                res++;
            }

            day++;
        }

        return res;
    }
}
