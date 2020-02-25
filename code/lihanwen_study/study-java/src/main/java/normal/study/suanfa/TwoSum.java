package normal.study.suanfa;

import java.util.ArrayList;

public class TwoSum {
    public static void main(String[] args){
        int[] nums = {3,7,9,7,1};

        int target = 10;

        ArrayList<String> res = twoSum(nums, target);

        System.out.println(res);

    }


    public static ArrayList<String> twoSum(int[] nums, int target) {

        ArrayList<String> res = new ArrayList<String>();

        for(int i=0; i<=nums.length; i++){
            for(int j=i +1; j< nums.length; j++){
                if(target == nums[i] + nums[j]){
                    String str = String.valueOf(i) + "-" + String.valueOf(j);
                    res.add(str);
                }
            }
        }

        return res;

    }

}
