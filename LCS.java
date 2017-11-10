public class LCS {
    /*
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if(A.length() == 0 || B.length() == 0)
            return 0;
        
        int[][] dp = new int[A.length()][B.length()];
        int max = 0;
        
        for(int i=0; i<A.length(); i++){
            for(int j=0; j<B.length(); j++){
                if(A.charAt(i) == B.charAt(j)){
                    if(i == 0 || j == 0)
                        dp[i][j] = 1;
                    else
                        dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    if(i == 0 || j == 0)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
                if(dp[i][j] > max)
                        max = dp[i][j];
            }
        }
        return max;
    }
    
    
    //recursion solution
    public static int  findLcs(String s1,String s2,int i, int j,int[][] table){
		if(i == s1.length()  || j == s2.length()){
			table[i][j] = 0;
			return table[i][j];
		}
			
		
		//int result = 0;
		
		if(s1.charAt(i) == s2.charAt(j)){
			table[i][j] = 1 + findLcs(s1,s2,i+1,j+1,table);
		}
		else{
			table[i][j]  =  Math.max(findLcs(s1,s2,i+1,j,table),findLcs(s1,s2,i,j+1,table));

		}	
		return table[i][j] ;
	}
}
