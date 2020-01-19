package tree.leetcode102;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import common.datastruct.TreeNode;

/**
 * 102. 二叉树的层次遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * @author mqq
 * @date 2020-01-19
 */
public class BinaryTreeLevelOrderTraversal {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		Solution solution = new DfsSolution();
		List<List<Integer>> lists = solution.levelOrder(root);
		System.out.println(lists.toString());
	}
}

/**
 * 广度优先搜索（BFS）
 * 思路：
 * 1、借助队列实现 BFS，每次循环队列内存储当前层（level）的节点
 * 2、每次依次从队列取出节点，并从左到右依次添加节点，进入下一次循环
 * 3、待队列为 Empty 时，即得到结果
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();
		if (root == null) {
			return ans;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		deque.addLast(root);
		while (!deque.isEmpty()) {
			List<Integer> subList = new LinkedList<>();
			int count = deque.size();
			for (int i = 0; i < count; i++) {
				TreeNode currNode = deque.removeFirst();
				subList.add(currNode.val);
				if (currNode.left != null) {
					deque.addLast(currNode.left);
				}
				if (currNode.right != null) {
					deque.addLast(currNode.right);
				}
			}
			ans.add(subList);
		}
		return ans;
	}
}

/**
 * 深度优先搜索（DFS）
 * 思路：
 * 1、把当前层节点放入结果集，递归遍历每个结点，下钻时通过 level 标记层
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class DfsSolution extends Solution {
	@Override
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();
		if (root == null) {
			return ans;
		}
		helper(ans, 0, root);
		return ans;
	}

	private void helper(List<List<Integer>> ans, int level, TreeNode root) {
		if (root == null) {
			return;
		}
		if (ans.size() >= level) {
			ans.add(new LinkedList<>());
		}
		ans.get(level).add(root.val);
		helper(ans, level + 1, root.left);
		helper(ans, level + 1, root.right);
	}
}