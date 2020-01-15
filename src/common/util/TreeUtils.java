package common.util;

import common.datastruct.TreeNode;

/**
 * 树工具类
 *
 * @author mqq
 * @date 2020-01-15
 */
public class TreeUtils {

	private static final String SPLITER = ",";

	private static final String LEFT = "[";

	private static final String RIGHT = "]";

	public static void preOrder(TreeNode root) {
		StringBuilder builder = new StringBuilder(LEFT);
		preOrderTraversal(root, builder);
		System.out.println(builder.toString());
	}

	public static void inOrder(TreeNode root) {
		StringBuilder builder = new StringBuilder(LEFT);
		inOrderTraversal(root, builder);
		System.out.println(builder.toString());
	}

	public static void postOrder(TreeNode root) {
		StringBuilder builder = new StringBuilder(LEFT);
		postOrderTraversal(root, builder);
		System.out.println(builder.toString());
	}

	private static void preOrderTraversal(TreeNode root, StringBuilder builder) {
		if (root == null) {
			builder.append(RIGHT);
			return;
		}
		builder.append(root.val).append(SPLITER);
		preOrderTraversal(root.left, builder);
		preOrderTraversal(root.right, builder);
	}

	private static void inOrderTraversal(TreeNode root, StringBuilder builder) {
		if (root == null) {
			builder.append(RIGHT);
			return;
		}
		inOrderTraversal(root.left, builder);
		builder.append(root.val).append(SPLITER);
		inOrderTraversal(root.right, builder);
	}

	private static void postOrderTraversal(TreeNode root, StringBuilder builder) {
		if (root == null) {
			builder.append(RIGHT);
			return;
		}
		postOrderTraversal(root.left, builder);
		postOrderTraversal(root.right, builder);
		builder.append(root.val).append(SPLITER);
	}

}