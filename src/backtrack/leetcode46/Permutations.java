package backtrack.leetcode46;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * @author mqq
 * @date 2020-01-16
 */
public class Permutations {
	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> ans = solution.permute(new int[]{1, 2, 3});
		System.out.println("ans = " + ans.toString());
	}
}

/**
 * 回溯（递归）
 * 思路：
 * 1、每个数字使用过在后面的递归中就不可再被使用
 * 2、使用 used 记住已使用过的数字
 * 3、当 list.size == nums.length 时，满足一个组合
 * 4、注意 ans.add(new ArrayList<>(list)); 要使用 new ArrayList<>() 包裹组合，因为 list 是内存地址，会被 list.remove(list.size-1) 删除掉
 */
class Solution {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		helper(ans, new ArrayList<>(), new boolean[nums.length], nums);
		return ans;
	}

	private void helper(List<List<Integer>> ans, List<Integer> list, boolean[] used, int[] nums) {
		if (list.size() == nums.length) {
			ans.add(new ArrayList<>(list));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			list.add(nums[i]);
			helper(ans, list, used, nums);
			used[i] = false;
			list.remove(list.size() - 1);
		}
	}
}