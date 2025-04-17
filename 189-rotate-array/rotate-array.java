class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int a = k % n;
        reverseArr(nums, 0, n-a-1);
        reverseArr(nums, n-a, n-1);
        reverseArr(nums, 0, n-1);
        for(int i=0; i<n; i++){
            System.out.print(nums[i]);
        }
    }

    public void reverseArr(int[] nums, int st, int end){
        while(st<end){
            swap(nums, st, end);
            st++;
            end--;
        }
    }

    public void swap(int[] arr, int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
    
}