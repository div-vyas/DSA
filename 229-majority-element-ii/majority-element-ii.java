class Solution {
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> ls = new ArrayList<>();
        int n = nums.length;

        for(int i=0; i<n; i++){
            if(ls.size() == 0 || ls.get(0) != nums[i]){
                int cnt=0;
                for(int j=0; j<n; j++){
                    if(nums[j] == nums[i]){
                        cnt++;
                    }
                }
                
                if(cnt > (n/3)){
                    ls.add(nums[i]);
                }

            }
            if(ls.size() == 2){ 
            break;
        }
        }

        

        return ls;
    }
}