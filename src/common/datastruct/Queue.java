package common.datastruct;

/**
 * 队列接口
 *
 * @author mqq
 * @date 2020-01-12
 */
public interface Queue {

	/**
	 * 入队
	 *
	 * @param value 入队元素
	 * @return true：入队成功 false：入队失败
	 */
	boolean enQueue(int value);

	/**
	 * 出队
	 *
	 * @return 出队元素
	 */
	int deQueue();

}
