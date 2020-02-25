package normal.study.suanfa;

public class LengthOfLongestSubstring {
    public static void main(String[] args){
        String s = "pwpke";


    }


    public static int lengthOfLongestSubstring(String s){
        int count = 0;
        String str = null;

        for(int i = 0; i<=s.length(); i++){
            for(int j = i+1; j <= s.length(); j++){
                if(!str.contains(s.substring(i,j))){
                    str = str + s.substring(i,j);

                    count = str.length();
                }else {

                }
            }

        }

        return count;
    }
}
