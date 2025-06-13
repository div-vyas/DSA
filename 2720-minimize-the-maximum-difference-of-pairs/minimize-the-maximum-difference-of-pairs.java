class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);

        int low=0, high = nums[nums.length-1] - nums[0];
        int ans = high;

        while(low <= high){
            int mid = low + (high-low)/2;
            if(canFormPairs(nums, p, mid)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return ans;
    }

    private boolean canFormPairs(int[] nums, int p, int maxDiff){
        int count = 0;
        int i = 1;

        while(i < nums.length){
            if(nums[i] - nums[i-1] <= maxDiff){
                count++;
                i+= 2;
            }
            else{
                i++;
            }
            if(count >= p)
                return true;
        }
        return false;
    }
}