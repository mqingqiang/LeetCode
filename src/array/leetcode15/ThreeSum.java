package array.leetcode15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * 15. 三数之和
 *
 * @author mqq
 * @date 2020-01-06
 */
public class ThreeSum {
	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.threeSum(new int[]{0, 0, 0, 0});
	}
}

/**
 * Brute Force
 * 暴力破解方法提交会超时。
 * 1、穷举每一种情况，判断 nums[i] + nums[j] + nums[k] == 0，则判断是否需要添加
 * 2、去重判断（我这里使用 java.util.Arrays.ArrayList）
 * ① 使用 Arrays.asList，返回的是 java.util.Arrays.ArrayList，使用 containsAll() 可判断要添加的 List 是否全在结果集中，[0, 0, 0] 情况需特殊处理
 * ② 使用 java.util.ArrayList，则无法通过 containsAll() 判断结果集中是否存在，需要先排序再使用该方法
 * 区别：
 * 对于 [1, 2, 3] 和 [1, 2, 3]
 * java.util.Arrays.ArrayList.containsAll() 返回 true
 * java.util.ArrayList.containsAll() 返回 true
 * 对于 [1, 2, 3] 和 [2, 1, 3]
 * java.util.Arrays.ArrayList.containsAll() 返回 true
 * java.util.ArrayList.containsAll() 返回 false
 * 对于 [1, 0, 1] 和 [0, 0, 0]
 * java.util.Arrays.ArrayList.containsAll() 返回 true（重点是这里，所以需要对 [0, 0, 0] 进行特殊处理）
 * java.util.ArrayList.containsAll() 返回 false
 * <p>
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n)
 */
class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length < 3) {
			return result;
		}
		// 特殊处理 [0, 0, 0] 的情况，判断 [0, 0, 0] 是否已被添加到结果集中
		boolean addedAllZeroes = false;
		for (int k = 0; k < nums.length - 2; k++) {
			for (int i = k + 1; i < nums.length - 1; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						List<Integer> temp = Arrays.asList(nums[k], nums[i], nums[j]);
						boolean repeat = false;
						// 若遍历结果全为 0，则判断 [0, 0, 0] 是否已添加
						if (nums[k] == 0 && nums[i] == 0 && nums[j] == 0) {
							if (!addedAllZeroes) {
								addedAllZeroes = true;
								result.add(temp);
							}
							continue;
						}
						for (List<Integer> list : result) {
							if (list.containsAll(temp)) {
								repeat = true;
								break;
							}
						}
						if (!repeat) {
							result.add(temp);
						}
					}
				}
			}
		}
		return result;
	}
}

/**
 * 哈希法
 */
class HashSolution extends Solution {
	@Override
	public List<List<Integer>> threeSum(int[] nums) {
		return super.threeSum(nums);
	}
}

/**
 * 双指针法
 * 思路：
 * 1、先对数组进行排序
 * 2、固定左边的一个指针 k，判断 nums[k] 是否大于 0，大于 0 则直接退出，因为元素已排序，最左边的 nums[k] 大于 0 则不可能存在三个数相加等于 0 的情况。
 * 3、nums[k] < 0 则用 left，right 两个指针遍历 k 右边的元素，当和大于 0 则 right 指针往左移，和小于 0 则 left 往右移，直到两个指针相遇
 * 4、之后 k 往右移，重复步骤 3，直到 k 遍历到末尾
 * 注意：第 3 步 left、right 指针和等于 0 可能会遇到重复值，如 [-4, -1, -1, 0, 1, 2]，此处两个 -1 为重复值，需要跳过。k 的移动也会有重复值，同理跳过。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
class DoublePointerSolution extends Solution {
	@Override
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return result;
		}
		// 对数组进行排序
		Arrays.sort(nums);
		for (int k = 0; k < nums.length - 2; k++) {
			if (nums[k] > 0) {
				break;
			}
			if (k > 0 && nums[k] == nums[k - 1]) {
				continue;
			}
			// 左右指针
			int left = k + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[k] + nums[left] + nums[right];
				if (sum < 0) {
					left++;
				} else if (sum > 0) {
					right--;
				} else {
					result.add(Arrays.asList(nums[k], nums[left], nums[right]));
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}
					left++;
					right--;
				}
			}
		}
		return result;
	}
}