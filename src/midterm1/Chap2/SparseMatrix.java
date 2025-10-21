package midterm1.Chap2;

import java.util.ArrayList;

public class SparseMatrix {
	class MatrixTerm {
		int row, col, value;
		
		public MatrixTerm(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
		
		public String toString() {
			return "(row= " + row + ", col= " + col + ", value= " + value + ")";
		}
	}
	
	int mRows, mCols, mTerms;
	ArrayList<MatrixTerm> smArray;
	
	public SparseMatrix(int row, int col) {
		mRows = row;
		mCols = col;
		mTerms = 0;
	}
	
	public String toString() {
		return "희소 행렬: " + smArray;
	}
	
	public SparseMatrix transpose() {
		SparseMatrix B = new SparseMatrix(mCols, mRows);
		if (mTerms > 0) {
			for (int c=0; c<mCols; c++) {
				for (int i=0; i<mTerms; i++) {
					if (smArray.get(i).col == c) {
						B.addTerm(c, smArray.get(i).row, smArray.get(i).value); 
					}
				}
			}
		}
		return B;
	}
	
	public void addTerm(int row, int col, int value) {
		if (row < 0 || row >= mRows || col < 0 || col >= mCols) {
			return;
		}
		smArray.add(new MatrixTerm(row, col, value));
		mTerms++;
	}
	
	public SparseMatrix fastTranspose() {
		SparseMatrix B = new SparseMatrix(mCols, mRows);
		int[] rowTerms = new int[mCols];
		int[] startingPos = new int[mCols];
		int i, j;
		
		if (mTerms > 0) {
			for (i=0; i < mTerms; i++) {
				rowTerms[smArray.get(i).col]++;
				B.addTerm(0 , 0, 0);
			}
			startingPos[0] = 0;
			for (i = 1; i < mCols; i++) {
				startingPos[i] = startingPos[i-1] + rowTerms[i-1];
			}
			for (i = 0; i < mTerms; i++) {
				j = startingPos[smArray.get(i).col]++;
				MatrixTerm term = B.smArray.get(j);
				term.col = smArray.get(i).row;
				term.row = smArray.get(i).col;
				term.value = smArray.get(i).value;
			}
		}
		return B;
	}
}
