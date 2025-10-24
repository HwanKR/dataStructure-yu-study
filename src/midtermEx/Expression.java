package midtermEx;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Expression {
	enum Op {lparen, rparen, plus, minus, times, divide, mod, power, eos, space, operand}
	int[] icp = {20, 19, 12, 12, 13, 13, 13, 15, 0, 0, 0};
	int[] isp = { 0, 19, 12, 12, 13, 13, 13, 14, 0, 0, 0};
	
	public String postfix(String e) {
		Op token;
		StringBuilder sb = new StringBuilder();
		
		Op[] stack = new Op[e.length()+1];
		int top = -1;
		
		stack[++top] = Op.eos;
		
		for (int i=0; i < e.length(); i++) {
			token = nextToken(e.charAt(i));
			
			if (token == Op.space) continue;
			if (token == Op.operand) {
				sb.append(e.charAt(i));
			} else if (token == Op.rparen) {
				while (stack[top] != Op.lparen) {
					sb.append(printToken(stack[top--]));
				}
				top--;
			} else {
				while (isp[stack[top].ordinal()] >= icp[token.ordinal()]) {
					sb.append(printToken(stack[top--]));
				}
				stack[++top] = token;
			}
		}
		
		while (stack[top] != Op.eos) {
			sb.append(printToken(stack[top--]));
		}
		return sb.toString();
	}
	
	private Op nextToken(char ch) {
		switch (ch) {
		case '(': return Op.lparen;
		case ')': return Op.rparen;
		case '+': return Op.plus;
		case '-': return Op.minus;
		case '*': return Op.times;
		case '/': return Op.divide;
		case '%': return Op.mod;
		case '^': return Op.power;
		case ' ': return Op.space;
		default: return Op.operand;
		}
	}
	
	private char printToken(Op token) {
		switch (token) {
		case plus: return '+';
		case minus: return '-';
		case times: return '*';
		case divide: return '/';
		case mod: return '%';
		case power: return '^';
		default: return ' ';
		}
	}
	
	public void processFile(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			
			while (sc.hasNextLine()) {
				String infix = sc.nextLine();
				System.out.println("Infix: " + infix);
				String postfix = postfix(infix);
				System.out.println("Postfix: " + postfix);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
