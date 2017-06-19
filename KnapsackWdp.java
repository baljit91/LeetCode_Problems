// reference https://www.youtube.com/watch?v=6h6Fi6AQiRM

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KnapsackWdp {
    
    public static int maximumValue(int capacity, int currentIndex, int[] weight, int value[]){
        //if the index is surpassing N
        if(currentIndex >= weight.length)
            return 0;
        
        //If the weight of the current object is greater than capacity.we will not include current current object and gonna check with next object
        if(capacity < weight[currentIndex])
            return maximumValue(capacity, currentIndex + 1, weight, value);
        
        else{
            //considering two scenarios here 
            //1.including the current object.
            //2. not including the current object
            return Math.max(maximumValue(capacity, currentIndex + 1, weight, value),
                            value[currentIndex] + maximumValue(capacity - weight[currentIndex], currentIndex + 1,weight, value));
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

        System.out.println(maximumValue(capacity, 0 , weight, value));
    }
}
