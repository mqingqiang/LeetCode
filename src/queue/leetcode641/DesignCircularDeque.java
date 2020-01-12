package queue.leetcode641;

/**
 * 641. 设计循环双端队列
 *
 * @author mqq
 * @date 2020-01-12
 */
public class DesignCircularDeque {
	public static void main(String[] args) {
		MyCircularDeque circularDeque = new MyCircularDeque(3);
		System.out.println(circularDeque.insertLast(1));
		System.out.println(circularDeque.insertLast(2));
		System.out.println(circularDeque.insertFront(3));
		System.out.println(circularDeque.insertFront(4));
		System.out.println(circularDeque.getRear());
		System.out.println(circularDeque.isFull());
		System.out.println(circularDeque.deleteLast());
		System.out.println(circularDeque.insertFront(4));
		System.out.println(circularDeque.getFront());
	}
}

/**
 * 双端循环队列
 * 思路与循环队列一致。只是在双端循环队列头部添加元素是，要把 head 指针往前移
 * 思路：
 * 1、先实现一个 循环队列，参考【622. 设计循环队列】
 * 2、insertFront 时往前移动 head 指针，前移：head = (head - 1 + size) % size;
 * 3、deleteFront 后移 head 指针，后移：head = (head + 1) % size;
 * 4、其他操作与循环队列类似
 */
class MyCircularDeque {

	private int[] elements;

	private int size;

	private int head = 0;

	private int tail = 0;

	/**
	 * Initialize your data structure here. Set the size of the deque to be k.
	 */
	public MyCircularDeque(int k) {
		size = k + 1;
		elements = new int[size];
	}

	/**
	 * Adds an item at the front of Deque. Return true if the operation is successful.
	 */
	public boolean insertFront(int value) {
		if (isFull()) {
			return false;
		}
		head = (head - 1 + size) % size;
		elements[head] = value;
		return true;
	}

	/**
	 * Adds an item at the rear of Deque. Return true if the operation is successful.
	 */
	public boolean insertLast(int value) {
		if (isFull()) {
			return false;
		}
		elements[tail] = value;
		tail = (tail + 1) % size;
		return true;
	}

	/**
	 * Deletes an item from the front of Deque. Return true if the operation is successful.
	 */
	public boolean deleteFront() {
		if (isEmpty()) {
			return false;
		}
		head = (head + 1) % size;
		return true;
	}

	/**
	 * Deletes an item from the rear of Deque. Return true if the operation is successful.
	 */
	public boolean deleteLast() {
		if (isEmpty()) {
			return false;
		}
		tail = (tail - 1 + size) % size;
		return true;
	}

	/**
	 * Get the front item from the deque.
	 */
	public int getFront() {
		if (isEmpty()) {
			return -1;
		}
		return elements[head];
	}

	/**
	 * Get the last item from the deque.
	 */
	public int getRear() {
		if (isEmpty()) {
			return -1;
		}
		int index = (tail - 1 + size) % size;
		return elements[index];
	}

	/**
	 * Checks whether the circular deque is empty or not.
	 */
	public boolean isEmpty() {
		return head == tail;
	}

	/**
	 * Checks whether the circular deque is full or not.
	 */
	public boolean isFull() {
		return (tail + 1) % size == head;
	}
}
