class Solution {
    public int partitionArray(int[] nums, int k) {
       Arrays.sort(nums); 
       int count = 0;
       int i = 0;
       int n = nums.length;

       while(i < n){
        int start = nums[i];
        count++;
        while(i < n && nums[i] <= start + k){
            i++;
        }
       } 
       return count;
    }
}