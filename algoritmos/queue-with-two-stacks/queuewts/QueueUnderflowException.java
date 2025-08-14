package queuewts;

public class QueueUnderflowException extends RuntimeException {
	public QueueUnderflowException() {
		super("Queue is empty");
	}
}
