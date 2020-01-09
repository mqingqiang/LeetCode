package common.util;

import java.util.HashMap;
import java.util.Map;

import common.datastruct.ListNode;

/**
 * 链表工具类
 *
 * @author mqq
 * @date 2019-12-29
 */
public class ListUtils {

	/**
	 * 打印链表，如果存在环，默认打印环深度为 3
	 *
	 * @param head 链表头结点
	 */
	public static void printList(ListNode head) {
		printList(head, 3);
	}

	/**
	 * 打印链表
	 *
	 * @param head  链表头结点
	 * @param depth 若存在环，需要打印的环深度
	 */
	public static void printList(ListNode head, int depth) {
		if (head == null) {
			System.out.print("head is NULL");
		}
		StringBuilder printStr = new StringBuilder();
		Map<ListNode, Integer> visited = new HashMap<>();
		boolean existCycle = false;

		printStr.append("[");
		do {
			printStr.append(head.val);
			if (visited.containsKey(head)) {
				int count = visited.get(head);
				if (count >= depth) {
					existCycle = true;
					break;
				}
				visited.put(head, count + 1);
			} else {
				visited.put(head, 1);
			}
			head = head.next;
			if (head != null) {
				printStr.append(", ");
			}
		} while (head != null);
		printStr.append("]");
		if (existCycle) {
			printStr.append("（").append("depth=").append(depth).append("）");
		}
		System.out.println(printStr.toString());
	}
}