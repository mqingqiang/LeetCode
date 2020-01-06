package array.leetcode283;

/**
 * 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/submissions/
 *
 * @author mqq
 * @date 2020-01-06
 */
public class MoveZero {

}

/**
 * 双指针法
 * 思路：
 * 1. 记录非 0 元素的下标 count，当遇到元素非 0 时，判断当前下标与 count 是否相等，相等则无需赋值只记录 count 即可；不相等则把该非 0 元素
 * 移动到下标为 count 位置，并记录 count。
 * 2. 第一次遍历结束后，再次遍历把下标 count 之后的位置设为 0 即可。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class Solution {
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		// 数组长度
		int length = nums.length;
		// 非 0 元素的下标
		int count = 0;
		for (int i = 0; i < length; i++) {
			if (nums[i] != 0) {
				if (i != count) {
					nums[count] = nums[i];
				}
				count++;
			}
		}
		// count 下标之后的元素全部设为 0
		while (count < length) {
			nums[count++] = 0;
		}
	}
}

/**
 * 快慢指针
 * 思路：
 * 1、定义 fast 和 slow 两个指针，遍历数组，fast 每次向前走，slow 记录非 0 元素位置
 * 2、fast 指针遇到非 0 元素，赋值到 slow 指针位置，并把 fast 指针当前元素设为 0
 * 3、快慢指针和双指针法其实是同一种方法，只是快慢指针把赋值 0 的操作放到了同一次循环中实现，
 * 快慢指针其实会比双指针法多做几次赋值 0 的操作，但是不需要重新遍历数组
 */
class FastSlowPointerSolution extends Solution {
	@Override
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		// 快慢指针
		int fast = 0;
		int slow = 0;
		for (int num : nums) {
			if (num != 0) {
				if (slow != fast) {
					nums[slow] = num;
					nums[fast] = 0;
				}
				slow++;
			}
			fast++;
		}
	}
}