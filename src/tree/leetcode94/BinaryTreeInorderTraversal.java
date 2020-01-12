package tree.leetcode94;

import java.util.ArrayList;
import java.util.List;

import common.datastruct.TreeNode;

/**
 * 94. 二叉树的中序遍历
 *
 * @author mqq
 * @date 2020-01-12
 */
public class BinaryTreeInorderTraversal {

}

/**
 * 递归
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		helper(root, result);
		return result;
	}

	private void helper(TreeNode root, List<Integer> result) {
		if (root != null) {
			if (root.left != null) {
				helper(root.left, result);
			}
			result.add(root.val);
			if (root.right != null) {
				helper(root.right, result);
			}
		}
	}
}