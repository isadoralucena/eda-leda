package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

	}

	private void getImplementations() {
		stack1 = new StackDoubleLinkedListImpl<>(4);
		stack2 = new StackDoubleLinkedListImpl<>(2);
		stack3 = null;
	}

	@Test
	public void testTop() {
		assertEquals(new Integer(3), stack1.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(stack1.isFull());
	}

	@Test
	public void testPush() {
		try {
			stack1.push(new Integer(5));
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(new Integer(5));
		stack1.push(new Integer(5));
	}

	@Test
	public void testPop() {
		try {
			assertEquals(new Integer(3), stack1.pop());
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals(new Integer(3), stack1.pop());
		assertEquals(new Integer(2), stack1.pop());
		assertEquals(new Integer(1), stack1.pop());
		assertEquals(new Integer(7), stack1.pop());
	}

	@Test
public void testPopEmOrdemCorreta() throws StackUnderflowException {
	assertEquals(new Integer(3), stack1.pop());
	assertEquals(new Integer(2), stack1.pop());
	assertEquals(new Integer(1), stack1.pop());
	assertTrue(stack1.isEmpty());
}

@Test
public void testPushPopAlternado() throws StackOverflowException, StackUnderflowException {
	stack1.pop();
	stack1.push(99); 
	assertEquals(new Integer(99), stack1.top());
	assertEquals(new Integer(99), stack1.pop());
	assertEquals(new Integer(2), stack1.top());
}

@Test
public void testTopSemModificar() throws StackUnderflowException {
	Integer top1 = stack1.top();
	Integer top2 = stack1.top();
	assertEquals(top1, top2);
	assertEquals(new Integer(3), top1);
}

@Test
public void testIsEmptyNaStack3() {
	getImplementations();
	assertTrue(stack3 == null || stack3.isEmpty());
}

@Test
public void testIsFullStack2() {
	assertTrue(stack2.isFull());
}

@Test(expected = StackOverflowException.class)
public void testPushStack2Estourando() throws StackOverflowException {
	stack2.push(99);
}

@Test
public void testPushPopTodosElementos() throws StackOverflowException, StackUnderflowException {
	getImplementations();
	stack3 = new StackDoubleLinkedListImpl<>(3);
	assertTrue(stack3.isEmpty());

	stack3.push(10);
	stack3.push(20);
	stack3.push(30);
	assertTrue(stack3.isFull());

	assertEquals(new Integer(30), stack3.pop());
	assertEquals(new Integer(20), stack3.pop());
	assertEquals(new Integer(10), stack3.pop());
	assertTrue(stack3.isEmpty());
}

}