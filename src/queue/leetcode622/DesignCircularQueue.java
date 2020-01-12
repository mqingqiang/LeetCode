package queue.leetcode622;

/**
 * 622. 设计循环队列
 * https://leetcode-cn.com/problems/design-circular-queue/
 *
 * @author mqq
 * @date 2020-01-12
 */
public class DesignCircularQueue {
	public static void main(String[] args) {
		// 设置长度为 3
		MyCircularQueue circularQueue = new MyCircularQueue(3);
		// 返回 true
		System.out.println(circularQueue.enQueue(1));
		// 返回 true
		System.out.println(circularQueue.enQueue(2));
		// 返回 true
		System.out.println(circularQueue.enQueue(3));
		// 返回 false，队列已满
		System.out.println(circularQueue.enQueue(4));
		// 返回 3
		System.out.println(circularQueue.Rear());
		// 返回 true
		System.out.println(circularQueue.isFull());
		// 返回 true
		System.out.println(circularQueue.deQueue());
		// 返回 true
		System.out.println(circularQueue.enQueue(4));
		// 返回 4
		System.out.println(circularQueue.Rear());
	}
}

/**
 * 循环队列
 * 思路：
 * 1、其他设计与普通队列一致，主要是队空和队满的判断
 * 2、循环队列队空：head == tail
 * 3、循环队列队满：(tail + 1) % size == head
 * 4、插入元素后计算队尾指针位置：tail = (tail + 1) % size;
 * 5、获取队尾元素：int index = (tail - 1 + size) % size; elements[index]
 */
class MyCircularQueue {

	private int[] elements;

	private int size;

	private int head;

	private int tail;

	/**
	 * Initialize your data structure here. Set the size of the queue to be k.
	 */
	public MyCircularQueue(int k) {
		size = k + 1;
		elements = new int[size];
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation is successful.
	 */
	public boolean enQueue(int value) {
		if (isFull()) {
			return false;
		}
		elements[tail] = value;
		tail = (tail + 1) % size;
		return true;
	}

	/**
	 * Delete an element from the circular queue. Return true if the operation is successful.
	 */
	public boolean deQueue() {
		if (isEmpty()) {
			return false;
		}
		head = (head + 1) % size;
		return true;
	}

	/**
	 * Get the front item from the queue.
	 */
	public int Front() {
		if (head == tail) {
			return -1;
		}
		return elements[head];
	}

	/**
	 * Get the last item from the queue.
	 */
	public int Rear() {
		if (isEmpty()) {
			return -1;
		}
		int index = (tail - 1 + size) % size;
		return elements[index];
	}

	/**
	 * Checks whether the circular queue is empty or not.
	 */
	public boolean isEmpty() {
		return head == tail;
	}

	/**
	 * Checks whether the circular queue is full or not.
	 */
	public boolean isFull() {
		return (tail + 1) % size == head;
	}
}