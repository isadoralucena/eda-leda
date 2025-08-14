package stack;

public class StackOverflowException extends RuntimeException {

	public StackOverflowException() {
		super("Stack is full");
	}
}
