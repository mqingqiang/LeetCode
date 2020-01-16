package backtrack.leetcode77;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * https://leetcode-cn.com/problems/combinations/
 * 好的题解：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 * 全球站第一条评论：
 * https://leetcode.com/problems/combinations/discuss/27002/Backtracking-Solution-Java
 *
 * @author mqq
 * @date 2020-01-16
 */
public class Combinations {
	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> combine = solution.combine(5, 3);
		System.out.println("combine = " + combine.toString());
	}
}

/**
 * 回溯（递归）
 * 思路：
 * 1、start 表示上一个选择的数是什么，list 存储某一种组合，res 用于存储所有组合
 * 2、此题的关键是点 i 最大值的确定，这是剪枝的关键
 * 3、假设 n = 5，k = 3
 * 4、当 1 已选时，下一个肯定不能选 5，因为 5 后面没有数字了，选 5 的话数量就达不到 k 了，所以 i 的最大值为 4，因为 [1,4,5] 可以组合
 * 5、由 4 可以剪枝 max(i) = n - (k - list.size) + 1，即 i 的最大值为 max(i)
 */
class Solution {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (k < 1 || n < k) {
			return res;
		}
		helper(n, k, 1, new ArrayList<>(), res);
		return res;
	}

	private void helper(int n, int k, int start, List<Integer> list, List<List<Integer>> res) {
		// terminator
		if (k == 0) {
			res.add(new ArrayList<>(list));
			return;
		}
		// process current logic
		for (int i = start; i <= n - k + 1; i++) {
			list.add(i);
			// drill down
			helper(n, k - 1, i + 1, list, res);
			list.remove(list.size() - 1);
		}
	}
}