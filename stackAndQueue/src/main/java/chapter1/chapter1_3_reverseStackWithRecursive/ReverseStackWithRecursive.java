package chapter1.chapter1_3_reverseStackWithRecursive;

import lombok.extern.log4j.Log4j2;

import java.util.Stack;

/*
题目：使用递归函数反转一个stack
 */
/*
解题思路：
   使用两个递归函数，函数1 返回栈中最后一个元素，并将该值从stack中pop；函数2 将函数1获取的值重新push进stack
 */
@Log4j2
public class ReverseStackWithRecursive {
    /**
     * 将stack中最后一个元素pop出，保持其他元素不动
     * @param stack
     *
     * @return
     */
    private static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * stack反转
     * @param stack
     */
    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        //测试数据
        int[] data = {4, 7, 3, 1, 5, 3, 9};
        Stack<Integer> stackData = new Stack<Integer>();
        for (int i = 0; i < data.length; i++) {
            stackData.push(data[i]);
        }
        log.info("stackData: {}", stackData);

        //调用反转stack的函数
        reverse(stackData);

        log.info("stackData after reverse: {}", stackData);
    }
}
