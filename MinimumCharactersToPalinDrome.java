public class Solution {
    public int solve(String A) {
        if(A == null || A.length() ==0){
            return 0;
        }
        
        
        int count = 0;
        int index = A.length();
        
        while(index>=0 && !isPalindrome(A.substring(0,index))){
            index--;
            count++;
        }
        
        return count;
    }
    
    
    public boolean isPalindrome(String s){
        int start = 0;
        int last = s.length() -1;
        
        while(start <= last){
            if(s.charAt(start) != s.charAt(last)){
                return false;
            }
            start++;
            last--;
        }
        return true;
    }
}
