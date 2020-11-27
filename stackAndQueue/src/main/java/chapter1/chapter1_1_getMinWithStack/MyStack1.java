package chapter1.chapter1_1_getMinWithStack;

import lombok.extern.log4j.Log4j2;

import java.util.Stack;

/*
题目：实现一个特殊的栈，在实现栈的基本功能基础上，再实现返回栈中最小元素的操作。
要求：
    1. pop、push、getMin 操作的时间复杂度都是 O(1)
    2. 设计的栈类型可以使用现成的栈结构
 */
/*
解题思路1：
   使用两个栈，stackData保存元素，stackMin保存每个元素入 stackData 的最小值。
   1. 入栈操作：stackData元素A入栈; 当 stackMin栈为空 或 元素A <= stackMin栈顶元素, 将元素A压入 stackMin栈
   （此时 stackData的当前元素一定是 >= stackMin的栈顶元素）
   2. 出栈操作：stackData 栈顶元素A出栈; 若 元素A = stackMin 栈顶元素，则 stackMin 也出栈操作（这时 stackMin 的栈顶元素一定是最小元素）

解题思路2：
    与思路1的区别是：在stackData入栈时，若元素 > stackMin栈顶元素，则重复压入 stackMin栈顶元素；否则压入该元素。
    stackData出栈时，同步对 stackMin进行出栈操作。这样stackMin每次获取栈顶都是最小值。
    代码省略...
 */
@Log4j2
public class MyStack1 {
//    private static final Logger log = LogManager.getLogger(MyStack1.class);

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1(Stack<Integer> stackData, Stack<Integer> stackMin) {
        this.stackData = stackData;
        this.stackMin = stackMin;
    }

    /**
     * 入栈操作
     * @param newNum
     */
    public void push(Integer newNum) {
        //压入stackData
        stackData.push(newNum);
        //判断newNum是否 > stackMin栈顶元素
        if (stackMin.empty() || newNum <= stackMin.peek()) {
            stackMin.push(newNum);
        }
    }

    /**
     * 出站操作（出栈操作stackData时，也要操作stackMin）
     * @return
     */
    public Integer pop() {
        Integer topElement = stackData.pop();
        //当stackData的栈顶元素 = stackMin 栈顶元素时，stackMin 也要弹出栈顶
        //当stackData栈顶元素 > stackMin栈顶元素时，stackMin 不弹出
        if (topElement == stackMin.peek()) {
            stackMin.pop();
        }
        return topElement;
    }

    /**
     * 获取栈中最小值
     * @return
     */
    public Integer getMin() {
        if (!stackMin.isEmpty()) {
            return stackMin.peek();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        //测试数据
        int[] data = {4, 7, 3, 1, 5, 3, 9, 4};
        Stack<Integer> stackData = new Stack<Integer>();
        Stack<Integer> stackMin = new Stack<Integer>();

        MyStack1 myStack1 = new MyStack1(stackData, stackMin);
        //将所有测试数据压入栈中
        for (int i = 0; i < data.length; i++) {
            myStack1.push(data[i]);
        }
        log.info("MyStack1.stackData: {}", myStack1.stackData);
        log.info("MyStack1.stackMin: {}", myStack1.stackMin);
        log.info("MyStack1.getMin: {}", myStack1.getMin());

        //循环弹出栈顶元素，并记录每次栈内数据
        log.info("-------------开始出栈---------------");
        for (int i = 0; i < data.length; i++) {
            log.info("***第{}次pop***", i + 1);
            myStack1.pop();
            log.info("MyStack1.stackData: {}", myStack1.stackData);
            log.info("MyStack1.stackMin: {}", myStack1.stackMin);
            log.info("MyStack1.getMin: {}", myStack1.getMin());
        }
    }
}
