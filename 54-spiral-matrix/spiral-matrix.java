class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int topRow=0, bottomRow=matrix.length-1, leftCol=0, rightCol=matrix[0].length-1;
        int totalElements =0;
        while(totalElements < matrix.length * matrix[0].length){
             for (int i = leftCol; i <= rightCol; i++) {
                result.add(matrix[topRow][i]);
                totalElements++;
            }
            topRow++;  // Move the top row down
            
            // Traverse from top to bottom along the right column
            for (int i = topRow; i <= bottomRow; i++) {
                result.add(matrix[i][rightCol]);
                totalElements++;

            }
            rightCol--;  // Move the right column left
            
            // Check if there's still a row to process (bottom row)
            if (topRow <= bottomRow) {
                for (int i = rightCol; i >= leftCol; i--) {
                    result.add(matrix[bottomRow][i]);
                    totalElements++;
                }
                bottomRow--;  // Move the bottom row up
            }
            
            // Check if there's still a column to process (left column)
            if (leftCol <= rightCol) {
                for (int i = bottomRow; i >= topRow; i--) {
                    result.add(matrix[i][leftCol]);
                    totalElements++;
                }
                leftCol++;  // Move the left column right
            }
        }
        return result;
    }

}