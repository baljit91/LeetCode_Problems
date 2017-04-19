public class CompareVersion {
	public int compareVersion(String a, String b) {
	    String[] versionOne = a.split("\\.");
	    String[] versionTwo = b.split("\\.");

	    int iterator = 0;
            int limiter = Math.min(versionOne.length,versionTwo.length);
        
	    while(iterator < limiter){
	        int ver_one = Integer.parseInt(versionOne[iterator]);
	        int ver_two = Integer.parseInt(versionTwo[iterator]);
	        if(ver_two < ver_one){
	            return  1;
	        }
	        if(ver_two > ver_one){
	            return -1;
	        }
	        iterator++;
	    }
      
        //if version 1 is greater
      if(versionOne.length > limiter){
          while(iterator < versionOne.length){
              if(Integer.parseInt(versionOne[iterator]) > 0){
                  return 1;
              }
              iterator++;
          }
      }
      //if version 2 is greater
      if(versionTwo.length > limiter){
          while(iterator < versionTwo.length){
              if(Integer.parseInt(versionTwo[iterator]) > 0){
                  return -1;
              }
              iterator++;
          }
      }
     
      //if both version are equal  
	    return 0;
	}
}
