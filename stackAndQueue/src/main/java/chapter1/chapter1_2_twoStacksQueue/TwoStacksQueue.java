package chapter1.chapter1_2_twoStacksQueue;

import lombok.extern.log4j.Log4j2;

import java.util.Stack;

/*
题目：使用两个栈实现队列，支持队列的基本操作（add、poll、peek）
 */
/*
解题思路：
   使用两个栈，stackPush 入栈，stackPop 出栈。stackPush 每次操作后，需将 stackPush 中的所有元素压入stackPop。
   注意：每次数据由 stackPush 压入 stackPop后，如果有出栈并且 有新元素要入栈 stackPush，
   则需要将 stackPop 未出栈的元素重新 压入 stackPush（在这之前保证stackPush是空的），然后再压入新元素到 stackPush（保证顺序）
   最后再将 stackPush 压入 stackPop保证出栈顺序
 */
@Log4j2
public class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue(Stack<Integer> stackPush, Stack<Integer> stackPop) {
        this.stackPush = stackPush;
        this.stackPop = stackPop;
    }

    /**
     * 将stackPush栈的数据压入stackPop栈
     * git
     */
    public void pushToPop() {
        //若 stackPop 为空，直接push
        if (!stackPop.isEmpty()) {
            stackPop.clear();
        }
        while (!stackPush.isEmpty()) {
            stackPop.push(stackPush.pop());
        }
    }

    /**
     * 将stackPop的元素压回 stackPush，以便接受新的元素
     */
    public void popToPush() {
        if (!stackPush.isEmpty()) {
            stackPush.clear();
        }
        while (!stackPop.isEmpty()) {
            stackPush.push(stackPop.pop());
        }
    }

    /**
     * 元素入队尾
     * @param newNum
     */
    public void add(int newNum) {
        if (!stackPop.isEmpty()) {
            popToPush();
        }
        stackPush.add(newNum);
        pushToPop();
    }

    /**
     * 队头元素出队
     * @return
     */
    public int poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackPop.pop();
    }

    /**
     * 获取队头元素
     * @return
     */
    public int peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackPop.peek();
    }

    public static void main(String[] args) {
        //测试数据
        int[] data1 = {4, 7, 3};
        int[] data2 = {1, 5, 9};
        Stack<Integer> stackData = new Stack<Integer>();
        Stack<Integer> stackMin = new Stack<Integer>();

        TwoStacksQueue queue = new TwoStacksQueue(stackData, stackMin);
        //将data1压入栈中
        for (int i = 0; i < data1.length; i++) {
            queue.add(data1[i]);
        }

        //先出队列一个元素，应该是 4
        log.info("queue top element = {}", queue.peek());
        queue.poll();
        log.info("(after poll 1)queue top element = {}", queue.peek());

        //再将data2 压入栈中，然后出队列一个元素，应该是7
        for (int i = 0; i < data2.length; i++) {
            queue.add(data2[i]);
        }
        log.info("queue top element = {}", queue.peek());
        queue.poll();
        log.info("(after poll 2)queue top element = {}", queue.peek());
    }
}
