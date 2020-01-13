package tree.leetcode589;

import java.util.ArrayList;
import java.util.List;

import common.datastruct.Node;

/**
 * 589. N叉树的前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 *
 * @author mqq
 * @date 2020-01-12
 */
public class NAryTreePreorderTraversal {

}

class Solution {
	public List<Integer> preorder(Node root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private void helper(Node root, List<Integer> res) {
		if (root != null) {
			res.add(root.val);
			List<Node> children = root.children;
			for (Node child : children) {
				if (child != null) {
					helper(child, res);
				}
			}
		}
	}
}