package linkedlist.leetcode24;

import java.util.List;

import common.datastruct.ListNode;
import common.util.ListUtils;

/**
 * 24. 两两交换链表中的节点
 *
 * @author mqq
 * @date 2020-01-07
 */
public class SwapNodesInPairs {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.add(2);
		head.add(3);
		head.add(4);
		head.add(5);
		head.add(6);
		head.add(7);
		ListUtils.printList(head);
		Solution solution = new Solution();
		ListNode swapPairs = solution.swapPairs(head);
		ListUtils.printList(swapPairs);
	}
}

/**
 * 迭代
 * 思路：
 * 1、新增一个空的头结点 empty，用于指向整个链表
 * 2、prev 指针表示当前节点的上一个节点，此处当前节点指 head（一般会重新定义一个当前节点，如 ListNode curr = head，此处为了代码更少直接操作 head）
 * 3、然后操作 prev 和 curr/head 指针交换第一第二位置
 * 4、交换后 prev 和 curr/head 指针向后移动两次，重复步骤 2/3/4
 * 5、返回 empty.next，即得两两反转后的链表
 */
class Solution {
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 新增一个空头结点，指向 head
		ListNode empty = new ListNode(-1);
		empty.next = head;
		ListNode prev = empty;
		while (head != null && head.next != null) {
			ListNode temp = head.next.next;
			prev.next = head.next;
			head.next.next = head;
			head.next = temp;
			prev = head;
			head = prev.next;
		}
		return empty.next;
	}
}

/**
 * 递归
 * 思路：
 * 1、首先要把第三个结点之后的结点是已反转好了的
 * 2、然后反转第一和第二个结点
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class RecurseSolution extends Solution {
	@Override
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode listNode = swapPairs(head.next.next);
		ListNode next = head.next;
		head.next.next = head;
		head.next = listNode;
		return next;
	}
}