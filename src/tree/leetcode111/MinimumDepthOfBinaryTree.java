package tree.leetcode111;

import common.datastruct.TreeNode;

/**
 * 111. 二叉树的最小深度
 *
 * @author mqq
 * @date 2020-01-13
 */
public class MinimumDepthOfBinaryTree {

}

/**
 * 递归
 * 思路：
 * 1、该题主要是理解递归终止条件
 * 2、root 为空时，直接返回 0
 * 3、root 左右子节点都为空时，返回 1
 * 4、root 左右子节点有一个为空时，返回不为空的子节点的深度
 * 4、root 左右子节点都不为空时，返回较小者的深度
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)
 */
class Solution {
	public int minDepth(TreeNode root) {
		// terminator
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		// drill down
		int leftDepth = minDepth(root.left);
		int rightDepth = minDepth(root.right);
		if (root.left == null || root.right == null) {
			return leftDepth + rightDepth + 1;
		}
		// restore current status
		return Math.min(leftDepth, rightDepth) + 1;
	}
}