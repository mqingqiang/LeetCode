package array.leetcode42;

/**
 * 42. 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * @author mqq
 * @date 2020-01-12
 */
public class TrappingRainWater {
	public static void main(String[] args) {
		int[] height = {0, 1, 8, 2, 0, 0, 7, 3, 1};
		Solution solution1 = new Solution();
		Solution solution2 = new MemorySolution();
		Solution solution3 = new DoublePointerSolution();
		int trap1 = solution1.trap(height);
		int trap2 = solution2.trap(height);
		int trap3 = solution3.trap(height);
		System.out.println("trap = " + trap1);
		System.out.println("trap = " + trap2);
		System.out.println("trap = " + trap3);
	}
}

/**
 * Brute Force
 * 思路：
 * 1、寻找每根柱子的左边最大高度[0..i]和右边最大高度[i..height.length-1]
 * 2、用左右两边柱子较小者减当前柱子高度，累加到雨水面积中
 * 3、这里有个技巧，leftHeight 和 rightHeight 初始值设置为当前柱子高度 height[i]，不要设置为 0，可以省去计算面积时的一些特殊情况判断
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
class Solution {
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		int waterArea = 0;
		for (int i = 0; i < height.length; i++) {
			int leftHeight = height[i];
			int rightHeight = height[i];
			// 左边最高的柱子
			for (int j = 0; j < i; j++) {
				leftHeight = Math.max(leftHeight, height[j]);
			}
			// 右边最高的柱子
			for (int j = i + 1; j < height.length; j++) {
				rightHeight = Math.max(rightHeight, height[j]);
			}
			waterArea += Math.min(leftHeight, rightHeight) - height[i];
		}
		return waterArea;
	}
}

/**
 * 记忆法
 * 思路：
 * 1、暴力法中每根柱子都要重复计算 leftMax 和 rightMax，我们可以使用一个数组提前计算好每根柱子左边和右边的最大高度，后续就不需要重复遍历计算
 * 2、计算每根柱子的左边最大高度，只需要拿柱子高度与左边那根柱子的最大高度对比即可，较大者即为当前柱子最大高度。
 * 同理，柱子的右边最大高度只需要与右边那根柱子的最大高度对比即可，较大者为当前柱子最大高度
 * 时间复杂度：O(n)，实际为 O(3n)
 * 空间复杂度：O(n)，实际为 O(2n)
 */
class MemorySolution extends Solution {
	@Override
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		int length = height.length;
		// 雨水面积
		int waterArea = 0;
		// 记录左右最高柱子
		int[] leftMax = new int[length];
		int[] rightMax = new int[length];
		// 特殊处理，第一根柱子的左边最大高度为柱子高度；最后一根柱子的右边最大高度为柱子高度
		leftMax[0] = height[0];
		rightMax[length - 1] = height[length - 1];
		// 计算左边柱子的最高高度
		for (int i = 1; i < length; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}
		// 计算右边柱子的最高高度
		for (int i = length - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}
		// 计算雨水面积
		for (int i = 1; i < length; i++) {
			waterArea += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return waterArea;
	}
}

/**
 * 双指针法
 * 思路：
 * 1、定义两个指针 left、right，left 向右移动，right 向左移动，直到 left 和 right 指针相遇
 * 2、使用 leftMax 记录 [0..left] 的最大高度，rightMax 记录 [right..height.length] 最大高度
 * 3、当 height[left] < height[right] 时，计算 left 这根柱子的雨水面积，雨水面积=leftMax - height[left]。
 * 为什么不用看右边的柱子呢？因为 height[left] < height[right]，左右 left 左边的左右柱子高度均会比 height[right] 低，
 * 如果 left 左边有比 height[right] 高的柱子，那么 left 指针不会移动到当前位置，因为此时的 right < leftMax，
 * right 一定会比 leftMax 那根柱子先移动。
 * 4、当 height[left] >= height[right] 时，计算 right 这根柱子的雨水面积，雨水面积=rightMax - height[right]。
 * <p>
 * 注意：
 * 此处难理解的是，当 height[left] < height[right] 时，为什么 left 左边的柱子不会出现比 height[right] 高的柱子；
 * 当 height[left] >= height[right] 时，为什么 right 右边的柱子不会出现比 height[left] 高的柱子。
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class DoublePointerSolution extends Solution {
	@Override
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		int waterArea = 0;
		int left = 0;
		int right = height.length - 1;
		int leftMax = height[left];
		int rightMax = height[right];
		while (left < right) {
			if (height[left] < height[right]) {
				leftMax = Math.max(leftMax, height[left]);
				waterArea += leftMax - height[left];
				left++;
			} else {
				rightMax = Math.max(rightMax, height[right]);
				waterArea += rightMax - height[right];
				right--;
			}
		}
		return waterArea;
	}
}