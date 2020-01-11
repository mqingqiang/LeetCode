package array.leetcode239;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class SlidingWindowMaximum {
	public static void main(String[] args) {
		int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
		// int[] nums = {};
		int k = 3;
		Solution solution = new Solution();
		int[] maxSlidingWindow = solution.maxSlidingWindow(nums, k);
		System.out.println("maxSlidingWindow = " + Arrays.toString(maxSlidingWindow));
	}
}

/**
 * Brute Force
 * 思路：
 * 1、遍历每个滑动窗口，计算最大值，共有 (nums.length - k + 1) 个滑动窗口
 * 时间复杂度：O(n*k)
 * 空间复杂度：O(n)
 */
class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (k * nums.length == 0) {
			return new int[0];
		}
		int[] maxNums = new int[nums.length - k + 1];
		for (int i = 0; i <= nums.length - k; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = i; j < k + i; j++) {
				max = Math.max(max, nums[j]);
			}
			maxNums[i] = max;
		}
		return maxNums;
	}
}

/**
 * 单调队列（双端队列）
 */
class DequeSolution extends Solution {
	@Override
	public int[] maxSlidingWindow(int[] nums, int k) {
		Deque<Integer> deque = new LinkedList<>();
		// TODO 单调队列暂时未完成，有些内容还没理解
		return null;
	}
}