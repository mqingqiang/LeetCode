package tree.leetcode104;


import java.util.LinkedList;
import java.util.Queue;

import common.datastruct.TreeNode;

/**
 * 104. 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * @author mqq
 * @date 2020-01-05
 */
public class MaxDepthOfBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		Solution solution = new BFSSolution();
		int maxDepth = solution.maxDepth(root);
		System.out.println("maxDepth = " + maxDepth);
	}
}

/**
 * DFS（递归）
 * 最大深度：左节点或右节点最大深度中较大值 + 1
 * 时间复杂度：由于每个节点仅访问一次，时间复杂度为 O(n)
 * 空间复杂度：
 * 当二叉树退化为链表时，树的高度为 n，递归将会调用 n 次，空间复杂度为 O(n)；当二叉树极度平衡时，二叉树的高度接近 O(logn)，空间复杂度为 O(logn)
 */
class Solution {
	public int maxDepth(TreeNode root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}

/**
 * BFS
 * 广度优先搜索，使用队列存储每一层的节点。
 * 首先入队根节点，队列不为空时，出队队头节点并入队该节点左右子节点，更新最大深度，继续迭代直到队列为空，此时深度为最大深度。
 */
class BFSSolution extends Solution {
	@Override
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// 最大深度
		int maxDepth = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode poll = queue.poll();
				if (poll != null && poll.left != null) {
					queue.offer(poll.left);
				}
				if (poll != null && poll.right != null) {
					queue.offer(poll.right);
				}
			}
			maxDepth++;
		}
		return maxDepth;
	}
}