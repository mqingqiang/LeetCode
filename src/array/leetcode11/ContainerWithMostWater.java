package array.leetcode11;

/**
 * 11. 盛最多水的容器
 *
 * @author mqq
 * @date 2020-01-06
 */
public class ContainerWithMostWater {

}

/**
 * 暴力法
 * 思路：2 层循环遍历 height，计算每两个区间的面积，求出最大面积。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
class Solution {
	public int maxArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				maxArea = Math.max((j - i) * Math.min(height[i], height[j]), maxArea);
			}
		}
		return maxArea;
	}
}

/**
 * 双指针法
 * 思路：
 * 1、定义两个指针 left 和 right，并且处于左右边界
 * 2、比较两个指针柱子的高度，计算面积后移动较小者，往中间逐渐收敛，直到两个指针相遇
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class DoublePointerSolution extends Solution {
	@Override
	public int maxArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int left = 0;
		int right = height.length - 1;
		int maxArea = 0;
		while (left < right) {
			int minHeight = height[left] < height[right] ? height[left++] : height[right--];
			maxArea = Math.max((right - left + 1) * minHeight, maxArea);
		}
		return maxArea;
	}
}