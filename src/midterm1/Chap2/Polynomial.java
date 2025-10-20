package midterm1.Chap2;

import java.util.ArrayList;

public class Polynomial {
	public class PolynomialTerm {
		public double coef;
		public int exp;
		
		public PolynomialTerm(double coef, int exp) {
			this.coef = coef;
			this.exp = exp;
		}
		
		public String toString() {
			return "(" + "coef= " + coef + ", exp= " + exp + ")";
		}
	}
	
	private final ArrayList<PolynomialTerm> termArray;

	public Polynomial() {
		termArray = new ArrayList<>();
	}
	
	public void appendTerm(double coef, int exp) {
		termArray.add(new PolynomialTerm(coef, exp));
	}
	
	@Override
	public String toString() {
		return "다항식: " + termArray;
	}
	
	public Polynomial add(Polynomial B) {
		Polynomial D = new Polynomial();
		
		int posA = 0, posB = 0;
		double sum;
		while (posA < termArray.size() && posB < B.termArray.size()) {
			PolynomialTerm termA = termArray.get(posA);
			PolynomialTerm termB = B.termArray.get(posB);
			switch (Integer.compare(termA.exp, termB.exp)) {
			case 1: D.appendTerm(termA.coef,  termA.exp);
				posA++;
				break;
			case -1: D.appendTerm(termB.coef,  termB.exp);
				posB++;
				break;
			case 0: sum = termA.coef + termB.coef;
				if (sum != 0.0);
					D.appendTerm(sum,  termA.exp);
				posA++; posB++;
			}
		}
		
		for (; posA < termArray.size(); posA++) {
			D.appendTerm(termArray.get(posA).coef, termArray.get(posA).exp);
		}
		for (; posB < B.termArray.size(); posB++) {
			D.appendTerm(B.termArray.get(posB).coef, B.termArray.get(posB).exp);
		}	
		return D;
	}
	
	public static void main(String[] args) {
		Polynomial A = new Polynomial();
		Polynomial B = new Polynomial();
		
		A.appendTerm(3,  14);
		A.appendTerm(2, 8);
		A.appendTerm(5, 1);
		A.appendTerm(1, 0);
		
		B.appendTerm(8, 14);
		B.appendTerm(-3, 10);
		B.appendTerm(10, 6);
		B.appendTerm(-5, 1);
		
		System.out.println("A " + A);
		System.out.println("B " + B);
		System.out.println("A + B " + A.add(B));
	}
}
