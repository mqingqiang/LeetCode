import common.datastruct.ArrayQueue;
import common.datastruct.Queue;

/**
 * @author mqq
 * @date 2020-01-06
 */
public class Test {
	public static void main(String[] args) {
		Queue queue = new ArrayQueue(3);
		System.out.println(queue.enQueue(1));
		System.out.println(queue.enQueue(2));
		System.out.println(queue.enQueue(3));
		System.out.println("queue = " + queue.deQueue());
		System.out.println(queue.enQueue(4));
		System.out.println("queue = " + queue.deQueue());
		System.out.println("queue = " + queue.deQueue());
		System.out.println("queue = " + queue.deQueue());
		System.out.println("queue = " + queue.deQueue());
	}
}