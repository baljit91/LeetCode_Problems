/** Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
**/

public class Solution1 {
    public String reverseWords(String s) {
        String[] list = s.split(" ");
        String ans = "";
        for(int i=0; i<list.length;i++){
            ans = ans + rev(list[i]) + " "; 
        }
        return ans.substring(0,s.length());
    }
    
    String rev(String s){
        int length = s.length();
        
        char[] chararray = s.toCharArray();
        for(int i=0; i< length/2; i++){
            char temp = chararray[length - 1 -i];
            chararray[length - 1 -i] = chararray[i];
            chararray[i] = temp;
        }
        
        return new String(c);
    }
}
