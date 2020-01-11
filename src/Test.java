import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mqq
 * @date 2020-01-06
 */
public class Test {
	public static void main(String[] args) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(Arrays.asList(-1, 0, 1));
		list.add(Arrays.asList(2, 3, 4));
		List<Integer> temp = Arrays.asList(5, 6, 7);
		if (list.containsAll(temp)) {
			System.out.println("存在 4");
		}
	}
}