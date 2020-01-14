package tree.leetcode236;

import common.datastruct.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author mqq
 * @date 2020-01-14
 */
public class LowestCommonAncestorOfABinaryTree {

}

class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// terminator
		if (root == null || root == p || root == q) {
			return root;
		}
		// drill down
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		// 左右子树同时存在 p 和 q
		if (left != null && right != null) {
			return root;
		}
		// 左子树为空则返回右子树，否则返回左子树
		return left == null ? right : left;
	}
}