class Solution {
    public List<Integer> majorityElement(int[] nums) {

        // // Brute - O(N^2)
        // ArrayList<Integer> ls = new ArrayList<>();
        // int n = nums.length;

        // for(int i=0; i<n; i++){
        //     if(ls.size() == 0 || ls.get(0) != nums[i]){
        //         int cnt=0;
        //         for(int j=0; j<n; j++){
        //             if(nums[j] == nums[i]){
        //                 cnt++;
        //             }
        //         }
                
        //         if(cnt > (n/3)){
        //             ls.add(nums[i]);
        //         }

        //     }
        //     if(ls.size() == 2){ 
        //     break;
        // }
        // }
        // return ls;


        // // Better - O(N)
        // ArrayList<Integer> ls = new ArrayList<>();
        // HashMap<Integer, Integer> map = new HashMap<>();
        // int n = nums.length;

        // int min = (int)(n/3) + 1;

        // for(int i =0; i<n; i++){
        //     int value = map.getOrDefault(nums[i], 0);
        //     map.put(nums[i], value+1);

        //     if(map.get(nums[i]) == min){
        //         ls.add(nums[i]);
        //     }
        // }
        // return ls;

        // Optimal - O(1) 

        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;
        int cnt1 = 0;
        int cnt2 = 0;
        int n = nums.length;

        for(int i=0; i<n; i++){
            if(cnt1 == 0 && ele2 != nums[i]){
                cnt1 = 1;
                ele1 = nums[i];
            }
            else if(cnt2 == 0 && ele1 != nums[i]){
                cnt2 = 1;
                ele2 = nums[i];
            }
            else if(nums[i] == ele1){
                cnt1++;
            }
            else if(nums[i] == ele2){
                cnt2++;
            }
            else{
                cnt1--;
                cnt2--;
            }
        }

        ArrayList<Integer> ls = new ArrayList<>();
        int min = (int)(n/3) + 1;
        cnt1 = 0;
        cnt2 = 0;
        for(int i=0; i<n; i++){
            if(nums[i] == ele1){
                cnt1++;
            }
            if(nums[i] == ele2){
                cnt2++;
            }
        }
        if(cnt1 >= min)
            ls.add(ele1);
        if(cnt2 >= min)
            ls.add(ele2);
    
        return ls;
    }
}