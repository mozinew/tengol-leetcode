package com.tengol.leetcode;


import java.util.Stack;

/**
 * No394DecodeString，中等
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 【个人总结】
 * 1. 思路很简单，使用数据结构【栈】解决，难点在于处理过程
 * 2. 官方题解的巧妙之处：
 *     （1）使用 ptr 指针
 *     （2）入栈时获取连续的数字或字母，将其作为一个整体入栈，简化了出栈合并的问题
 * 3. 该文档的解法可以继续优化，后续复习时可以尝试优化方案，更多解题思路参见官方题解
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
