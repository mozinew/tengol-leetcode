package com.tengol.leetcode;

/**
 * 837 new 21 game （新 21 点）
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。
 * 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * 示例 1：
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 *
 * 示例 2：
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 *
 * 示例 3：
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 *
 * 提示：
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/new-21-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 【解题思考】
 * 1. 概率 = 可能的次数 / 总次数
 * 2. 如果都取最小值（k = 0, n = 0, n = 1），则概率是 1
 * 3. 如果只选择一张，那么概率等于 N / W
 *
 * @author dongrui
 * @date 2020/6/3 11:58
 */
public class No837New21Game {
    public double new21Game(int N, int K, int W) {

        return 0.0;
    }

    public static void main(String[] args) {
        No837New21Game new21Game = new No837New21Game();
        int n1 = 10, k1 = 1, w1 = 10;
        int[] case1 = {10, 1, 10};
        int[] case2 = {21, 17, 10};
        double result = new21Game.new21Game(case1[0], case1[1], case1[2]);
        System.out.println(result);
    }
}
