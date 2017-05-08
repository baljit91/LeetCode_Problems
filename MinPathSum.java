public class MinPathSum {
    static int[][] K;
    
    public int minPathSum(int[][] grid) {
        K = new int[grid.length + 1][grid[0].length + 1];
        return minPathSumHelper(grid,0,0);   
    }
    
    
    public int minPathSumHelper(int[][] grid, int row, int col) {
        //going outside the boundary
        if(row == grid.length || col == grid[0].length){
            if(K[row][col] == 0){
               K[row][col] = Integer.MAX_VALUE; 
            }
            return K[row][col];
        }

        //reaching the final destination
        if(row == grid.length - 1 && col == grid[0].length - 1)
            return grid[row][col];
        
        //exploring the right cell
        if(K[row + 1][col] == 0){
            K[row + 1][col] = minPathSumHelper(grid, row + 1, col);
        }
        
        //exploring the down cell
        if(K[row][col + 1] == 0){
            K[row][col + 1] = minPathSumHelper(grid, row,col + 1);
        }
        
        //returning the the min path
        return  grid[row][col] + Math.min(K[row + 1][col],K[row][col + 1]);
    }
}
