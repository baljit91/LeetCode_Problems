public class Solution {
    public int longestPalindrome(String s) {
        int result = 0;
        boolean containsOddOccurence = false;
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(int i=0; i < s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }
            else{
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        
        
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey() + " ,," +entry.getValue());
            if(entry.getValue() % 2 == 0){
                result = result + entry.getValue();
            }
            else{
                containsOddOccurence = true;
                result = result + entry.getValue() - 1;
            }
        }
        
        if(containsOddOccurence){
            result = result + 1;
        }
        
        return result;
    }
}
