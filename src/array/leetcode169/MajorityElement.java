package array.leetcode169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 *
 * @author mqq
 * @date 2020-01-17
 */
public class MajorityElement {

}

/**
 * 哈希法
 * 思路：
 * 1、遍历数据，把每个元素的计数放到 HashMap 中，key 为元素，value 为元素个数
 * 2、在遍历过程中判断元素个数是否大于 n/2，是则直接返回，否则继续遍历其他元素
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public int majorityElement(int[] nums) {
		int major = nums[0];
		Map<Integer, Integer> counts = new HashMap<>();
		for (int num : nums) {
			if (!counts.containsKey(num)) {
				counts.put(num, 1);
				continue;
			}
			int count = counts.get(num) + 1;
			if (count > (nums.length / 2)) {
				major = num;
				break;
			}
			counts.put(num, count);
		}
		return major;
	}
}

/**
 * 排序法
 * 思路：
 * 1、由于必定存在多数元素，并且出现次数必定大于 n/2，那么数组排序后，下标为 n/2 的元素必定就是多数元素了
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 */
class SortSolution extends Solution {
	@Override
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}
}

/**
 * 摩尔投票算法（Boyer-Moore 投票算法）
 * 思路：
 * 1、从头开始随机选择一个数为众数（此数不一定为众数），如 major = nums[0]
 * 2、遍历数组对众数进行计数，遇到选出来的众数加一，非众数减一，判断计数器是否为 0
 * 3、为 0，则重置众数，选当前元素的下一个元素为众数，重复步骤 2
 * 4、遍历结束，count > 0，则 major 值为众数
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class MooreVotingSolution extends Solution {
	@Override
	public int majorityElement(int[] nums) {
		int major = 0;
		int count = 0;
		for (int num : nums) {
			if (count == 0) {
				major = num;
			}
			count += (major == num) ? 1 : -1;
		}
		return major;
	}
}