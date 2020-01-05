package common.datastruct;

import java.util.Objects;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 链表节点
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

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, next);
	}
}

