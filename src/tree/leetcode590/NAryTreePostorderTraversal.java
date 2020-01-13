package tree.leetcode590;

import java.util.ArrayList;
import java.util.List;

import common.datastruct.Node;

/**
 * 590. N叉树的后序遍历
 *
 * @author mqq
 * @date 2020-01-12
 */
public class NAryTreePostorderTraversal {

}

class Solution {
	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private void helper(Node root, List<Integer> res) {
		if (root != null) {
			List<Node> children = root.children;
			for (Node child : children) {
				if (child != null) {
					helper(child, res);
				}
			}
			res.add(root.val);
		}
	}
}