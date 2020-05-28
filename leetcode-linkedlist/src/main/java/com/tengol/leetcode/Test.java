package com.tengol.leetcode;

/**
 * Test
 *
 * @author dongrui
 * @date 2020/5/27 21:16
 */
public class Test {
    public static int subarraysDivByK(int[] A, int K) {
        int num = 0;
        for(int i = 0; i < A.length; i++){
            int sub = 0;
            int t = -1;
            for(int j=i; j < A.length; j++){
                sub += A[j];
                if(sub / K == 0 && j > t){
                    num++;
                    sub = 0;
                    t = j;
                    j = i;
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        int[] a = {4,5,0,-2,-3,1};
        int i = subarraysDivByK(a, 5);
        System.out.println(i);
    }
}
