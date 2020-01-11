package stack.leetcode84;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class LargestRectangleInHistogram {
	public static void main(String[] args) {
		int[] heights = {2, 1, 5, 6, 2, 3};
		// int[] heights = {1};
		Solution solution = new StackSolution();
		int maxArea = solution.largestRectangleArea(heights);
		System.out.println("maxArea = " + maxArea);
	}
}

/**
 * Brute Force（LeetCode 超出时间限制）
 * 思路：
 * 1、枚举所有两两柱子之间形成的矩形面积，该矩形高为他们之间的最小高度，宽为他们之间的距离
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(1)
 */
class Solution {
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			for (int j = i; j < heights.length; j++) {
				int minHeight = Integer.MAX_VALUE;
				for (int k = i; k <= j; k++) {
					minHeight = Math.min(minHeight, heights[k]);
				}
				maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
			}
		}
		return maxArea;
	}
}

/**
 * Brute Froce 优化
 * 思路：
 * 1、两层循环遍历，求出两个柱子之间的最小高度
 * 2、update 最大面积
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
class BruteForceSolution extends Solution {
	@Override
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			int minHeight = Integer.MAX_VALUE;
			for (int j = i; j < heights.length; j++) {
				minHeight = Math.min(minHeight, heights[j]);
				maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
			}
		}
		return maxArea;
	}
}

/**
 * 栈
 * 思路：
 * 1、维护一个栈，为了处理方便，先把 -1 入栈
 * 2、遍历数组，不断把下标入栈，直到遇到比栈顶元素小的元素
 * 3、弹出元素，计算矩形面积。面积=(i−stack[top−1]−1)×a[stack[top]]
 * 4、update maxArea，并继续拿该元素与栈顶元素对比，若该元素还是比栈顶元素小，重复步骤 3
 * 5、处理完，若栈顶元素不为 -1，即还有元素未取出，则一次弹出栈顶元素，计算矩形面积
 * 注意：maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1)); 这句代码中 stack.pop() 一定要在 stack.peek() 前面，
 * maxArea = Math.max(maxArea, (heights.length - stack.peek() - 1) * heights[stack.pop()]); 这样写算出来的最大面积是有可能不准确的。因为 stack.pop() 弹出
 * 的是当前元素，弹出之后执行 stack.peek() 就是当前元素的下一个元素了，这才是正确的。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class StackSolution extends Solution {
	@Override
	public int largestRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
				maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
			}
			stack.push(i);
		}
		while (stack.peek() != -1) {
			maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
		}
		return maxArea;
	}
}