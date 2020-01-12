package hash.leetcode242;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 *
 * @author mqq
 * @date 2020-01-12
 */
public class ValidAnagram {

}

/**
 * Brute Force
 * 思路：
 * 1、s、t 转为字符数组
 * 2、对字符数组进行排序
 * 3、比对排序后的字符数组是否相等
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 */
class Solution {
	public boolean isAnagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		char[] char1 = s.toCharArray();
		char[] char2 = t.toCharArray();
		Arrays.sort(char1);
		Arrays.sort(char2);
		return Arrays.equals(char1, char2);
	}
}

/**
 * 哈希字母表计数法
 * 思路：
 * 1、由于 s、t 只包含小写字母，声明一个长度为 26 的数组 counter 用于计数
 * 2、s 字母用于 counter 计数器加一，t 字母用于 counter 计数器减一
 * 3、最后遍历计数器，看是否存在计数不等于 0 的计数。存在则返回 false，否则返回 true
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class HashAlphabetSolution extends Solution {
	@Override
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] counter = new int[26];
		for (int i = 0; i < s.length(); i++) {
			counter[s.charAt(i) - 'a']++;
			counter[t.charAt(i) - 'a']--;
		}
		for (int i : counter) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}

/**
 * HashMap 计数法
 * 思路：
 * 1、使用 Map 存储每个字符 char 与其出现的次数
 * 2、s 所在字符做加法，t 所在字符做减法
 * 3、最后遍历 HashMap，若出现计数不为 0 的情况，返回 false，否则返回 true
 * 注意：该法如果出现哈希冲突，结果不准确。这里假如 a 和 b 会出现哈希冲突（实际不会）,
 * s = aabbc，t = bbbbc，结果返回 true，因为 hash('a') = hash('b')
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class HashMapSolution extends Solution {
	@Override
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		Map<Character, Integer> countMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
			countMap.put(t.charAt(i), countMap.getOrDefault(t.charAt(i), 0) - 1);
		}
		for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			if (entry.getValue() != 0) {
				return false;
			}
		}
		return true;
	}
}

