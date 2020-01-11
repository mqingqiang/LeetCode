package array.leetcode66;

import java.util.Arrays;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/discuss/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class PlusOne {
	public static void main(String[] args) {
		int[] digits = new int[]{4, 3, 9};
		Solution solution = new Solution();
		solution.plusOne(digits);
		System.out.println(Arrays.toString(digits));
	}
}

/**
 * 末位无进位，则末位加一即可，因为末位无进位，前面也不可能产生进位，比如 45 => 46
 * 末位有进位，在中间位置进位停止，则需要找到进位的典型标志，即为当前位 %10后为 0，则前一位加 1，直到不为 0 为止，比如 499 => 500
 * 末位有进位，并且一直进位到最前方导致结果多出一位，对于这种情况，需要在第 2 种情况遍历结束的基础上，进行单独处理，比如 999 => 1000
 * <p>
 * 作者：guanpengchn
 * 链接：https://leetcode-cn.com/problems/plus-one/solution/hua-jie-suan-fa-66-jia-yi-by-guanpengchn/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
	public int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i]++;
			digits[i] %= 10;
			if (digits[i] != 0) {
				return digits;
			}
		}
		digits = new int[digits.length + 1];
		digits[0] = 1;
		return digits;
	}
}