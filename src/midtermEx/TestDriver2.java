package midtermEx;

import java.util.Scanner;

public class TestDriver2 {
	public static class Expression {
		enum Op {lparen, rparen, plus, minus, times, divide, mod, power, eos, operand, space}
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
		
		public int eval(String e) {
			Op token;
			
			int[] stack = new int[e.length()];
			int top = -1;
			
			for (int i=0; i < e.length(); i++) {
				token = nextToken(e.charAt(i));
				
				if (token == Op.space) continue;
				if (token == Op.operand) {
					stack[++top] = e.charAt(i) - '0';
				} else if (token == Op.plus || token == Op.minus || token == Op.times
						|| token == Op.divide || token == Op.mod || token == Op.power) {
					int op2 = stack[top--];
					int op1 = stack[top--];
					
					switch(token) {
    				case plus:   stack[++top] = op1 + op2; break;
    				case minus:  stack[++top] = op1 - op2; break;
    				case times:  stack[++top] = op1 * op2; break;
    				case divide: stack[++top] = op1 / op2; break;
    				case mod:    stack[++top] = op1 % op2; break;
                    case power:  stack[++top] = (int)Math.pow(op1, op2); break;
				}
				}
			}
			return stack[top--];
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
	}
	
	public static void main(String[] args) {
		Expression algorithm = new Expression();
		Scanner sc = new Scanner(System.in);
		System.out.print("Infix notation?: ");
		String infix = sc.nextLine();
		
		String postfix = algorithm.postfix(infix);
		System.out.println("Postfix notation= " + postfix);
		
		System.out.println("실행 결과= " + algorithm.eval(postfix.replaceAll(" ", "")));
	}
}
