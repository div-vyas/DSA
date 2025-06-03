class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        int ans[] = new int[n];
        int k = 0;
        while(left<=right){
            if(Math.abs(nums[left])>Math.abs(nums[right])){
                ans[k++] = nums[left] * nums[left];
                left++;
            }else{
                ans[k++] = nums[right] * nums[right];
                right--;
            }
        }
        RevArray(ans);
        return ans;
    }

    public int[] RevArray(int[] ans){
        int left = 0;
    int right = ans.length - 1;

    while (left < right) {
        // Swap the elements at left and right
        int temp = ans[left];
        ans[left] = ans[right];
        ans[right] = temp;

        // Move pointers
        left++;
        right--;
        
    }
    return ans;
    }
}