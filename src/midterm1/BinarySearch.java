package midterm1;

public class BinarySearch {
	private int compare(int x, int y) {
		return (x < y) ? -1 : (x > y) ? 1 : 0;
	}
	
	public int bsearch(int[] A, int key) {
		for (int left=0, right=A.length-1; left<=right;) {
			int middle = (left + right) / 2;
			switch (compare(A[middle], key)) {
			case -1: left = middle + 1; break;
			case 1: right = middle - 1; break;
			case 0: return middle;
			}
		}
		return -1;
	}
	
	public int bsearch2(int[] A, int key) {
		return bsearchR(A, key, 0, A.length-1);
	}
	
	private int bsearchR(int[] A, int key, int left, int right) {
		if (left <= right) {
			int middle = (left + right)/2;
			switch(compare(A[middle], key)) {
			case -1: return bsearchR(A, key, middle+1, right);
			case 1: return bsearchR(A, key, left, middle-1);
			case 0: return middle;
			}
		}
		return -1;
	}
}
