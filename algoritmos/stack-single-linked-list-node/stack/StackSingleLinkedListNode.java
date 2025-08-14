package stack;

import linkedList.SingleLinkedListNode;

/**
 * Classe que representa uma pilha (Stack) implementada usando nós de uma lista
 * simplesmente ligada como estrutura subjacente.
 *
 * Restrições e requisitos:
 * - Todos os métodos da pilha devem obedecer à política de acesso típica de pilhas,
 *   ou seja, operações de push, pop e peek devem ser O(1).
 * - Não é permitido utilizar memória extra ou estruturas auxiliares fora dos nós da lista.
 * - Quaisquer métodos auxiliares necessários devem ser implementados dentro desta classe.
 * - A classe SingleLinkedListNode não pode ser modificada.
 *
 * @param <T> Tipo dos elementos armazenados na pilha.
 */
public class StackSingleLinkedListNode<T> implements Stack<T>{
  private SingleLinkedListNode<T> top;
	private int tamanhoAtual;
	private int tamanhoMaximo;

	/**
	 * A pilha para ser criada precisa ter um tamanho maximo
	 * @param tamanhoMaximo
	 */
	public StackSingleLinkedListNode(int tamanhoMaximo) {
		this.tamanhoAtual = 0;
		this.tamanhoMaximo = tamanhoMaximo;
		this.top = new SingleLinkedListNode<T>();
	}

	@Override
	public void push(T element) {
		if(this.isFull()) throw new StackOverflowException();

		if(element != null){
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>();
			newNode.setData(element);

			if(this.top.isNIL()){
				this.top = newNode;
				newNode.setNext(new SingleLinkedListNode<>());
			}else{
				newNode.setNext(this.top);
				this.top = newNode;
			}

			this.tamanhoAtual++;
		}
	}
	@Override
	public T pop() throws StackUnderflowException {
		if(this.isEmpty()) throw new StackUnderflowException();

		T answer =  null;
		if(!this.top.isNIL()){
			answer = this.top.getData();
			this.top.setData(this.top.getNext().getData());
			this.top.setNext(this.top.getNext().getNext());
			this.tamanhoAtual--;
		}

		return answer;
	}
	@Override
	public T top() {
		T answer = null;
		if(!this.top.isNIL()){
			answer = this.top.getData();
		}
		return answer;
	}
	@Override
	public boolean isEmpty() {
		return this.tamanhoAtual == 0;
	}
	@Override
	public boolean isFull() {
		return this.tamanhoAtual == this.tamanhoMaximo;
	}

	public class Main {
    public static void main(String[] args) {
			// Create a stack with maximum size 3
			StackSingleLinkedListNode<Integer> stack = new StackSingleLinkedListNode<>(3);

			System.out.println("Is stack empty? " + stack.isEmpty()); // true

			// Testing push
			System.out.println("\nPushing 10");
			stack.push(10);
			System.out.println("Top: " + stack.top());               // 10
			System.out.println("Is stack empty? " + stack.isEmpty()); // false

			System.out.println("\nPushing 20");
			stack.push(20);
			System.out.println("Top: " + stack.top());               // 20

			System.out.println("\nPushing 30");
			stack.push(30);
			System.out.println("Top: " + stack.top());               // 30
			System.out.println("Is stack full? " + stack.isFull());  // true

			// Trying to push beyond maximum size
			try {
					System.out.println("\nPushing 40 (should throw StackOverflowException)");
					stack.push(40);
			} catch (StackOverflowException e) {
					System.out.println("Caught exception: " + e.getMessage());
			}

			// Testing pop
			System.out.println("\nPopping elements:");
			while (!stack.isEmpty()) {
					System.out.println("Pop: " + stack.pop() + ", new top: " + stack.top());
			}

			// Trying to pop from an empty stack
			try {
					System.out.println("\nPop from empty stack (should throw StackUnderflowException)");
					stack.pop();
			} catch (StackUnderflowException e) {
					System.out.println("Caught exception: " + e.getMessage());
			}
    }
	}
}