package array.leetcode88;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 *
 * @author mqq
 * @date 2020-01-11
 */
public class MergeSortedArray {
	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int[] nums2 = {2, 5, 6};
		Solution solution = new DoublePointerFromBackSolution();
		solution.merge(nums1, 3, nums2, 3);
		System.out.println(Arrays.toString(nums1));
	}
}

/**
 * Brute Force
 * 思路：
 * 1、直接合并代码，再重新排序
 * 时间复杂度：O((m+n)log(m+n))
 * 空间复杂度：O(1)
 */
class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int i : nums2) {
			nums1[m++] = i;
		}
		Arrays.sort(nums1);
	}
}

/**
 * 双指针法
 * 思路：
 * 1、先 copy 一份 nums1 到另一个数据 temp
 * 2、定义 3 个指针，p1 指向 temp，p2 指向 nums2，p 指向 nums1
 * 3、比较 p1 和 p2 指针元素大小，下面 3、4 步骤用 p1 和 p2 表示 p1 和 p2 指针指向的元素
 * 4、p1 < p2，则 p = p1，然后向后移动指针 p1 和 p
 * 5、p2 < p1，则 p = p2，然后向后移动指针 P2 和 p
 * 6、循环结束，把剩余未遍历的元素复制到 nums1 中（循环结束肯定有一个数组遍历完）
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(m)
 */
class DoublePointerSolution extends Solution {
	@Override
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] temp = new int[m];
		System.arraycopy(nums1, 0, temp, 0, m);
		int p1 = 0, p2 = 0, p = 0;
		while ((p1 < m) && (p2 < n)) {
			nums1[p++] = temp[p1] < nums2[p2] ? temp[p1++] : nums2[p2++];
		}
		if (p1 < m) {
			System.arraycopy(temp, p1, nums1, p, nums1.length - p);
		}
		if (p2 < n) {
			System.arraycopy(nums2, p2, nums1, p, nums2.length - p2);
		}
	}
}

/**
 * 双指针法（从后往前）
 * 思路：
 * 1、定义指针 p、i、j，其中 p 用户追踪添加元素的位置，初始位置为 m+n-1；i 和 j 分别指向 nums1 和 nums2 最后一个元素
 * 2、从后往前遍历 nums1 和 nums2，把较大者放入 p 位置，往后移动较大者和 p 指针
 * 3、循环结束，若 nums2 还有元素未遍历，则拷贝到 nums1 数组中，下标从 0 开始
 * 时间复杂度：O(m+n)
 * 空间复杂度：O(1)
 */
class DoublePointerFromBackSolution extends Solution {
	@Override
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int p = m + n - 1;
		int i = m - 1;
		int j = n - 1;
		while (i >= 0 && j >= 0) {
			nums1[p--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		}
		if (j >= 0) {
			System.arraycopy(nums2, 0, nums1, 0, j + 1);
		}
	}
}