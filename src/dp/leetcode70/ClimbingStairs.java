package dp.leetcode70;

/**
 * 70. 爬楼梯
 *
 * @author mqq
 * @date 2020-01-06
 */
public class ClimbingStairs {

}

/**
 * 递归
 * 思路：
 * 1、n 级台阶只能从 n-1 级或 n-2 级上来。该问题实际上是一个斐波那契数列，f(n) = f(n-1) + f(n-2)
 * 2、使用记忆缓存 cache 存储递归过程的中间结果，防止重复计算问题
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 注意：Java 中函数调用会使用栈来保存临时变量，递归深度越大，栈空间使用越大；调用层次很深，就会有堆栈溢出风险。
 * 因此递归调用通常要限制最大深度，超过最大深度就不再往下递归了。
 */
class Solution {

	public int climbStairs(int n) {
		int[] cache = new int[n + 1];
		return climbStairs(n, cache);
	}

	public int climbStairs(int n, int[] cache) {
		if (n <= 3) {
			return n;
		}
		int result = cache[n];
		if (result == 0) {
			result = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
			cache[n] = result;
		}
		return result;
	}
}

/**
 * 斐波那契数列
 * 思路：
 * 1、该问题实际上就是一个斐波那契数列，公式：f(n) = f(n-1) + f(n-2)
 * 2、使用变量 f1 和 f2 记录 f(n-1) 和 f(n-2) 的值，循环 n 次累加 f(n-1) + f(n-2)，循环结束 f2 的值即为第 n 项的结果
 * 时间复杂度：O(n）
 * 空间复杂度：O(1)
 */
class LoopSolution extends Solution {
	@Override
	public int climbStairs(int n) {
		if (n <= 3) {
			return n;
		}
		int f1 = 1;
		int f2 = 2;
		for (int i = 3; i <= n; i++) {
			int f3 = f1 + f2;
			f1 = f2;
			f2 = f3;
		}
		return f2;
	}
}