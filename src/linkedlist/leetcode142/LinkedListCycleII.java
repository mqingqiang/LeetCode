package linkedlist.leetcode142;

import java.util.HashSet;
import java.util.Set;

import common.datastruct.ListNode;
import common.util.ListUtils;

/**
 * 142. 环形链表 II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/submissions/
 *
 * @author mqq
 * @date 2020-01-10
 */
public class LinkedListCycleII {
	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		ListNode head2 = new ListNode(2);
		head.next = head2;
		head.add(0);
		head.add(-4);
		head.next = head2;
		Solution solution = new FastSlowPointerSolution();
		ListUtils.printList(head);
		solution.detectCycle(head);
		ListUtils.printList(head);
	}
}

/**
 * 记忆法
 * 思路：
 * 1、用 HashSet 保存访问过的节点和位置
 * 2、遍历判断链表中的节点是否已访问过，已访问过则直接返回该节点
 * 3、遍历结束，则返回 null
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		// 存储已访问的节点
		Set<ListNode> visited = new HashSet<>();
		while (head != null) {
			if (visited.contains(head)) {
				return head;
			}
			visited.add(head);
			head = head.next;
		}
		return null;
	}
}

/**
 * 快慢指针
 * 思路：
 * 1、快慢指针同时走，快指针一次走两步，慢指针一次走一步
 * 2、若有环，则快慢指针会相遇，相遇时慢指针不变，快指针回到起点，并且改为每次走一步。此时快慢指针同时走相遇时则为入环点
 * 3、若五环，直接返回 null
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class FastSlowPointerSolution extends Solution {
	@Override
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode fast = head.next.next;
		ListNode slow = head.next;
		while (fast != slow) {
			if (fast == null || fast.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		// 快指针回到起点，并且改为每次走一步
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
}