class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int cnt1 = 0;
        int el1 = Integer.MIN_VALUE;
        int cnt2 = 0;
        int el2 = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i=0; i<n; i++){
            if(cnt1 == 0 && nums[i] != el2){
                cnt1 = 1;
                el1 = nums[i];
            }
            else if(cnt2 == 0 && nums[i] != el1){
                cnt2 = 1;
                el2 = nums[i];
            }
            else if(nums[i] == el1){
                cnt1++;
            }
            else if(nums[i] == el2){
                cnt2++;
            }
            else{
                cnt1--;
                cnt2--;
            }
        }

        ArrayList<Integer> ls = new ArrayList<>();
        int min = (int)(n/3) + 1;
        cnt1 = 0; cnt2 = 0;
        for(int i=0; i<n; i++){
            if(nums[i] == el1){
                cnt1++;
            }
            if(nums[i] == el2){
                cnt2++;
            }
        }
        if(cnt1 >= min){
            ls.add(el1);
        }
        if(cnt2 >= min){
            ls.add(el2);
        }

        return ls;
    }
}