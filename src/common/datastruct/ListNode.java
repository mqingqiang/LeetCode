package common.datastruct;

import java.util.Objects;

/**
 * 链表节点
 * 节点的值为 int 类型是考虑到 LeetCode 链表题目为 int 型，无需实现泛型那么复杂。
 * add() 方法允许在当前头结点链尾增加新节点，主要为了便于刷题测试
 *
 * @author mqq
 * @date 2019-12-29
 */
public class ListNode {
	/**
	 * 节点值
	 */
	public int val;

	/**
	 * 下一个节点
	 */
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

	public void add(int newValue) {
		ListNode newNode = new ListNode(newValue);
		ListNode next = this;
		while (next.next != null) {
			next = next.next;
		}
		next.next = newNode;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, next);
	}
}

