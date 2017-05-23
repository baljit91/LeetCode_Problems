public class FirstMissingPositiveOpt {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0){
            return 1;
        }
        int num2 = 2;
        int ans = 1;
        for(int i=0; i < nums.length; i++){
            while(nums[i] >0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                int num = nums[i];
                nums[i] = nums[num-1] ;
                nums[num-1] = num;
            }
        }
        
        for(int i=0; i < nums.length; i++){
            if(nums[i] == i+1){
                ans++;
            }
            else{
                break;
            }
        }
        return ans;
        
}
}
