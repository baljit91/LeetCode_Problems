public class MinPathSumWoDP {
    public int minPathSum(int[][] grid) {
        return minPathSumHelper(grid,0,0);
    }
    
    public int minPathSumHelper(int[][] grid, int row, int col) {
        if(row == grid.length )
            return 4000;
        
        if(col == grid[0].length)
            return 4000;  
          
        if(row == grid.length - 1 && col == grid[0].length - 1)
            return grid[row][col];
            
        return  grid[row][col] + Math.min(minPathSumHelper(grid, row + 1, col),minPathSumHelper(grid, row,col + 1));
    }
}
