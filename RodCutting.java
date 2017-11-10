public class RodCutting {
    /*
     * @param : the prices
     * @param : the length of rod
     * @return: the max value
     */
     int mx = 0;
     
     int[][] memo 
    public int cutting(int[] prices, int n) {
        // Write your code here
        memo = new int[prices.length][n];
        if(n == 0)
            return 0;
        
        cuttingH(prices,n,0);
        
        return mx;
    }
    
    //recrsion solution
    public void cuttingH(int[] prices, int n,int val) {
        // Write your code here
        if(n < 0)
            return;
        if(n == 0){
            mx = Math.max(mx,val);
        }

        for(int i=0; i<prices.length; i++){
            cuttingH(prices,n-i-1,prices[i] + val);  
        }
        
    }
    
    //memoization solutin
    
    
   
}
