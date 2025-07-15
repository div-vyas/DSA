class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> ans = new ArrayList<>();
        long result = 1;
        ans.add(1);
        for(int i=1; i<=rowIndex; i++){
            result = result * (rowIndex - i + 1);
            result = result / (i);
            ans.add((int)result);
        }
        return ans;
    }
}