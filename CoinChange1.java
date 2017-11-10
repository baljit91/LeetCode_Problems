public class CoinChange1 {
    int val = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        coinChangeHelper(coins,amount,0);
            return val == Integer.MAX_VALUE ? -1 : val;
    }
    
    public void coinChangeHelper(int[] coins, int amount, int total) {
        if(amount < 0)
            return;
        if(amount == 0){
            if(total < val){
                val = total;
            }
            return;
        }
        for(int i=0; i<coins.length; i++){
            coinChangeHelper(coins,amount - coins[i],total+1);
        }
    }
}
