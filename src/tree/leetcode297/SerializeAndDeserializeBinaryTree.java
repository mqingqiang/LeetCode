package tree.leetcode297;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import common.datastruct.TreeNode;

/**
 * 297. 二叉树的序列化与反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @author mqq
 * @date 2020-01-14
 */
public class SerializeAndDeserializeBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		Codec codec = new Codec();
		String serialize = codec.serialize(root);
		System.out.println("serialize = " + serialize);
	}
}

/**
 * 递归
 * 思路：
 * 1、对于序列化，使用数的前序遍历，以逗号（其他任何字符都可以，比如空格）分隔每个节点，叶子节点的 null 用 # 代替（也可用其他字符，如 X 等）
 * 2、前序遍历后得到一个字符串（如：1,2,3,#,#,4,5）
 * 3、对于反序列化，步骤 2 中得到的字符串，使用逗号分隔，得到一个字符串数组
 * 4、字符串数组转化为栈
 * 5、递归遍历栈，从栈顶开始构建树，遇到 # 则表示当前子树已到叶子节点，否则构建该节点
 */
class Codec {

	private static final String SPLITER = ",";

	private static final String NN = "#";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder builder = new StringBuilder();
		buildString(root, builder);
		return builder.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || "".equals(data)) {
			return null;
		}
		String[] split = data.split(SPLITER);
		Deque<String> deque = new ArrayDeque<>(Arrays.asList(split));
		return buildTree(deque);
	}

	private TreeNode buildTree(Deque<String> deque) {
		String val = deque.removeFirst();
		if (NN.equals(val)) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(val));
		root.left = buildTree(deque);
		root.right = buildTree(deque);
		return root;
	}

	private void buildString(TreeNode root, StringBuilder buildString) {
		// terminator
		if (root == null) {
			buildString.append(NN).append(SPLITER);
			return;
		}
		// process current logic
		buildString.append(root.val).append(SPLITER);
		// drill down
		buildString(root.left, buildString);
		buildString(root.right, buildString);
	}
}