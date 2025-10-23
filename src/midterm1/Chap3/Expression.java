package midterm1.Chap3;

import java.util.Scanner;
// ArrayStack 등 필요한 Stack 구현체가 있다고 가정합니다.

public class Expression {	
    // ⭐ 1. Op enum (순서 중요: lparen(0) ~ power(10))
	enum Op {lparen, rparen, plus, minus, times, divide, mod, eos, operand, power, space}
    
    // ⭐ 2. isp/icp 배열 (크기 11로 수정, operand(8), power(9), space(10) 추가)
	int[] icp = {20, 19, 12, 12, 13, 13, 13, 0, 0, 15, 0}; // icp[operand]=0, icp[power]=15, icp[space]=0
	int[] isp = { 0, 19, 12, 12, 13, 13, 13, 0, 0, 14, 0}; // isp[operand]=0, isp[power]=14, isp[space]=0
	
	public int eval(String e) {
        // (Stack<Integer> stack = new ArrayStack<>(); 등이 이 클래스 내에 있거나 import되어야 함)
		Stack<Integer> stack = new ArrayStack<>(); 
		for (int i=0; i < e.length(); i++) {
			Op token = nextToken(e.charAt(i));
			
			if (token == Op.operand) {
				stack.push(e.charAt(i) - '0');
			} 
            else if (token == Op.space) { // ⭐ 3. 공백 무시
                continue;
            }
            // ⭐ 4. lparen, rparen, eos 등은 eval에서 만나면 안 됨 (이미 postfix에서 처리됨)
            else if (token == Op.plus || token == Op.minus || token == Op.times ||
                     token == Op.divide || token == Op.mod || token == Op.power) 
            {
				int op2 = stack.pop();
				int op1 = stack.pop();
				switch(token) {
    				case plus: stack.push(op1 + op2); break;
    				case minus: stack.push(op1 - op2); break;
    				case times: stack.push(op1 * op2); break;
    				case divide: stack.push(op1 / op2); break;
    				case mod: stack.push(op1 % op2); break;
                    case power: stack.push((int)Math.pow(op1, op2)); break; // ⭐ 5. power 계산 추가
				}
			}
		}
		return stack.pop();
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
    		case '^': return Op.power; // ⭐ 6. power 토큰 추가
    		case ' ': return Op.space; // ⭐ 7. 공백(space) 토큰으로 변경
    		// eos는 문자열 끝을 의미하므로, ' '와 다름. (여기서는 사용되지 않음)
    		default: return Op.operand;
		}
	}
	
	public String postfix(String e) {
		Op token;
		StringBuilder sb = new StringBuilder();
		Stack<Op> stack = new ArrayStack<>();
		stack.push(Op.eos); // eos는 index 7
		
		for (int i=0; i < e.length(); i++) {
			token = nextToken(e.charAt(i));
			
            if (token == Op.space) { // ⭐ 8. 공백 무시
                continue;
            }
            
			if (token == Op.operand) {
				sb.append(e.charAt(i));
			} else if (token == Op.rparen) {
				while(stack.top() != Op.lparen) {
					sb.append(printToken(stack.pop()));
				}
				stack.pop(); // lparen 버리기
			} else {
                // isp[token.ordinal()] -> ArrayIndexOutOfBoundsException 방지
				while (isp[stack.top().ordinal()] >= icp[token.ordinal()]) {
					sb.append(printToken(stack.pop()));
				}
				stack.push(token);
			}
		}
		while (stack.top() != Op.eos) {
			sb.append(printToken(stack.pop()));
		}
		return sb.toString();
	}
	
	private char printToken(Op token) {
		switch(token) {
    		case plus: return '+';
    		case minus: return '-';
    		case times: return '*';
    		case divide: return '/';
    		case power: return '^';
    		case mod: return '%';
    		default: return ' '; // lparen, eos 등은 출력 안 함
		}
	}
	
	public static void main(String[] args) {
		Expression algorithm = new Expression();
		Scanner sc = new Scanner(System.in);
		System.out.print("Infix notation? ");
		String infix = sc.nextLine();
		String postfix = algorithm.postfix(infix);
		System.out.println("Postfix notation= " + postfix);
        // eval은 공백 없는 postfix 문자열을 처리
		System.out.println("실행 결과= " + algorithm.eval(postfix.replaceAll(" ", "")));
		sc.close();
	}
	
    // --- ArrayStack 클래스 구현이 필요함 ---
    // (이 코드가 작동하려면 ArrayStack과 Stack 인터페이스가 필요합니다)
    // (이전에 만드신 코드를 여기에 붙여넣으세요)
    public interface Stack<T> {
        void push(T item);
        T pop();
        T top();
        boolean isEmpty();
    }

    public static class ArrayStack<T> implements Stack<T> {
        private int top;
        private T[] stack;
        private int capacity;
        
        @SuppressWarnings("unchecked")
        public ArrayStack(int initialCapacity) {
            this.capacity = initialCapacity;
            this.stack = (T[]) new Object[capacity];
            this.top = -1;
        }

        public ArrayStack() { this(10); } // 기본 생성자

        public boolean isEmpty() { return (top == -1); }
        public boolean isFull() { return (top == capacity - 1); }

        public void push(T item) {
            if (isFull()) stackFull();
            stack[++top] = item;
        }

        public T pop() {
            if (isEmpty()) return null; 
            return stack[top--];
        }
        
        public T top() { // peek()
            if (isEmpty()) return null;
            return stack[top];
        }

        @SuppressWarnings("unchecked")
        private void stackFull() {
            this.capacity *= 2;
            T[] newStack = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, newStack, 0, top + 1);
            this.stack = newStack;
        }
    }
}