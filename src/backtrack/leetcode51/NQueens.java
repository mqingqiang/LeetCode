package backtrack.leetcode51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N皇后
 *
 * @author mqq
 * @date 2020-01-17
 */
public class NQueens {
	public static void main(String[] args) {
		Solution solution = new BacktrackingSolution();
		List<List<String>> lists = solution.solveNQueens(4);
		System.out.println("lists = " + lists.toString());
	}
}

/**
 * 根据《数据结构与算法之美》代码编写，未改造成 LeetCode 格式
 * 思路：
 * 1、数组 ans 存储结果，下标表示行，值表示存储的 queen 所在列
 * 2、递归终止条件 row == total 表示 n 行已经全部放置了 queen
 * 3、循环表示每行有 n 种方法，isOK() 过滤不符合要求的放法
 * 4、ans[i] == col 判断是否同一列，Math.abs(row - i) == Math.abs(col - ans[i]) 判断是否在对角线上
 */
class Solution {
	public List<List<String>> solveNQueens(int n) {
		helper(new int[8], 0, n);
		return null;
	}

	private void helper(int[] ans, int row, int total) {
		if (row == total) {
			printQueens(ans);
			return;
		}
		for (int col = 0; col < total; col++) {
			if (isOK(ans, row, col)) {
				ans[row] = col;
				helper(ans, row + 1, total);
			}
		}
	}

	private boolean isOK(int[] ans, int row, int col) {
		for (int i = row - 1; i >= 0; i--) {
			if (ans[i] == col || Math.abs(row - i) == Math.abs(col - ans[i])) {
				return false;
			}
		}
		return true;
	}

	private void printQueens(int[] ans) {
		for (int row = 0; row < ans.length; row++) {
			for (int column = 0; column < ans.length; column++) {
				if (ans[row] == column) {
					System.out.print("Q ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

/**
 * 回溯
 * 思路：
 * 1、使用 int[] result 存储每一种放法，下标表示行，value 表示 queen 所在列
 * 2、每行校验每个位置是否有效，有效则放到 result 数组中
 * 3、最后把 result 转为 List<String> 形式，存到数组 List<List<String>> 中
 */
class BacktrackingSolution extends Solution {
	@Override
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ans = new ArrayList<>();
		if (n < 1) {
			return ans;
		}
		helper(ans, new int[n], 0, n);
		return ans;
	}

	private void helper(List<List<String>> ans, int[] result, int row, int n) {
		if (row == n) {
			ans.add(arrayToList(result));
			return;
		}
		// 每行有 8 中放法
		for (int column = 0; column < n; column++) {
			if (valid(result, row, column)) {
				result[row] = column;
				helper(ans, result, row + 1, n);
			}
		}
	}

	/**
	 * 判断皇后所放位置是否有效
	 */
	private boolean valid(int[] result, int row, int column) {
		for (int i = 0; i < row; i++) {
			// result[i] == column 列是否相同；Math.abs(row - i) == Math.abs(column - result[i]) 对角线是否相同
			if (result[i] == column || Math.abs(row - i) == Math.abs(column - result[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 数组转为特殊的 List<String> 形式
	 */
	private List<String> arrayToList(int[] result) {
		List<String> list = new ArrayList<>();
		for (int value : result) {
			char[] temp = new char[result.length];
			Arrays.fill(temp, '.');
			temp[value] = 'Q';
			list.add(String.valueOf(temp));
		}
		return list;
	}
}