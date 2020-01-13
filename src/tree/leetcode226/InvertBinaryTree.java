package tree.leetcode226;

import common.datastruct.TreeNode;

/**
 * 226. 翻转二叉树
 *
 * @author mqq
 * @date 2020-01-13
 */
public class InvertBinaryTree {

}

/**
 * 递归
 * 思路：
 * 1、翻转二叉树，实际上就是翻转左右子节点
 * 2、翻转当前节点的左右子节点后，递归反转子节点的左右子节点
 * 3、递归终止条件为 root==null，即当 root 为 null 时，无法再继续翻转
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 */
class Solution {
	public TreeNode invertTree(TreeNode root) {
		// terminator
		if (root == null) {
			return null;
		}
		// process current logic
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		// drill down
		invertTree(root.left);
		invertTree(root.right);
		// restore current status
		return root;
	}
}