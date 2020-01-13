package tree.leetcode98;

import java.util.ArrayList;
import java.util.List;

import common.datastruct.TreeNode;

/**
 * 98. 验证二叉搜索树
 *
 * @author mqq
 * @date 2020-01-13
 */
public class ValidateBinarySearchTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(-2147483648);
		Solution solution = new BinaryTreeInorderSolution();
		boolean validBST = solution.isValidBST(root);
		System.out.println("validBST = " + validBST);
	}
}

/**
 * 笨方法
 * 思路：
 * 1、先用递归中序遍历二叉树，遍历结果放到 List 中
 * 2、再遍历 List，判断 i<i+1 是否成立
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)
 */
class Solution {
	public boolean isValidBST(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		recursion(root, res);
		for (int i = 0; i < res.size() - 1; i++) {
			if (res.get(i) >= res.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	private void recursion(TreeNode root, List<Integer> res) {
		// terminator
		if (root == null) {
			return;
		}
		// drill down
		if (root.left != null) {
			recursion(root.left, res);
		}
		res.add(root.val);
		if (root.right != null) {
			recursion(root.right, res);
		}
	}
}

/**
 * 中序遍历
 * 思路：
 * 1、中序遍历过程中，每个节点的值均大于左子节点的值并且小于右子节点的值。那么依次判断每个节点，均满足该条件即为二叉搜索树
 * 2、使用变量 prev 记录上一个遍历节点的值
 * 3、用 null 表示  prev 无穷小，递归左子树是否返回 true，若不是，直接返回 false；若是，继续往下执行
 * 4、当前节点是否满足二叉搜索树条件，若不满足，直接返回 false；若满足，继续往下直接
 * 5、把当前节点的值赋给 prev，即 prev = root.val，继续递归右子树
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class BinaryTreeInorderSolution extends Solution {
	Integer prev = null;

	@Override
	public boolean isValidBST(TreeNode root) {
		return recursion(root);
	}

	private boolean recursion(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (!recursion(root.left)) {
			return false;
		}
		if (prev != null && prev >= root.val) {
			return false;
		}
		prev = root.val;
		return recursion(root.right);
	}
}

/**
 * 递归
 * 思路：
 * 1、这里使用前序遍历，根节点的取值范围为 [null, null]，null 表示无穷小和无穷大，
 * 2、左子节点取值范围为 [null, root]，右子节点取值范围为 [root, null]，root 为父节点的值
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)
 */
class RecursionSolution extends Solution {
	@Override
	public boolean isValidBST(TreeNode root) {
		return helper(root, null, null);
	}

	private boolean helper(TreeNode root, Integer left, Integer right) {
		// terminator
		if (root == null) {
			return true;
		}
		// process current logic
		if ((left != null && left >= root.val) || (right != null && root.val >= right)) {
			return false;
		}
		return helper(root.left, left, root.val) && helper(root.right, root.val, right);
	}
}