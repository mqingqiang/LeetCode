package common.datastruct;

/**
 * 循环队列
 *
 * @author mqq
 * @date 2020-01-12
 */
public class CircularQueue implements Queue {

	/**
	 * 队列元素
	 */
	private int[] elements;

	/**
	 * 队列大小
	 */
	private int size;

	/**
	 * 队头指针
	 */
	private int head;

	/**
	 * 队尾指针
	 */
	private int tail;

	public CircularQueue(int capacity) {
		this.elements = new int[capacity];
		size = capacity;
	}

	@Override
	public boolean enQueue(int value) {
		// 队满
		if ((tail + 1) % size == head) {
			return false;
		}
		elements[tail] = value;
		// 后移一位，若移动到数组末尾，则赋值为 o
		tail = (tail + 1) % size;
		return true;
	}

	@Override
	public int deQueue() {
		// 队空
		if (head == tail) {
			return -1;
		}
		int element = elements[head];
		// 后移一位，若移动到数组末尾，则赋值为 o
		head = (head + 1) % size;
		return element;
	}
}