package tree.leetcode515;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import common.datastruct.TreeNode;

/**
 * 515. 在每个树行中找最大值
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * @author mqq
 * @date 2020-01-19
 */
public class FindLargestValueInEachTreeRow {

}

/**
 * 广度优先搜索（BFS）
 * 思路：
 * 1、队列存储每层节点
 * 2、取每层节点比较值大小
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> ans = new LinkedList<>();
		if (root == null) {
			return ans;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		deque.addLast(root);
		while (!deque.isEmpty()) {
			int max = Integer.MIN_VALUE;
			int count = deque.size();
			for (int i = 0; i < count; i++) {
				TreeNode currNode = deque.removeFirst();
				max = Math.max(max, currNode.val);
				if (currNode.left != null) {
					deque.addLast(currNode.left);
				}
				if (currNode.right != null) {
					deque.addLast(currNode.right);
				}
			}
			ans.add(max);
		}
		return ans;
	}
}

/**
 * 深度优先搜索（DFS）
 */
class RecursionSolution extends Solution {
	@Override
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> ans = new LinkedList<>();
		if (root == null) {
			return ans;
		}
		helper(ans, 0, root);
		return ans;
	}

	private void helper(List<Integer> ans, int level, TreeNode root) {
		if (root == null) {
			return;
		}
		if (ans.size() >= level) {
			ans.add(Integer.MIN_VALUE);
		}
		ans.set(level, Math.max(ans.get(level), root.val));
		helper(ans, level + 1, root.left);
		helper(ans, level + 1, root.right);
	}
}