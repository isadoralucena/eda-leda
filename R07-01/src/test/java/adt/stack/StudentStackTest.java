package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
		stack2.push(10);
		stack2.push(20);
	}

	private void getImplementations() {
		stack1 = new StackImpl(3);
		stack2 = new StackImpl(2);
		stack3 = new StackImpl(6);
	}

	@Test
	public void testTop() {
		assertEquals(Integer.valueOf(3), stack1.top());
		assertEquals(Integer.valueOf(20), stack2.top());
		assertNull(stack3.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertTrue(stack1.isFull());
		assertTrue(stack2.isFull());

		stack3.push(1);
		stack3.push(2);
		assertFalse(stack3.isFull());
	}

	@Test
	public void testPushAndTop() throws StackOverflowException {
		stack3.push(5);
		stack3.push(8);
		assertEquals(Integer.valueOf(8), stack3.top());
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(999);
	}
	
	@Test
	public void testPop() throws StackUnderflowException {
		assertEquals(Integer.valueOf(3), stack1.pop());
		assertEquals(Integer.valueOf(2), stack1.top());
		assertFalse(stack1.isFull());
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack3.pop();
	}

	@Test
	public void testPushPopPushPop() throws Exception {
		stack3.push(10);
		assertEquals(Integer.valueOf(10), stack3.pop());

		stack3.push(20);
		stack3.push(30);
		assertEquals(Integer.valueOf(30), stack3.pop());
		assertEquals(Integer.valueOf(20), stack3.pop());

		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testPopEsvaziaTudo() throws Exception {
		assertEquals(Integer.valueOf(3), stack1.pop());
		assertEquals(Integer.valueOf(2), stack1.pop());
		assertEquals(Integer.valueOf(1), stack1.pop());
		assertTrue(stack1.isEmpty());
		assertNull(stack1.top());
	}

	@Test
	public void testEstadoInicial() {
		Stack<Integer> s = new StackImpl<>(5);
		assertTrue(s.isEmpty());
		assertFalse(s.isFull());
		assertNull(s.top());
	}
}