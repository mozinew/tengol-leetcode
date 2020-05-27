package com.tengol.leetcode.util;

import com.alibaba.fastjson.JSON;
import com.tengol.leetcode.entity.ListNode;

/**
 * ListUtil
 *
 * @author dongrui
 * @date 2020/5/27 12:44
 */
public class LinkedListUtil {
    public static ListNode getList() {
        ListNode head = new ListNode(1);

        ListNode cur = head;
        for (int i = 2; i <= 5; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        return head;
    }

    public static void printList(ListNode head){
        ListNode cur = head;
        while (cur != null) {
            System.out.print(JSON.toJSONString(cur.val) + " ");
            cur = cur.next;
        }
    }
}
