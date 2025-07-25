class Solution {
    public int maxSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int last = nums[n-1];
        int sum = last;
        for(int i=n-2; i>=0; i--){ 
            if(nums[i] <= 0){
                return sum;
            }
            else if(nums[i] != last){
                sum += nums[i];
            }
            last = nums[i];
        }
        return sum;
    }
}