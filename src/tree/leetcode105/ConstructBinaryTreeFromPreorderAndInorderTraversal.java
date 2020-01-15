package tree.leetcode105;

import common.datastruct.TreeNode;
import common.util.TreeUtils;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author mqq
 * @date 2020-01-15
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public static void main(String[] args) {
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		Solution solution = new Solution();
		TreeNode root = solution.buildTree(preorder, inorder);
		TreeUtils.preOrder(root);
		TreeUtils.inOrder(root);
		TreeUtils.postOrder(root);
	}
}

/**
 * 前序：3 9 30 15 7
 * 中序：9 3 15 20 7
 * 递归分治
 * 思路：
 * 1、前序遍历的第一个结点肯定是根节点（如 3）
 * 2、中序遍历中等于前序遍历第一个结点的值（该值假设为 pivot），其左边（如 9）为当前节点的左子树，右边（如 15 20 7）为当前节点的右子树
 * 3、第2步可以得到左子树的节点个数 size，前序遍历从当前节点往右前 size 个结点为左子树（9），往后的节点为右子树（30 15 7）
 * 4、递归重复以上步骤即可，终止条件为 inStart > inEnd
 * 关键点：前序遍历中右子树的开始位置确认，如第 2 步中前序的 3 等于中序的 3，那么设 pivot 为中序值为 3 的元素的索引（即 size），
 * 那么 x - (preStart + 1) = pivot - inStart，其中 x 为前序右子树的开始位置，preStart + 1 为前序左子树的开始位置，pivot 参考步骤 2 描述，
 * inStart 为中序遍历开始位置，求得：x = preStart + pivot - inStart + 1
 */
class Solution {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(preorder, inorder, 0, 0, inorder.length - 1);
	}

	private TreeNode helper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
		// terminator
		if (inStart > inEnd) {
			return null;
		}
		// process current logic
		TreeNode root = new TreeNode(preorder[preStart]);
		int pivot = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == preorder[preStart]) {
				pivot = i;
				break;
			}
		}
		// drill down
		root.left = helper(preorder, inorder, preStart + 1, inStart, pivot - 1);
		root.right = helper(preorder, inorder, preStart + pivot - inStart + 1, pivot + 1, inEnd);
		return root;
	}
}
