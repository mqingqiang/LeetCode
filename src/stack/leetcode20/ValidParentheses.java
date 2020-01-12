package stack.leetcode20;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class ValidParentheses {
	public static void main(String[] args) {
		Solution solution = new StackSolution();
		boolean valid = solution.isValid("(]");
		System.out.println("valid = " + valid);
	}
}

/**
 * Brute Force
 * 思路：
 * 1、判断字符串中是否包含 ()、{}、[] 中的一个，包含则替换
 * 2、不断地替换 ()、{}、[]，知道字符串中不包含这些符号对
 * 3、替换后字符串为空，则为 true，否则返回 false
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 */
class Solution {
	public boolean isValid(String s) {
		while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
			s = s.replace("()", "").replace("{}", "").replace("[]", "");
		}
		return s.length() == 0;
	}
}

/**
 * 栈
 * 思路：
 * 1、维护一个栈，当遇到左括号（包括“({[}”三种），把对应的右括号推入栈
 * 2、若当前符号与栈顶元素相等，则移除栈顶元素
 * 3、若不相等，则先判断是否为左括号，若不是，则直接返回 false
 * 4、若是，则把该左括号对应的右括号入栈
 * 5、遍历完成，判断栈是否为空，若为空则返回 true，否则返回 false
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class StackSolution extends Solution {
	@Override
	public boolean isValid(String s) {
		Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');
		Deque<Character> stack = new ArrayDeque<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (Objects.equals(stack.peekFirst(), chars[i])) {
				stack.removeFirst();
			} else {
				if (!map.containsKey(chars[i])) {
					return false;
				}
				stack.addFirst(map.get(chars[i]));
			}
		}
		return stack.isEmpty();
	}
}