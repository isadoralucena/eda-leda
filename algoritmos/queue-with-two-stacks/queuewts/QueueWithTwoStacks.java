package queuewts;

import java.util.Stack;

public class QueueWithTwoStacks{
  private int maxSize = 10; 
  private Stack<Integer> stack1 = new Stack<>();
  private Stack<Integer> stack2 = new Stack<>();

  public void enqueue(Integer element){
    if(isFull()) throw new QueueOverflowException();

    if(element != null){
      stack1.push(element);
    }
  }

  public Integer dequeue(){
    if(this.isEmpty()){
			throw new QueueUnderflowException();
		}

    if (stack2.isEmpty()) {
      transferStack1ToStack2();
  }
        
    return stack2.pop();
  }

  public Integer head(){
    if(this.isEmpty()){
			throw new QueueUnderflowException();
		}

    if (stack2.isEmpty()) {
      transferStack1ToStack2();
    }
        
    return stack2.peek();
  }


  private void transferStack1ToStack2() {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
  }

  public boolean contains(Integer element){
    boolean answer = false;
    
    if(element != null){
      answer = stack1.contains(element) || stack2.contains(element);
    }
    return answer;
  }

  public int size(){
    return stack1.size() + stack2.size();
  }

  public boolean isEmpty(){
    return stack1.isEmpty() && stack2.isEmpty();
  }

  public boolean isFull(){
    return this.size() == maxSize;
  }
  public static void main(String[] args) {
    QueueWithTwoStacks queue = new QueueWithTwoStacks();
    queue.enqueue(10);
    System.out.println("Head after enqueue 10: " + queue.head()); // 10
    queue.enqueue(20);
    System.out.println("Head after enqueue 20: " + queue.head()); // 10
    queue.enqueue(30);
    System.out.println("Head after enqueue 30: " + queue.head()); // 10

    Integer removed = queue.dequeue();
    System.out.println("Element removed: " + removed); // 10
    System.out.println("Novo head: " + queue.head()); // 20

    removed = queue.dequeue();
    System.out.println("Element removed: " + removed); // 20
    System.out.println("New head: " + queue.head()); // 30

    queue.enqueue(40);
    System.out.println("Head após enqueue 40: " + queue.head()); // 30

    removed = queue.dequeue();
    System.out.println("Element removed: " + removed); // 30
    System.out.println("Novo head: " + queue.head()); // 40

    removed = queue.dequeue();
    System.out.println("Element removed: " + removed); // 40
    System.out.println("Empty queue? " + queue.isEmpty()); // true

    try {
        queue.dequeue();
    } catch (RuntimeException e) {
        System.out.println("Exception when removing from empty queue: " + e.getMessage());
    }
    }
}
