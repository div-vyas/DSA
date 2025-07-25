class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int largestNonPos = Integer.MIN_VALUE;
        int maxSum = 0;

        for(int num : nums){
            if(!seen.contains(num)){
                seen.add(num);

                if(num > 0){
                    maxSum += num;
                }
                else{
                    largestNonPos = Math.max(largestNonPos, num);
                }
            }
        }
        if(maxSum > 0){
            return maxSum;
        }
        else{
            return largestNonPos;
        }
    }
}