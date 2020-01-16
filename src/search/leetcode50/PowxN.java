package search.leetcode50;

/**
 * 50. Pow(x, n)
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author mqq
 * @date 2020-01-16
 */
public class PowxN {
	public static void main(String[] args) {
		double x = 2.00000;
		int n = -2147483648;
		System.out.println("n = " + n);
		System.out.println("abs = " + Math.abs(n));
		System.out.println("MaxInteger = " + Integer.MIN_VALUE);
		Solution solution = new Solution();
		solution.myPow(x, n);
	}
}

/**
 * 分治递归
 * 思路：
 * 1、求 x 的 n 次幂，等于求 x 的 n/2 次幂乘以自身，如：
 * n 为奇数：x^n = x^(n/2) * x^(n/2) * x
 * n 为偶数：x^n = x^(n/2) * x^(n/2)
 * 2、终止条件为 n == 0 时，任何数的 0 次幂等于 1
 * 3、当 n < 0 时，求 x 的 n 次幂等于求 (1/x）的 n 次幂，n 要求绝对值
 * 时间复杂度：O(logn)
 * 空间复杂度：O(1)
 */
class Solution {
	public double myPow(double x, int n) {
		// 当 n 为 Integer.MAX_VALUE 时，n != -n，所以需要借助使用 long 类型转换
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		return half(x, N);
	}

	private double half(double x, long n) {
		if (n == 0) {
			return 1;
		}
		double half = half(x, n / 2);
		return n % 2 == 1 ? half * half * x : half * half;
	}
}