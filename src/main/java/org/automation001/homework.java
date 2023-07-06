package org.automation001;

public class homework {
    //Given two temperatures, return true if one is less than 0
    // and the other is greater than 100.
    //
    //
    //icyHot(120, -1) → true
    //icyHot(-1, 120) → true
    //icyHot(2, 120) → false

    public static boolean ih(int temp1, int temp2){
        if ((temp1<0 && temp2 >100) || (temp1>100 && temp2<0)){
            return true;
        }else {
            return false;
        }
    }
//Given a string, return a new string where the last 3 chars are now in
// upper case. If the string has less than 3 chars, uppercase whatever is there.
// Note that str.toUpperCase() returns the uppercase version of a string.
//
//
//endUp("Hello") → "HeLLO"
//endUp("hi there") → "hi thERE"
//endUp("hi") → "HI"

    public static String end(String str){
        if (str.length()<=3) {
            return str.toUpperCase();
        }else{
            int cut = str.length() -3;
            String front = str.substring(0,cut);
            String back = str.substring(cut);
            return front + back.toUpperCase();
        }
    }
//Given an array of ints, return a new array length 2 containing the
// first and last elements from the original array. The original array will
// be length 1 or more.
//makeEnds([1, 2, 3]) → [1, 3]
//makeEnds([1, 2, 3, 4]) → [1, 4]
//makeEnds([7, 4, 6, 2]) → [7, 2]

    public static int[] makeEnds(int[] nums){
        int[] result = new int[2];
        result[0] = nums[0];
        result[1] = nums[nums.length-1];
        return result;
    }

}
