package leecode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author lzy
 * @version 1.0.0
 * @date 2021/12/21 下午2:00
 * @Description
 */
public class InterView01 {

    public static void main(String[] args) {
//        String key = "1234567890-=+_qwertyuiop[]|asdfgh1";
//
//        System.out.println(isUnique(key));


        System.out.println(CheckPermutation("aabb","abab"));
    }


    /**
     * 01.01 判断字符串是否唯一
     * 要求：实现一个算法，确定一个字符串 s 的所有字符是否全都不同
     * 逻辑；利用位运算符,将每一位都转换为二进制，并与高低位逻辑与，如果不为0 则不唯一，如果为0 则高或低位逻辑或当前字符
     */
    public static boolean isUnique(String str) {
        long low64 = 0;
        long high64 = 0;

        for (char c : str.toCharArray()) {
            if (c >= 64) {
                long indexBit = 1L << (c - 64);
                if ((high64 & indexBit) != 0) {
                    return false;
                }
                high64 |= indexBit;
            } else {
                long indexBit = 1L << c;
                if ((low64 & indexBit) != 0) {
                    return false;
                }
                low64 |= indexBit;
            }

        }
        return true;
    }


    /**
     * 01.02. 判定是否互为字符重排
     * 题目要求：给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     */
    public static boolean CheckPermutation(String s1, String s2){
        //  第一种方式  将字符串转为字符数组，排序后，再转为字符串比较是否相等
//        if(s1.length()!=s2.length())
//            return false;
//
//        char[]  c1= s1.toCharArray();
//        char[]  c2= s2.toCharArray();
//        Arrays.sort(c1);
//        Arrays.sort(c2);
//        return String.valueOf(c1).equals(String.valueOf(c2));

        // 第二种方式，同为重排必要提交是存在的字符个数一致
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i),0)+1);
            map.put(s2.charAt(i), map.getOrDefault(s2.charAt(i),0)-1);
        }
        return map.values().parallelStream().allMatch(c->c==0);
    }


}
