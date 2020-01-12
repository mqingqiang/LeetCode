package common.datastruct;


/**
 * 顺序队列
 *
 * @author mqq
 * @date 2020-01-12
 */
public class ArrayQueue implements Queue {

	// 底层数组
	private int[] elements;

	// 数组大小
	private int size;

	// 队头指针
	private int head = 0;

	// 队尾指针
	private int tail = 0;

	public ArrayQueue(int capacity) {
		this.elements = new int[capacity];
		size = capacity;
	}

	@Override
	public boolean enQueue(int value) {
		// 队满
		if (tail == size) {
			if (head == 0) {
				return false;
			}
			System.arraycopy(elements, head, elements, head - 1, size - head);
			tail -= head;
			head = 0;
		}
		elements[tail] = value;
		tail++;
		return true;
	}

	@Override
	public int deQueue() {
		// 队空
		if (head == tail) {
			return -1;
		}
		return elements[head++];
	}
}