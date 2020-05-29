package com.tengol.leetcode;


import java.util.Stack;

/**
 * No394DecodeString
 *
 * @author dongrui
 * @date 2020/5/28 15:05
 */
public class No394DecodeString {
    public static void main(String[] args) {
        String case1 = "3[a]2[bc]";
        String case2 = "3[a2[c]]";
        String case3 = "2[abc]3[cd]ef";
        String s = decodeString(case3);
        System.out.println(s);
    }

    private static int ptr = 0;

    private static String decodeString(String str) {
        String result = "";

        if (str == null) return null;

        Stack<String> strStack = new Stack<>();
        while (ptr < str.length()) {
            Character c = str.charAt(ptr);
            // 如果是数字，则获取连续的数字后入栈
            if (Character.isDigit(c)) {
                strStack.push(getDigit(str));
            }
            // 如果是字母，则获取连续的字母后入栈
            else if (Character.isLetter(c)) {
                strStack.push(getLetter(str));
            }
            // 如果是 [ 则直接入栈
            else if (c.equals('[')) {
                strStack.push(String.valueOf(str.charAt(ptr++)));
            }
            // 如果是 ] 则出栈
            else {
                // 获取字母，获取 [ 前的所有字母
                String p = strStack.pop();
                String letters = "";
                while (!p.equals("[")) {
                    letters = p.concat(letters);
                    p = strStack.pop();
                }
                // 获取数字
                String dStr = strStack.pop();
                int num = Integer.parseInt(dStr);
                // 产生新字母并入栈
                StringBuffer sb = new StringBuffer(letters);
                for (int i = 1; i < num; i++) {
                    sb.append(letters);
                }
                strStack.push(sb.toString());
                ptr++;
            }

        }

        // 获取最终字符串
        while (!strStack.isEmpty()) {
            String tempStr = strStack.pop();
            result = tempStr.concat(result);
        }

        return result;
    }

    private static String getDigit(String str) {
        StringBuffer sb = new StringBuffer();
        // 获取连续的数字
        while (ptr < str.length() && Character.isDigit(str.charAt(ptr))) {
            sb.append(str.charAt(ptr++));
        }
        return sb.toString();
    }

    private static String getLetter(String str) {
        StringBuffer sb = new StringBuffer();
        while (ptr < str.length() && Character.isLetter(str.charAt(ptr))) {
            sb.append(str.charAt(ptr++));
        }
        return sb.toString();
    }
}
