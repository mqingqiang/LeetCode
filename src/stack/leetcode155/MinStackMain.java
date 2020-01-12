package stack.leetcode155;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 * <p>
 * 解题方法：
 * 1、两个栈，数据栈 dataStack + 最小栈 minStack
 * 2、一个栈，min 存储所有元素最小值，入栈时 x < min，把 min 也入栈并更新 min；出栈 x 时，top = min，则把 x 下一个元素 y 也出栈，并更新 min = y
 * 3、一个栈，min 存储所有元素的最小值，但是入栈时不保存原值，而是保存原值与 min 的差值（暂未实现）
 * 4、自己实现的链表栈，增加一个字段 min，每次加入新节点时，新节点的 min 为当前元素 x 和链表头结点 head 的 min 中的较小者
 *
 * @author mqq
 * @date 2020-01-12
 */
public class MinStackMain {
	public static void main(String[] args) {
		MinStack minStack = new OneMinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-1);
		int min = minStack.getMin();
		int top = minStack.top();
		minStack.pop();
		int min1 = minStack.getMin();
		System.out.println("min = " + min);
		System.out.println("top = " + top);
		System.out.println("min1 = " + min1);
	}
}

/**
 * 两个栈方式，一个栈 dataStack 存正常入栈出栈的值，一个最小栈 minStack 存最小值，minStack 栈顶为当前所有元素的最小值。
 * dataStack 正常入栈出栈即可，无需特殊操作。以下针对最小栈 minStack 的操作思路。
 * 思路：
 * 1、入栈时，入栈元素 x <= minStack 栈顶元素，把 x 压入最小栈 minStack。
 * 为了减少入栈时 minStack 是否为空的判断，构造函数为 minStack 赋初值 Integer.MAX_VALUE，此时第一个元素入栈必定会小于栈顶元素，且 minStack 永远不会为空
 * 2、若入栈元素 x > minStack 栈顶元素，则最小栈不操作
 * 3、出栈时，若 dataStack 栈顶元素等于 minStack 栈顶元素，则把 minStack 栈顶元素也出栈，否则 minStack 不操作
 * 4、getMin() 获取最小值时，由于 minStack 初始化会赋初值，有可能 dataStack 为空但是 minStack 不为空的情况，此时需要同时判断 dataStack 是否为空
 * <p>
 * 参考：https://leetcode-cn.com/problems/min-stack/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-38/
 */
class MinStack {

	/**
	 * 数组栈，存储入栈的元素
	 */
	private Deque<Integer> dataStack;

	/**
	 * 最小栈，存储栈元素的最小值
	 */
	private Deque<Integer> minStack;

	/**
	 * initialize your data structure here.
	 */
	public MinStack() {
		dataStack = new LinkedList<>();
		minStack = new LinkedList<>();
		// 最小栈赋初值，减少 push 时边界条件检查
		minStack.addFirst(Integer.MAX_VALUE);
	}

	public void push(int x) {
		dataStack.addFirst(x);
		if (x <= minStack.peekFirst()) {
			minStack.addFirst(x);
		}
	}

	public void pop() {
		Integer top = dataStack.removeFirst();
		if (top.equals(minStack.peekFirst())) {
			minStack.removeFirst();
		}
	}

	public int top() {
		if (!dataStack.isEmpty()) {
			return dataStack.peekFirst();
		}
		return 0;
	}

	public int getMin() {
		if (!dataStack.isEmpty() && !minStack.isEmpty()) {
			return minStack.peekFirst();
		}
		return 0;
	}
}

/**
 * 一个栈
 * 思路：
 * 1、使用变量 min 保存所有元素的最小值，min 赋初值 Integer.MAX_VALUE，减少入栈时边界条件检测
 * 2、入栈时，当入栈元素 x > min 时，元素入栈不更新 min 值
 * 3、入栈时，当入栈元素 x <= min 时，先把当前 min 值入栈并更新 min 值为 x，再把 x 入栈
 * 4、出栈时，若栈顶元素不等于 min，则直接出栈，min不更新
 * 5、出栈时，若栈顶元素等于 min，则元素 x 出栈后，还需把下一个元素 y 也出栈，并更新 min 为 y
 */
class OneMinStack extends MinStack {

	/**
	 * 数据栈
	 */
	private Deque<Integer> dataStack;

	/**
	 * 最小值
	 */
	private int min = Integer.MAX_VALUE;

	public OneMinStack() {
		dataStack = new LinkedList<>();
	}

	@Override
	public void push(int x) {
		if (x <= min) {
			dataStack.addFirst(min);
			min = x;
		}
		dataStack.addFirst(x);
	}

	@Override
	public void pop() {
		Integer top = dataStack.removeFirst();
		if (top == min) {
			min = dataStack.removeFirst();
		}
	}

	@Override
	public int top() {
		if (dataStack.isEmpty()) {
			return 0;
		}
		return dataStack.peekFirst();
	}

	@Override
	public int getMin() {
		return min;
	}
}

/**
 * 自己实现一个链表栈
 * 思路：
 * 1、定义链表节点，节点包含节点值、最小值、next指针，其中最小值保存的是当前指针之后的所有值的最小值
 * 2、入栈时，用入栈元素 x 构造节点 newNode，并设置 min 为 Math.min(x, head.min)，并把 newNode.next 指向 head，
 * 此时 newNode 为栈顶节点，所以重新设置 head 为栈顶节点 head = newNode。
 * 3、出栈则正常出栈即可，这样一来，head.min 永远为当前链表（栈）的最小值
 */
class LinkedMinStack extends MinStack {
	/**
	 * 链式最小栈节点
	 */
	static class Node {
		// 节点值
		int value;
		// 最小值
		int min;
		// 下一个节点
		Node next;

		Node(int x, int min) {
			this.value = x;
			this.min = min;
		}
	}

	/**
	 * 栈底层链表
	 */
	Node head = null;

	@Override
	public void push(int x) {
		if (head == null) {
			head = new Node(x, x);
		} else {
			Node newNode = new Node(x, Math.min(x, head.min));
			newNode.next = head;
			head = newNode;
		}
	}

	@Override
	public void pop() {
		if (head != null) {
			head = head.next;
		}
	}

	@Override
	public int top() {
		if (head != null) {
			return head.value;
		}
		return 0;
	}

	@Override
	public int getMin() {
		if (head != null) {
			return head.min;
		}
		return 0;
	}
}