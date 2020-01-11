package array.leetcode1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author mqq
 * @date 2020-01-11
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] nums = {3, 2, 4};
		int target = 6;
		Solution solution = new HashMapSolution();
		System.out.println(Arrays.toString(nums));
		int[] twoSum = solution.twoSum(nums, target);
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(twoSum));
	}
}

/**
 * Brute Force
 * 思路：
 * 1、两层循环遍历数组 nums，判断 nums[i] + nums[j] 是否等于 target
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
class Solution {
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}
}

/**
 * 哈希法
 * 思路：
 * 1、使用 visited 存储已遍历元素和下标，遍历每次判断元素是否在 HashMap 中存在
 * 2、若存在，直接返回 new int[]{visited.get(nums[i]), i}
 * 3、若不存在，把该元素和下标放到 visited 中
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class HashMapSolution extends Solution {
	@Override
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> visited = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (visited.containsKey(nums[i])) {
				result[0] = visited.get(nums[i]);
				result[1] = i;
				break;
			}
			visited.put(target - nums[i], i);
		}
		return result;
	}
}