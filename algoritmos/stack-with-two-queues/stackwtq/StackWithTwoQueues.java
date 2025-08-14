package stackwtq;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues {
  private int maxSize = 10;
  private Queue<Integer> queue1 = new LinkedList<>();
  private Queue<Integer> queue2 = new LinkedList<>();

  public boolean isEmpty(){
    return queue1.isEmpty();
  }

  public boolean isFull(){
    return queue1.size() == maxSize;
  }

  public boolean contains(Integer element){
    return queue1.contains(element);
  }

  public Integer top(){
    Integer last = null;
    for (Integer val : queue1) {
      last = val;
    }
    return last;
  }

  public void push(Integer element){
    if(this.isFull()){
      throw new StackOverflowException();
    }
    queue1.add(element);
  }
  
  public void pop(){
    if(this.isEmpty()){
			throw new StackUnderflowException();
		}


      while(queue1.size() > 1){
        queue2.add(queue1.remove());
      }

      queue1.remove();

      Queue<Integer> temp = queue1;
      queue1 = queue2;
      queue2 = temp;
  }

  public static void main(String[] args) {
    StackWithTwoQueues stack = new StackWithTwoQueues();

    System.out.println("New stack is empty: " + stack.isEmpty()); // true

    stack.push(10);
    System.out.println("Top after pushing 10: " + stack.top()); // 10
    stack.push(20);
    System.out.println("Top after pushing 20: " + stack.top()); // 20
    stack.push(30);
    System.out.println("Top after pushing 30: " + stack.top()); // 30

    stack.pop();
    System.out.println("Top after pop: " + stack.top()); // 20
    stack.pop();
    System.out.println("Top after pop: " + stack.top()); // 10

    System.out.println("Stack is empty after pops: " + stack.isEmpty()); // false

    stack.pop();
    System.out.println("Stack is empty after last pop: " + stack.isEmpty()); // true
    System.out.println("Top of empty stack: " + stack.top()); // null

    // Test StackUnderflowException
    try {
        stack.pop();
    } catch (StackUnderflowException e) {
        System.out.println("Caught StackUnderflowException: " + e.getMessage());
    }

    // Test StackOverflowException
    try {
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
    } catch (StackOverflowException e) {
        System.out.println("Caught StackOverflowException: " + e.getMessage());
    }

    // Test sequence of operations
    stack = new StackWithTwoQueues(); // reset stack
    stack.push(100);
    stack.push(200);
    stack.pop();
    stack.push(300);
    System.out.println("Final top after sequence: " + stack.top()); // 300
  }
}