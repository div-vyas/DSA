class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        List<Integer> A1 = new ArrayList<>(), A2 = new ArrayList<>();
        List<Integer> B1 = new ArrayList<>(), B2 = new ArrayList<>();
        split(nums1, A1, A2);
        split(nums2, B1, B2);

        long negCount = (long)A1.size() * B2.size() + (long)A2.size() * B1.size();
        int sign = 1;
        if (k <= negCount) {
            k = negCount - k + 1;
            sign = -1;
            List<Integer> tmp = B1; B1 = B2; B2 = tmp;
        } else {
            k -= negCount;
        }

        long l = 0, r = (long)1e10;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (countPairs(A1, B1, mid) + countPairs(A2, B2, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return sign * l;
    }

    private void split(int[] arr, List<Integer> neg, List<Integer> pos) {
        for (int x : arr) {
            if (x < 0) neg.add(-x);
            else pos.add(x);
        }
        Collections.reverse(neg);
    }

    private long countPairs(List<Integer> A, List<Integer> B, long mid) {
        long cnt = 0;
        int j = B.size() - 1;
        for (int a : A) {
            while (j >= 0 && (long)a * B.get(j) > mid) j--;
            cnt += (j + 1);
        }
        return cnt;
    }
}
