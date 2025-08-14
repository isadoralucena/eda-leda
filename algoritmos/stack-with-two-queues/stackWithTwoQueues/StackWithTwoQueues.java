package stackWithTwoQueues;

import java.util.LinkedList;
import java.util.Queue;
/*
 * Nesta implementação de pilha com duas filas:
 * - A fila queue1 será responsável por armazenar todos os elementos e realizar as operações principais
 * - A fila queue2 funciona apenas como auxiliar durante a operação de remoção (pop)
 * - As demais operações (push, top, isEmpty, isFull) acontecem somente em queue1
 */
public class StackWithTwoQueues {
  private int maxSize = 10;
  private Integer topElement = null;
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
    return this.topElement;
  }

  public void push(Integer element){
    if(this.isFull()){
      throw new StackOverflowException();
    }
    if(element != null){
      queue1.add(element);
      topElement = element; 
    }
  }
  
  public Integer pop(){
    if(this.isEmpty()){
			throw new StackUnderflowException();
		}

      while(queue1.size() > 1){
        Integer val = queue1.remove();
        queue2.add(val);
        this.topElement = val; 
      }

      Integer answer = queue1.remove();

      Queue<Integer> temp = queue1;
      queue1 = queue2;
      queue2 = temp;

      return answer;
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