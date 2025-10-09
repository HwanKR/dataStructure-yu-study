package dataStructure;

import java.util.EmptyStackException;

public class MultipleStacks {

    // --- 멤버 변수 ---
    private final int MSIZE; // 전체 메모리 크기
    private final int n;     // 실제 스택의 수

    private final Object[] memory; // 데이터를 저장할 공유 메모리 배열
    private final int[] top;      // 각 스택의 top 포인터 배열
    private final int[] boundary; // 각 스택의 경계(바닥/천장) 배열

    // --- 생성자 ---
    public MultipleStacks(int numberOfStacks, int totalSize) {
        if (numberOfStacks <= 0 || totalSize <= 0) {
            throw new IllegalArgumentException("스택의 수와 전체 사이즈는 0보다 커야 합니다.");
        }
        if (totalSize < numberOfStacks) {
            throw new IllegalArgumentException("전체 사이즈는 스택의 수보다 크거나 같아야 합니다.");
        }

        this.n = numberOfStacks;
        this.MSIZE = totalSize;

        this.memory = new Object[MSIZE];
        this.top = new int[n];
        this.boundary = new int[n + 1];

        // --- 경계 및 top 초기화 (슬라이드 내용) ---
        boundary[0] = top[0] = -1;
        for (int i = 1; i < n; i++) {
            boundary[i] = top[i] = (MSIZE / n) * i - 1;
        }
        boundary[n] = MSIZE - 1;
    }

    // --- Push 메서드 (핵심 로직) ---
    public void push(int stackNum, Object item) {
        if (stackNum < 0 || stackNum >= n) {
            throw new IndexOutOfBoundsException("유효하지 않은 스택 번호입니다.");
        }

        // 1. 현재 스택이 꽉 찼는지 확인
        if (top[stackNum] == boundary[stackNum + 1]) {
            // 2. 꽉 찼다면, 공간 확보 시도
            if (!makeSpace(stackNum)) {
                // 3. 공간 확보에 실패하면, 진짜 메모리가 꽉 찬 것
                throw new StackOverflowError("메모리가 가득 찼습니다 (Memory is full).");
            }
        }

        // 4. 공간이 있으면 데이터 추가
        top[stackNum]++;
        memory[top[stackNum]] = item;
    }

    // --- Pop 메서드 ---
    public Object pop(int stackNum) {
        if (stackNum < 0 || stackNum >= n) {
            throw new IndexOutOfBoundsException("유효하지 않은 스택 번호입니다.");
        }
        if (top[stackNum] == boundary[stackNum]) {
            throw new EmptyStackException();
        }

        Object item = memory[top[stackNum]];
        top[stackNum]--;
        return item;
    }

    // --- 공간 확보 메서드 (Shift 로직) ---
    private boolean makeSpace(int stackNum) {
        // 1. 오른쪽 스택들에서 여유 공간 찾기
        for (int i = stackNum + 1; i < n; i++) {
            if (top[i] < boundary[i + 1]) {
                // 여유 공간을 찾았으면, i부터 stackNum+1 까지의 스택들을 오른쪽으로 한 칸씩 민다.
                shiftRight(i);
                return true; // 공간 확보 성공
            }
        }

        // 2. 왼쪽 스택들에서 여유 공간 찾기
        for (int i = stackNum - 1; i >= 0; i--) {
            if (top[i] < boundary[i + 1]) {
                // 여유 공간을 찾았으면, i부터 stackNum-1 까지의 스택들을 왼쪽으로 한 칸씩 민다.
                shiftLeft(i);
                return true; // 공간 확보 성공
            }
        }

        return false; // 양쪽에 여유 공간이 전혀 없음
    }

    // --- 오른쪽으로 이동(Shift Right) ---
    private void shiftRight(int startStackNum) {
        // startStackNum 부터 (공간을 밀어줘야 하는) 스택까지 역순으로 이동
        for (int i = startStackNum; i > 0; i--) {
            if(top[i-1] == boundary[i]) { // 비어있지 않은 스택만 이동
                 // 스택 i-1의 모든 데이터를 오른쪽으로 한 칸씩 복사
                for (int j = top[i - 1]; j > boundary[i - 1]; j--) {
                    memory[j + 1] = memory[j];
                }
                // 경계와 top 포인터를 1씩 증가
                top[i - 1]++;
                boundary[i]++;
            } else { // 비어있는 스택은 경계만 이동
                 boundary[i]++;
            }
        }
    }

    // --- 왼쪽으로 이동(Shift Left) ---
    private void shiftLeft(int startStackNum) {
        // startStackNum 부터 (공간을 당겨와야 하는) 스택까지 순서대로 이동
        for (int i = startStackNum; i < n - 1; i++) {
             if(top[i+1] == boundary[i+1]) { // 비어있지 않은 스택만 이동
                // 스택 i+1의 모든 데이터를 왼쪽으로 한 칸씩 복사
                for (int j = boundary[i + 1] + 1; j <= top[i + 1]; j++) {
                    memory[j - 1] = memory[j];
                }
                // 경계와 top 포인터를 1씩 감소
                top[i + 1]--;
                boundary[i + 1]--;
             } else { // 비어있는 스택은 경계만 이동
                boundary[i + 1]--;
            }
        }
    }

    // --- 상태 출력을 위한 헬퍼 메서드 ---
    public void printState() {
        System.out.println("=".repeat(40));
        System.out.println("Current State of Multiple Stacks");
        for (int i = 0; i < n; i++) {
            System.out.printf("Stack %d: (Boundary: %d, Top: %d, Ceiling: %d) -> ",
                    i, boundary[i], top[i], boundary[i+1]);
            for (int j = boundary[i] + 1; j <= top[i]; j++) {
                System.out.print(memory[j] + " ");
            }
            System.out.println();
        }
        System.out.println("=".repeat(40));
    }


    // --- 실행 예제 ---
    public static void main(String[] args) {
        // 12칸의 메모리를 3개의 스택이 나누어 쓴다. (각 4칸씩)
        MultipleStacks stacks = new MultipleStacks(3, 12);
        stacks.printState();

        // 0번 스택을 가득 채운다.
        System.out.println("\n>>> Filling Stack 0...");
        for (int i = 0; i < 4; i++) {
            stacks.push(0, "S0-" + i);
        }
        stacks.printState();

        // 1번 스택에 2개만 채운다.
        System.out.println("\n>>> Pushing 2 items to Stack 1...");
        stacks.push(1, "S1-A");
        stacks.push(1, "S1-B");
        stacks.printState();

        // 이제 0번 스택은 꽉 찼다. 하나 더 push 해보자.
        System.out.println("\n>>> Pushing one more item to full Stack 0...");
        // 1번 스택이 여유가 있으므로, 경계가 이동하며 push에 성공해야 한다.
        stacks.push(0, "S0-4");
        stacks.printState();
        
        // 2번 스택을 가득 채운다.
        System.out.println("\n>>> Filling Stack 2...");
        for (int i = 0; i < 4; i++) {
            stacks.push(2, "S2-" + i);
        }
        stacks.printState();

        // 이제 2번 스택은 꽉 찼다. 하나 더 push 해보자.
        System.out.println("\n>>> Pushing one more item to full Stack 2...");
        // 1번 스택이 여유가 있으므로, 경계가 이동하며 push에 성공해야 한다.
        stacks.push(2, "S2-4");
        stacks.printState();
    }
}