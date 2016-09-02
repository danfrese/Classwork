package quiz04;

import java.util.Arrays;

// Copy and complete the program to fill an array of strings with the 
// twenty-six strings "a" ... "z"
public class FillStrings {
   public static void main(String[] args) {
	   
      String[] letters = new String[26];
      int index = 0;
      
      for (char ch = 'a'; ch <= 'z'; ch++) {
         letters[index] = Character.toString(ch);
         index++;
      }
      
      System.out.println(Arrays.toString(letters));
   }
}