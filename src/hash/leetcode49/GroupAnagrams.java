package hash.leetcode49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. 字母异位词分组
 *
 * @author mqq
 * @date 2020-01-12
 */
public class GroupAnagrams {

}

/**
 * Brute Force
 * 思路：
 * 1、遍历字符串数组，对每个字符串转化为 char[] 进行排序
 * 2、维护一个 HashMap，key 为 sortedString，value 为异位词分组后的字符串列表
 * 时间复杂度：O(NKlogK),其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度。
 * 空间复杂度：O(NK)
 */
class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> ans = new HashMap<>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String sorted = String.valueOf(chars);
			if (!ans.containsKey(sorted)) {
				ans.put(sorted, new ArrayList<>());
			}
			ans.get(sorted).add(str);
		}
		return new ArrayList<>(ans.values());
	}
}