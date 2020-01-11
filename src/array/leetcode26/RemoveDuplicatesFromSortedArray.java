package array.leetcode26;

/**
 * 26. 删除排序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * @author mqq
 * @date 2020-01-11
 */
public class RemoveDuplicatesFromSortedArray {
	public static void main(String[] args) {
		int[] nums = {0, 0, 1, 1, 1, 2, 3, 3, 3, 4, 5};
		Solution solution = new Solution();
		int i = solution.removeDuplicates(nums);
		System.out.println("i = " + i);
	}
}

/**
 * 双指针法
 * 思路：
 * 1、i、j 两个指针，j 指针用来记录没有重复的数据，i 指针遍历数组
 * 2、当 nums[i] == nums[j] 时，证明元素重复
 * 3、当 nums[i] != nums[j] 时，证明元素不重复，把 j 的元素移动到 i 的下一个位置
 * 4、遍历结束，返回 j + 1 即为元素个数
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class Solution {
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int j = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[j]) {
				nums[++j] = nums[i];
			}
		}
		return j + 1;
	}
}