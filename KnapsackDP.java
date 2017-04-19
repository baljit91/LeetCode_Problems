import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KnapsackDP {
    static int[][] K ;
    
    public static int maximumValue(int capacity, int currentIndex, int[] weight, int value[]){
        if(currentIndex >= weight.length){
            K[capacity][currentIndex] = 0;
            return K[capacity][currentIndex];
        }
        
        if(capacity < weight[currentIndex]){ 
            if(K[capacity][currentIndex + 1] == 0){
                K[capacity][currentIndex + 1] = maximumValue(capacity, currentIndex + 1, weight, value);;
            }
            return K[capacity][currentIndex + 1];
        }
        
        else{
            if(K[capacity][currentIndex + 1] == 0){
                K[capacity][currentIndex + 1] = maximumValue(capacity, currentIndex + 1, weight, value);;
            }
            
            if(K[capacity - weight[currentIndex]][currentIndex + 1] == 0){
                K[capacity - weight[currentIndex]][currentIndex + 1] = maximumValue(capacity - weight[currentIndex], currentIndex + 1,weight, value);
            }
            
            return Math.max(K[capacity][currentIndex + 1],value[currentIndex] + K[capacity - weight[currentIndex]][currentIndex + 1]);
        }
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int n = sc.nextInt();
        int weight[] = new int[n];
        int value[] = new int[n];
        
        for(int i = 0; i < n; i++){
            int w = sc.nextInt();
            weight[i] = w;
            
            int v = sc.nextInt();
            value[i] = v;
        }
        K = new int[capacity+1][n+1];
        System.out.println(maximumValue(capacity, 0 , weight, value));
    }
}
