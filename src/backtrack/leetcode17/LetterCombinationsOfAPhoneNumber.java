package backtrack.leetcode17;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * @author mqq
 * @date 2020-01-17
 */
public class LetterCombinationsOfAPhoneNumber {
	public static void main(String[] args) {
		Solution solution = new Solution();
		List<String> list = solution.letterCombinations("23");
		System.out.println("list = " + list.toString());
	}
}

/**
 * 回溯
 * 思路：
 * 1、先定义数字字符与字母的映射关系，使用数组，下标为数字字母，值为字母字符串
 * 2、递归终止条件结果字符串长度等于输入字符串 digits 长度
 * 3、遍历每个数字对应的字母
 */
class Solution {
	private String[] phoneNumber = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

	public List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		if (digits == null || "".equals(digits)) {
			return ans;
		}
		helper(ans, "", digits, 0);
		return ans;
	}

	private void helper(List<String> ans, String s, String digits, int index) {
		if (index == digits.length()) {
			ans.add(s);
			return;
		}
		String num = phoneNumber[digits.charAt(index) - '0'];
		for (int i = 0; i < num.length(); i++) {
			helper(ans, s + num.charAt(i), digits, index + 1);
		}
	}
}