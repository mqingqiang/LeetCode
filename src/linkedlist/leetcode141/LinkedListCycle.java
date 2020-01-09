package linkedlist.leetcode141;

import java.util.HashSet;
import java.util.Set;

import common.datastruct.ListNode;

/**
 * 141. 环形链表
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * @author mqq
 * @date 2020-01-09
 */
public class LinkedListCycle {
	public static void main(String[] args) {

	}
}

/**
 * 记忆法
 * 思路：
 * 1、用 visited 存储已访问过的链表
 * 2、从头开始遍历链表，判断当前节点是否在 visited 中，若存在，直接返回 true 表示有环
 * 3、若不存在，继续遍历知道链表尾部，整个链表遍历完则返回 false
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public boolean hasCycle(ListNode head) {
		Set<ListNode> visited = new HashSet<>();
		while (head != null) {
			if (visited.contains(head)) {
				return true;
			}
			visited.add(head);
			head = head.next;
		}
		return false;
	}
}

/**
 * 快慢指针
 * 思路：
 * 1、快慢指针同时出发，快指针每次走两步，慢指针每次走一步，若有环，快慢指针会再次相遇
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class FastSlowPointerSolution extends Solution {
	@Override
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		// 快慢指针
		ListNode fast = head;
		ListNode slow = head;
		do {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == null || fast.next == null) {
				return false;
			}
		} while (fast != slow);
		return true;
	}
}