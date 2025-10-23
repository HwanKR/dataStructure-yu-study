package midterm1.Chap3;

import java.util.Scanner;

// "추가 함수/클래스 구현 금지" 룰에 따라
// ArrayStack 클래스, Stack 인터페이스를 모두 제거하고
// postfix, eval 함수 내부에 배열과 top 변수로 스택을 직접 구현합니다.

public class Expression {	
    
    // enum Op (순서 중요: lparen(0) ~ power(10))
	// lparen(0), rparen(1), plus(2), minus(3), times(4), 
    // divide(5), mod(6), eos(7), operand(8), power(9), space(10)
	enum Op {lparen, rparen, plus, minus, times, divide, mod, eos, operand, power, space}
    
    // isp/icp 배열 (크기 11, enum Op 순서와 동일)
	int[] icp = {20, 19, 12, 12, 13, 13, 13, 0, 0, 15, 0}; // icp[operand]=0, icp[power]=15, icp[space]=0
	int[] isp = { 0, 19, 12, 12, 13, 13, 13, 0, 0, 14, 0}; // isp[operand]=0, isp[power]=14, isp[space]=0
	
    /**
     * 후위 표기식 계산 (Postfix Evaluation)
     * ArrayStack 클래스 대신 [int 배열]과 [top 변수]로 스택 구현
     */
	public int eval(String e) {
        // --- 스택 구현 (ArrayStack 클래스 대신 사용) ---
        int[] stack = new int[e.length()]; // 피연산자(숫자)를 담을 스택
        int top = -1;                      // 스택 top 초기값
        // ------------------------------------------

		for (int i=0; i < e.length(); i++) {
			Op token = nextToken(e.charAt(i));
			
			if (token == Op.operand) {
                // stack.push(e.charAt(i) - '0'); 대신
				stack[++top] = e.charAt(i) - '0';
			} 
            else if (token == Op.space) { // 공백 무시
                continue;
            }
            // lparen, rparen, eos 등은 eval에서 만나면 안 됨
            else if (token == Op.plus || token == Op.minus || token == Op.times ||
                     token == Op.divide || token == Op.mod || token == Op.power) 
            {
                // int op2 = stack.pop(); 대신
				int op2 = stack[top--];
                // int op1 = stack.pop(); 대신
				int op1 = stack[top--];
                
				switch(token) {
                    // stack.push(...) 대신 stack[++top] = ...
    				case plus:   stack[++top] = op1 + op2; break;
    				case minus:  stack[++top] = op1 - op2; break;
    				case times:  stack[++top] = op1 * op2; break;
    				case divide: stack[++top] = op1 / op2; break;
    				case mod:    stack[++top] = op1 % op2; break;
                    case power:  stack[++top] = (int)Math.pow(op1, op2); break;
				}
			}
		}
        // return stack.pop(); 대신
		return stack[top--];
	} 
	
    /**
     * 중위 표기식 -> 후위 표기식 변환 (Infix to Postfix)
     * ArrayStack 클래스 대신 [Op 배열]과 [top 변수]로 스택 구현
     */
	public String postfix(String e) {
		Op token;
		StringBuilder sb = new StringBuilder();
        
        // --- 스택 구현 (ArrayStack 클래스 대신 사용) ---
		Op[] stack = new Op[e.length() + 1]; // 연산자를 담을 스택 (+1은 초기 eos 공간)
        int top = -1;                        // 스택 top 초기값
        // ------------------------------------------

        // stack.push(Op.eos); 대신
		stack[++top] = Op.eos; // eos는 index 7
		
		for (int i=0; i < e.length(); i++) {
			token = nextToken(e.charAt(i));
			
            if (token == Op.space) { // 공백 무시
                continue;
            }
            
			if (token == Op.operand) {
				sb.append(e.charAt(i));
			} else if (token == Op.rparen) {
                // while(stack.top() != Op.lparen) 대신
				while(stack[top] != Op.lparen) {
                    // sb.append(printToken(stack.pop())); 대신
					sb.append(printToken(stack[top--]));
				}
				// stack.pop(); (lparen 버리기) 대신
                top--; 
			} else {
                // while (isp[stack.top().ordinal()] >= icp[token.ordinal()]) 대신
				while (isp[stack[top].ordinal()] >= icp[token.ordinal()]) {
                    // sb.append(printToken(stack.pop())); 대신
					sb.append(printToken(stack[top--]));
				}
                // stack.push(token); 대신
				stack[++top] = token;
			}
		}
        // while (stack.top() != Op.eos) 대신
		while (stack[top] != Op.eos) {
            // sb.append(printToken(stack.pop())); 대신
			sb.append(printToken(stack[top--]));
		}
		return sb.toString();
	}
	
    /**
     * 헬퍼 함수: char -> Op 토큰 변환
     */
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
	
    /**
     * 헬퍼 함수: Op 토큰 -> char 변환 (출력용)
     */
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
	
    /**
     * C 코드의 main 함수와 동일
     * TestDriver 역할
     */
	public static void main(String[] args) {
		Expression algorithm = new Expression();
		Scanner sc = new Scanner(System.in);
		System.out.print("Infix notation? ");
		String infix = sc.nextLine();
        
        // 1. Infix -> Postfix 변환
		String postfix = algorithm.postfix(infix);
		System.out.println("Postfix notation= " + postfix);
        
        // 2. Postfix 계산
        // eval은 공백 없는 postfix 문자열을 처리해야 함
		System.out.println("실행 결과= " + algorithm.eval(postfix.replaceAll(" ", "")));
		sc.close();
	}
	
    // ArrayStack 클래스와 Stack 인터페이스는 모두 제거됨
}