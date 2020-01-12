package tree.leetcode144;

import java.util.ArrayList;
import java.util.List;

import common.datastruct.TreeNode;

/**
 * 144. 二叉树的前序遍历
 *
 * @author mqq
 * @date 2020-01-12
 */
public class BinaryTreePreorderTraversal {

}

/**
 * 递归
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class Solution {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private void helper(TreeNode root, List<Integer> res) {
		if (root != null) {
			res.add(root.val);
			if (root.left != null) {
				helper(root.left, res);
			}
			if (root.right != null) {
				helper(root.right, res);
			}
		}
	}
}