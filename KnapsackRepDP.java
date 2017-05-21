import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KnapsackRepDP {
    
    static int[] dp;
    public static int knapsack(int capacity,int[] objectWeights){
       
        int max = 0;
        for(int i=0; i < objectWeights.length; i++){
            
            if(objectWeights[i] <= capacity){
                if(dp[capacity - objectWeights[i]] == 0){
                    dp[capacity - objectWeights[i]] = knapsack(capacity - objectWeights[i],objectWeights);
                }
                max = Math.max(max,objectWeights[i] + dp[capacity - objectWeights[i]]);
            }
            
        }
        return max;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        int N;
        int capacity;
        int[] objectWeights;
        for(int i=0; i<testCases; i++){
            N =  sc.nextInt();
            capacity = sc.nextInt();
            objectWeights = new int[N];                
            for(int j=0; j<N; j++){
                objectWeights[j] = sc.nextInt();
            }
            dp = new int[capacity];
            System.out.println(knapsack(capacity,objectWeights));
        }
    }
}
