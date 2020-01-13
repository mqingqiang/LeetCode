package string.leetcode22;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * @author mqq
 * @date 2020-01-12
 */
public class GenerateParentheses {
	public static void main(String[] args) {
		Solution solution = new Solution();
		List<String> parentheses = solution.generateParenthesis(3);
		System.out.println(parentheses.toString());
	}
}

/**
 * 递归
 * 思路：
 * 1、首先第一个字符肯定是左括号
 * 2、至少已插入左括号的个数 left < n，均可插入左括号
 * 3、只要已插入右括号的个数 right < left，均可插入右括号
 * 4、当括号用完时返回，即 left==n&&right==n
 */
class Solution {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		generate(0, 0, n, "", res);
		return res;
	}

	private void generate(int left, int right, int n, String parenthes, List<String> res) {
		if (left == n && right == n) {
			res.add(parenthes);
			return;
		}
		if (left < n) {
			generate(left + 1, right, n, parenthes + "(", res);
		}
		if (right < left) {
			generate(left, right + 1, n, parenthes + ")", res);
		}
	}
}