package linkedlist.leetcode206;

import common.datastruct.ListNode;
import common.util.ListUtils;

/**
 * 206. 反转链表
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * @author mqq
 * @date 2020-01-07
 */
public class ReverseLinkedList {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.add(2);
		head.add(3);
		head.add(4);
		head.add(5);
		Solution solution = new Solution();
		ListUtils.printList(head);
		ListNode reverseList = solution.reverseList(head);
		ListUtils.printList(reverseList);
	}
}

/**
 * 迭代法
 * 思路：
 * 1、新建一个链表 prev
 * 2、每次取 head 链表的头结点插入到 prev 链表的头部
 * 3、待 head 链表遍历到末尾，返回 prev 链表即可
 * 注意： prev 其实是一个新的链表，一开始为 null，一个一个地把 head 的节点插入到 prev 链表的头部
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class Solution {
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		return prev;
	}
}

/**
 * 递归法
 * 思路：
 * 1、递归反转链表，首先要把后面的链表想象成已经反转好了。如 [1, 2, 3, 4, 5]，要把后面的 [2, 3, 4, 5] 想象成已反转的结果
 * 2、第一步后，想象得 [1, temp],即只需要反转这 2 个元素即可。这两个元素的反转就很简单了
 * 3、反转 [1, temp]，操作：head.next.next = head; head.next = null。经过这 2 步即可反转链表，随后返回 temp 指针
 * 4、temp 表示后面的元素反转后的指针，head.next.next = head; head.next = null 是把 temp 链尾指向 head 元素
 */
class RecurseSolution extends Solution {
	@Override
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode listNode = reverseList(head.next);
		// 反转 head 和 listNode
		head.next.next = head;
		head.next = null;
		return listNode;
	}
}