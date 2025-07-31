import java.util.HashSet;
import java.util.Set;

class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> allResults = new HashSet<>();
        Set<Integer> currentOrs = new HashSet<>();
        
        for (int x : arr) {
            Set<Integer> nextOrs = new HashSet<>();
            nextOrs.add(x);
            for (int y : currentOrs) {
                nextOrs.add(y | x);
            }
            currentOrs = nextOrs;
            allResults.addAll(currentOrs);
        }
        
        return allResults.size();
    }
}