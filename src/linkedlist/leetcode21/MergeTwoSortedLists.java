package linkedlist.leetcode21;

import common.datastruct.ListNode;
import common.util.ListUtils;

/**
 * 21. 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class MergeTwoSortedLists {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.add(2);
		l1.add(4);
		ListNode l2 = new ListNode(1);
		l2.add(3);
		l2.add(4);
		Solution solution = new RecurseSolution();
		ListNode mergeList = solution.mergeTwoLists(l1, l2);
		ListUtils.printList(mergeList);
	}
}

/**
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 迭代法
 * 思路：
 * 1、设置一个空白节点 dummy 作为哨兵节点，便于返回合并后的链表
 * 2、维护一个 last 指针，用户维护合并后的链表最后的节点
 * 3、比较 l1 和 l2 链表较小者，last 指向较小者
 * 4、若 l1.val < l2.val，last 指向 l1，并后移 l1 和 last 指针
 * 5、若 l1.val >= l2.val，last 指向 l2，并后移 l2 和 last 指针
 * 6、直到 l1 或 l2 有一个为 null，last 指向不为空的链表
 * 7、返回哨兵结点 dummy.next 即得到合并后的链表
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(1)
 */
class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode last = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				last.next = l1;
				l1 = l1.next;
			} else {
				last.next = l2;
				l2 = l2.next;
			}
			last = last.next;
		}
		last.next = l1 == null ? l2 : l1;
		return dummy.next;
	}
}

/**
 * 递归
 * 思路：
 * 1、先处理边界条件（递归终止条件）
 * 2、l1.val < l2.val，则 l1.next 指向 l1.next 和 l2 合并后的链表
 * 3、l1.val >= l2.val，则 l2.next 指向 l2.next 和 l1 合并后的链表
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(n+m)
 */
class RecurseSolution extends Solution {
	@Override
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}
}