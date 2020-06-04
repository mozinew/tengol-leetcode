package com.tengol.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * No198HouseRobber， 简单
 * <p>
 * 198. 打家劫舍
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 【第一次解题疑问】
 * 1. 第一次未做出来，尝试 3 个番茄
 * 2. 卡壳点：不连续意味着什么？如何用数学或程序角度表示不连续？
 * 3. 解题思路
 * （1） 使用两重 for 循环可以暴力解题，时间复杂度太高
 * （2） 似乎可以使用递归，但没找到递归入口
 * （3） 两个核心点：选择尽可能大的数字，选择尽可能多的项，所以，如果是偶数则一定是一半，奇数则最少是一半
 * （4） 遍历核心点：前面的选择项会影响后面元素的选择，比如选择元素 0，则下一个元素必须是 2 或 3
 * （5） 最终发现，可以使用递归，
 * 1） 初始选择可以是 0 或 1   // 起始切入点
 * 2） 后续每一步都可以走 2 步或走 3 步  // 递归处理
 * 3） 走到最后，如果剩 1 个元素则直接返回，如果剩 2个元素则返回较大的   // 递归终止条件
 * 4. 递归实现提交失败，原因是：超出时间限制。也就是说，时间复杂度太高了，一味地递归就是暴力解题
 * 5. 看了官方题解，正确解法：动态规划 + 滚动数组
 * <p>
 * 【个人总结】
 *
 * @author dongrui
 * @date 2020/5/29 12:48
 */
public class No198HouseRobber {

    /**
     * 官方题解：动态规划 + 动态数组
     */
    public int rob(int[] nums) {
        // 判空处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        // 创建一个数组，每个元素值表示前 i 项的总值
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 遍历数组
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[length - 1];
    }

    /**
     * 递归实现，时间复杂度太高，提交失败：超出时间限制
     */
    public int rob2(int[] nums) {
        int sum0 = sum(0, nums);
        int sum1 = sum(1, nums);
        return Math.max(sum0, sum1);
    }

    public int sum(int start, int[] nums) {
        int end = nums.length - 1;
        if (start > end) {
            return 0;
        }
        // 当前索引对应的数值
        int curVal = nums[start];
        // 如果是最后一个元素，则直接返回
        if (start == end) {
            return nums[end];
        }
        // 如果最后还有两个元素，则比较大小后返回大的
        if (start == end - 1) {
            return Math.max(nums[end - 1], nums[end]);
        }
        // 第一种选择：走 2 步
        int sum0 = curVal + sum(start + 2, nums);
        // 第二种选择：走 3 步
        int sum1 = curVal + sum(start + 3, nums);

        return Math.max(sum0, sum1);
    }

    public int rob1(int[] nums) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);
        sumMap.put(1, 0);

        for (int i = 0; i < nums.length; i++) {
            int key = i % 2;
            int val = nums[i];
            sumMap.put(key, sumMap.get(key) + val);
        }

        return sumMap.get(0) > sumMap.get(1) ? sumMap.get(0) : sumMap.get(1);
    }

    public static void main(String[] args) {
        No198HouseRobber robber = new No198HouseRobber();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {2, 1, 1, 2};
        int[] nums4 = {2,1};
        int[] nums5 = {1};
        int result = robber.rob(nums4);
        System.out.println(result);
    }
}
























