package com.tengol.leetcode;

import com.alibaba.fastjson.JSON;
import com.tengol.leetcode.entity.ListNode;
import com.tengol.leetcode.util.LinkedListUtil;

import java.util.LinkedList;

/**
 * 92. Reverse Linked List II
 * <p>
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * @author dongrui
 * @link https://leetcode-cn.com/problems/reverse-linked-list-ii
 * @date 2020/5/26 12:46
 */
public class No92ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null){
            return null;
        }

        // 找到 m 节点前的一个节点
        ListNode pre = null, cur = head;
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // 反转连表
        ListNode con = pre, tail = cur;
        ListNode third;
        while (n > 0) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
            n--;
        }

        // 拼接反转的连表
        if(con == null){
            head = pre;
        }else {
            con.next = pre;
        }
        tail.next = cur;

        return head;
    }

    public static void main(String[] args) {
        ListNode list = LinkedListUtil.getList();
        LinkedListUtil.printList(list);
        No92ReverseLinkedList2 reverse = new No92ReverseLinkedList2();
        System.out.println("\r\n反转后");
        reverse.reverseBetween(list,2,4);
        LinkedListUtil.printList(list);

    }


}
