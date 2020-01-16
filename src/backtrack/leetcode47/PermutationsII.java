package backtrack.leetcode47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 *
 * @author mqq
 * @date 2020-01-16
 */
public class PermutationsII {
	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> ans = solution.permuteUnique(new int[]{1, 1, 2});
		System.out.println("ans = " + ans.toString());
	}
}

/**
 * 回溯（递归）
 * 思路：
 * 1、该题与《46. 全排列》类似，是 46 题的变体
 * 2、先对 nums 进行排序
 * 3、除全排列判断外，本题的 nums 是一个可重复的数组，要返回不重复的全排列
 * 4、pre == nums[i] && !used[i - 1]，其中 pre 表示上一个元素。说明：与上一个元素相等并且未被使用过的数字被忽略
 * 5、其他逻辑与《46. 全排列》思路一模一样
 */
class Solution {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		Arrays.sort(nums);
		helper(ans, new ArrayList<>(), new boolean[nums.length], nums);
		return ans;
	}

	private void helper(List<List<Integer>> ans, ArrayList<Integer> list, boolean[] used, int[] nums) {

		if (list.size() == nums.length) {
			ans.add(new ArrayList<>(list));
			return;
		}
		int pre = nums[0] - 1;
		for (int i = 0; i < nums.length; i++) {
			if (used[i] || (pre == nums[i] && !used[i - 1])) {
				continue;
			}
			used[i] = true;
			list.add(nums[i]);
			helper(ans, list, used, nums);
			list.remove(list.size() - 1);
			used[i] = false;
			pre = nums[i];
		}
	}
}