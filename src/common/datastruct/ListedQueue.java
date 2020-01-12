package common.datastruct;

/**
 * 链式队列
 *
 * @author mqq
 * @date 2020-01-12
 */
public class ListedQueue implements Queue {

	ListNode head;

	ListNode tail;

	@Override
	public boolean enQueue(int value) {
		ListNode newNode = new ListNode(value);
		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		return true;
	}

	@Override
	public int deQueue() {
		if (head != null) {
			int element = head.val;
			head = head.next;
			if (head == null) {
				tail = null;
			}
			return element;
		}
		return 0;
	}
}