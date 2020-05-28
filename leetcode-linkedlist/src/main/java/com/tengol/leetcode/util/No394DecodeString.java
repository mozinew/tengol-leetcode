package com.tengol.leetcode.util;

import netscape.security.UserTarget;

import java.util.Stack;

/**
 * No394DecodeString
 *
 * @author dongrui
 * @date 2020/5/28 15:05
 */
public class No394DecodeString {
    public static void main(String[] args) {
        String case2 = "3[a2[c]]";
        String case1 = "3[a]2[bc]";
        String s = decodeString(case1);
        System.out.println(s);
    }

    private static String decodeString(String str){
        // 判空
        if(str == null){
            return null;
        }
        // 初始2个栈
        Stack<String> rs = new Stack<>();
        Stack<Character> cs = new Stack<>();

        char[] chars = str.toCharArray();
        for(Character c : chars){
            // [=91, ]=93
            if(c == 93){
                String s = "";
                Character pChar = cs.pop();
                while (pChar != 91){
                    s = String.valueOf(pChar).concat(s);
                }
                Character pNum = cs.pop();
                String unit = s;
                if(Character.isDigit(pNum)){
                   for(int j=1; j<Integer.parseInt(String.valueOf(pNum));j++){
                       s = s.concat(unit);
                   }
                }
                // 存储结果
                rs.push(s);
            }else{
                cs.push(c);
            }
        }

        // 读取结果
        String result = "";
        while (!rs.isEmpty()){
            String pop = rs.pop();
            result = pop.concat(result);
        }

        return result;
    }

    private static String decodeString2(String str) {
        // 判空
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        String s = "";
        // 创建一个栈
        Stack<String> stack = new Stack<>();

        // 拆成有意义的字符串(如何解析到数字）
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // 如果不是 ] 则入栈
            if (c != ']') {
                stack.push(String.valueOf(c));
            }
            // 如果是 ] 则出栈
            else {
                String con = "";
                while (true) {
                    String t = stack.pop();
                    if (!t.equals("[")) {
                        con = t.concat(con);
                    } else {
                        int n = Integer.parseInt(stack.pop());
                        String unit = con;
                        for (int j = 1; j < n; j++) {
                            con = con.concat(unit);
                        }
                        if (i < chars.length - 1) {
                            stack.push(con);
                        }
                        break;
                    }
                }
            }
        }
        while (!stack.isEmpty()){
            String temp = stack.pop();
            s = temp.concat(s);
        }

        return s;
    }
}
