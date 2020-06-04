package com.tengol.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * No 974 Subarray Sums Divisible By K (974. 和可被 K 整除的子数组)
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * 示例：
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *  
 * 提示：
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 【个人总结】
 * 1. 详细解题思路参考官方题解
 * 2. 解决连续子数组问题，优先考虑前缀和（数学理论是同余定理）
 *
 * @author dongrui
 * @date 2020/5/29 13:49
 */
public class No974SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer,Integer> records = new HashMap<>();
        // 若全元素之和可被整除，则命中默认值
        records.put(0,1);
        // 遍历前缀和，并记录次数
        int sum = 0, ans = 0;
        for(int a : A){
            // 计算当前的前缀和
            sum = sum + a;
            // 确保余数为正数，Java 取余运算特殊，会出现负值余数，故要加 K 转换
            int mod = ((sum % K) + K) % K;
            // 获取相同余数的前缀和
            int sames = records.getOrDefault(mod,0);
            // 根据同余定理，计算连续子数组的个数
            ans = ans + sames;
            // 更新余数的出现次数
            records.put(mod, sames + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        No974SubarraySumsDivisibleByK sub = new No974SubarraySumsDivisibleByK();
        int[] a = {4,5,0,-2,-3,1};
        int k = 5;
        int result = sub.subarraysDivByK(a, k);
        System.out.println(result);
    }
}
