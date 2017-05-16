public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        
        if(s == null || s.length() == 0){
            return 0;
        }
        
        int sizeLongestSub = 0;

        int i = 0;
        int j = 0;
        
        while(i < s.length()){
            HashSet<Character> set = new HashSet<Character>();
            
            j = i;
            while(j < s.length() && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
            }
            
            if(set.size() > sizeLongestSub){
                sizeLongestSub = set.size();
            }
            
            i++;
        }
        
        return sizeLongestSub;
    }
}
