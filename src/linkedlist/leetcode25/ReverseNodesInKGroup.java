package linkedlist.leetcode25;

import common.datastruct.ListNode;
import common.util.ListUtils;

/**
 * 25. K 个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author mqq
 * @date 2020-01-10
 */
public class ReverseNodesInKGroup {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.add(2);
		head.add(3);
		head.add(4);
		head.add(5);
		head.add(6);
		head.add(7);
		ListUtils.printList(head);

		Solution solution = new RecurseSolutionIII();
		ListNode reversedHead = solution.reverseKGroup(head, 3);
		ListUtils.printList(reversedHead);
	}
}

/**
 * 迭代法
 * 思路：
 * 1、链表分为已翻转、待翻转、未翻转部分
 * 2、其中已翻转和待翻转部分是一个链表，未翻转部分是一个链表，是割裂开来的两个链表
 * 3、每次循环翻转待翻转部分，翻转过后把2个链表连接起来，寻找下一个待翻转部分，重新割裂，重复该步骤。
 * 4、待循环到链表末尾，即反转成功
 */
class Solution {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		ListNode start = head;
		ListNode end = prev;
		prev.next = head;
		while (end.next != null) {
			for (int i = 0; i < k; i++) {
				if (end == null) {
					break;
				}
				end = end.next;
			}
			if (end == null) {
				break;
			}
			ListNode next = end.next;
			end.next = null;
			prev.next = reverse(start);
			start.next = next;
			prev = start;
			start = next;
			end = prev;
		}
		return dummy.next;
	}

	protected ListNode reverse(ListNode head) {
		if (head.next == null) {
			return head;
		}
		ListNode reversed = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return reversed;
	}
}

/**
 * 递归
 * 思路：
 * 1、定义空节点 dummy，指向头节点
 * 2、先找出前 k 个结点，递归反转 k+1 结点后的元素
 * 3、连接前 k 个结点与已翻转的 k+1 结点
 * 4、返回 dummy.next，即可得到反转后的链表
 */
class RecurseSolution extends Solution {
	@Override
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode tail = dummy;
		for (int i = 0; i < k; i++) {
			if (tail == null) {
				break;
			}
			tail = tail.next;
		}
		if (tail != null) {
			ListNode reverseKGroup = reverseKGroup(tail.next, k);
			tail.next = null;
			reverse(head);
			head.next = reverseKGroup;
			dummy.next = tail;
		}
		return dummy.next;
	}
}

/**
 * 递归II
 * 思路：
 * 1、定义空节点 dummy，指向头节点
 * 2、先找出前 k 个结点，递归反转 k+1 结点后的元素
 * 3、连接前 k 个结点与已翻转的 k+1 结点
 * 4、返回 dummy.next，即可得到反转后的链表
 */
class RecurseSolutionII extends Solution {
	@Override
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode tail = dummy;
		for (int i = 0; i < k; i++) {
			if (tail == null) {
				break;
			}
			tail = tail.next;
		}
		if (tail != null) {
			ListNode reverseKGroup = reverseKGroup(tail.next, k);
			tail.next = reverseKGroup;
			ListNode curr = head.next;
			while (--k > 0) {
				head.next = curr.next;
				curr.next = dummy.next;
				dummy.next = curr;
				curr = head.next;
			}
		}
		return dummy.next;
	}
}

/**
 * 递归III
 * 思路：
 * 1、定义空节点 dummy，指向头节点
 * 2、先找出前 k 个结点，递归反转 k+1 结点后的元素
 * 3、连接前 k 个结点与已翻转的 k+1 结点
 * 4、返回 dummy.next，即可得到反转后的链表
 */
class RecurseSolutionIII extends Solution {
	@Override
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k < 2) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		int count = 0;
		while (head != null) {
			count++;
			if (count % k == 0) {
				prev = reverse(prev, head.next);
				head = prev.next;
			} else {
				head = head.next;
			}
		}
		return dummy.next;
	}

	/**
	 * 反转某个区间的链表节点，如：
	 * prev = [1, 2, 3, 4, 5]
	 * next = [5]
	 * 则反转区间为 [2, 3, 4]
	 * 反转后链表为 [1, 4, 3, 2, 5]
	 *
	 * @param prev 反转区间的前一个节点 [1, 2, ,3 ,4 5]
	 * @param next 反转区间的后一个节点 [5]
	 * @return 反转区间的最后一个节点 [2, 5]
	 */
	private ListNode reverse(ListNode prev, ListNode next) {
		ListNode last = prev.next;
		ListNode curr = last.next;
		while (curr != next) {
			last.next = curr.next;
			curr.next = prev.next;
			prev.next = curr;
			curr = last.next;
		}
		return last;
	}
}