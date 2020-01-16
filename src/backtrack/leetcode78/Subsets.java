package backtrack.leetcode78;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * @author mqq
 * @date 2020-01-16
 */
public class Subsets {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = {1, 2, 3};
		List<List<Integer>> ans = solution.subsets(nums);
		System.out.println("ans = " + ans.toString());
	}
}

/**
 * 回溯（递归）
 * 思路：
 * 1、画出决策树
 * 2、每个字符决策选择与不选择，构成不同的组合
 * 3、真子集已选择的元素不再继续选择，i + 1 控制下一个选择区间
 */
class Solution {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		helper(ans, new ArrayList<>(), 0, nums);
		return ans;
	}

	private void helper(List<List<Integer>> ans, List<Integer> list, int start, int[] nums) {
		ans.add(new ArrayList<>(list));
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			helper(ans, list, i + 1, nums);
			list.remove(list.size() - 1);
		}
	}
}
