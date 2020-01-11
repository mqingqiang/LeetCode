package array.leetcode189;

import java.util.Arrays;

/**
 * 189. 旋转数组
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class RotateArray {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7};
		Solution solution = new ReverseSolution();
		solution.rotate(nums, 3);
		System.out.println(Arrays.toString(nums));
		nums = new int[]{1, 2, 3, 4, 5, 6, 7};
		solution.rotate(nums, 10);
		System.out.println(Arrays.toString(nums));
	}
}

/**
 * Brute Force
 * 思路：
 * 1、旋转 k 次，每次旋转一个元素
 * 时间复杂度：O(k*n)
 * 空间复杂度：O(1)
 */
class Solution {
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}
		if (k >= nums.length) {
			k %= nums.length;
		}
		for (int i = 0; i < k; i++) {
			int tail = nums[nums.length - 1];
			System.arraycopy(nums, 0, nums, 1, nums.length - 1);
			nums[0] = tail;
		}
	}
}

/**
 * 使用额外的数组（不满足题目要求，但是方法不错，时间复杂度从暴力法的 O(k*n) 降到了，O(2n)是一种空间换时间的优化思想）
 * 思路：
 * 1、新建一个跟原数组长度一样的数组
 * 2、把原数组 nums 中的第 i 个元素放到新数组的下标为 (i+k)%nums.length 的位置
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class ExtraArraySolution extends Solution {
	@Override
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int[] newNums = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			newNums[(i + k) % nums.length] = nums[i];
		}
		System.arraycopy(newNums, 0, nums, 0, newNums.length);
	}
}

/**
 * 三次反转
 * 思路：
 * 原始数组                  : 1 2 3 4 5 6 7
 * 反转所有数字后            : 7 6 5 4 3 2 1
 * 反转前 k 个数字后         : 5 6 7 4 3 2 1
 * 反转后 n-k 个数字后       : 5 6 7 1 2 3 4 --> 结果
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class ReverseSolution extends Solution {
	@Override
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}
		// 当 k >= nums.length 时，只需要移动 k%nums.lengt 次即可
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}
	}
}