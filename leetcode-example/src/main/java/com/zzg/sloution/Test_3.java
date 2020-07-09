package com.zzg.sloution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *      输入: "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *      输入: "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *      输入: "pwwkew"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *        请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Test_3 {

    public static void main(String[] args) {
        System.out.println(length("pwwkew"));
    }

    public static int length(String s){
        char[] chars = s.toCharArray();
        List<Object> maxLength = new ArrayList();
        List<Object> temp = new ArrayList();
        for(char c : chars){
            if(temp.contains(c)){
                if(temp.size() > maxLength.size()){
                    maxLength = new ArrayList<Object>(temp);
                }
                temp = temp.subList(temp.indexOf(c) + 1, temp.size());
            }
            temp.add(c);
        }
        System.out.println("**********" + maxLength.toString());
        return maxLength.size();
    }




















    // abcabcbb
    //----------------------------------------------------------------
    public static int length1(String s){
        char[] chars = s.toCharArray();
        int tem = 0;
        for (int i = 0; i < chars.length; i++) {



            System.out.println(i);
        }
        return 1;
    }

}
