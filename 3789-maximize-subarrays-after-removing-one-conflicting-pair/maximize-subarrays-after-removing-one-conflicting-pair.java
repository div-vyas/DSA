import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        int numPairs = conflictingPairs.length;
        Map<Integer, List<int[]>> pairsByB = new HashMap<>();

        for (int i = 0; i < numPairs; i++) {
            int a = conflictingPairs[i][0];
            int b = conflictingPairs[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (!pairsByB.containsKey(b)) {
                pairsByB.put(b, new ArrayList<>());
            }
            pairsByB.get(b).add(new int[]{a, i});
        }

        long[] M1 = new long[n + 1];
        long[] M2 = new long[n + 1];
        long[] M2PrefixSum = new long[n + 1];
        
        int[] M1PairIdx = new int[n + 1];
        Arrays.fill(M1PairIdx, -1);
        
        long currentM1a = 0;
        long currentM2a = 0;
        int currentM1PairIdx = -1;
        
        for (int j = 1; j <= n; j++) {
            if (pairsByB.containsKey(j)) {
                for (int[] pair : pairsByB.get(j)) {
                    int aVal = pair[0];
                    int pairIdx = pair[1];
                    
                    if (aVal > currentM1a) {
                        currentM2a = currentM1a;
                        currentM1a = aVal;
                        currentM1PairIdx = pairIdx;
                    } else if (aVal == currentM1a) {
                        currentM1PairIdx = -1;
                    } else if (aVal > currentM2a) {
                        currentM2a = aVal;
                    }
                }
            }
            
            M1[j] = currentM1a;
            M2[j] = currentM2a;
            M1PairIdx[j] = currentM1PairIdx;
            M2PrefixSum[j] = M2PrefixSum[j - 1] + M2[j];
        }

        long[] reduction = new long[numPairs];
        
        long currentM1ValInSeg = M1[1];
        int currentM1PairIdxInSeg = M1PairIdx[1];
        int segmentStartJ = 1;

        for (int j = 2; j <= n; j++) {
            long M1ValAtJ = M1[j];
            int M1PairIdxAtJ = M1PairIdx[j];
            
            if (M1ValAtJ != currentM1ValInSeg || M1PairIdxAtJ != currentM1PairIdxInSeg) {
                int segmentEndJ = j - 1;
                if (currentM1PairIdxInSeg != -1) {
                    long sumM2InSegment = M2PrefixSum[segmentEndJ] - M2PrefixSum[segmentStartJ - 1];
                    long reductionInSegment = (long) (segmentEndJ - segmentStartJ + 1) * currentM1ValInSeg - sumM2InSegment;
                    reduction[currentM1PairIdxInSeg] += reductionInSegment;
                }
                
                currentM1ValInSeg = M1ValAtJ;
                currentM1PairIdxInSeg = M1PairIdxAtJ;
                segmentStartJ = j;
            }
        }

        int segmentEndJ = n;
        if (currentM1PairIdxInSeg != -1) {
            long sumM2InSegment = M2PrefixSum[segmentEndJ] - M2PrefixSum[segmentStartJ - 1];
            long reductionInSegment = (long) (segmentEndJ - segmentStartJ + 1) * currentM1ValInSeg - sumM2InSegment;
            reduction[currentM1PairIdxInSeg] += reductionInSegment;
        }
            
        long totalSubarrays = (long) n * (n + 1) / 2;
        long totalInvalidAllPairs = 0;
        for (long val : M1) {
            totalInvalidAllPairs += val;
        }
        long totalValidAllPairs = totalSubarrays - totalInvalidAllPairs;
        
        long maxReduction = 0;
        for (long val : reduction) {
            if (val > maxReduction) {
                maxReduction = val;
            }
        }
        
        return totalValidAllPairs + maxReduction;
    }
}